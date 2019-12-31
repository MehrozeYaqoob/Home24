package com.home24.infrastructure.platform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.home24.R
import com.home24.infrastructure.exception.Failure
import com.home24.infrastructure.extensions.invisible
import com.home24.infrastructure.extensions.visible


abstract class BaseFragment : Fragment(), BaseView {


    protected var binding: ViewDataBinding? = null
    protected open var shouldBindData = false

    //endregion
    //region Abstracts
    protected fun showProgress(showProgress: Boolean, lockScreen: Boolean) {
        if (showProgress) {
            progressBar?.visible()


        } else {
            progressBar?.invisible()

        }

        if (lockScreen) {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            activity?.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        }
    }

    protected abstract val layoutResourceId: Int

    private var progressBar: ProgressBar? = null

    //endregionx
    //region LifeCycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (shouldBindData) {
            binding = DataBindingUtil.inflate(
                inflater, layoutResourceId, container, false
            )
            binding!!.lifecycleOwner = viewLifecycleOwner
            binding!!.root
        } else
            inflater.inflate(layoutResourceId, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = activity?.findViewById(R.id.progressBar)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.getInt("TotalArticles")
        ignite(savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onDestroyView() {
        showProgress(false, false)
        super.onDestroyView()
    }

    //endregion
    //region Failures and Messages
    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    protected fun handleFailure(failure: Failure?) {
        showProgress(false, lockScreen = false)

        when (failure) {
            is Failure.NetworkConnection -> showMessage(getString(R.string.failure_network_connection))
            is Failure.ServerError -> showMessage(getString(R.string.server_error))
        }
    }

    //endregion
}