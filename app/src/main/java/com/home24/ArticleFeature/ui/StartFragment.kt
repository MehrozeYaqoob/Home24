package com.home24.articlefeature.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.home24.articlefeature.viewmodel.ArticleViewModel

import com.home24.R
import com.home24.infrastructure.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_start.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * A simple [Fragment] subclass.
 */
class StartFragment : BaseFragment() {

    override val layoutResourceId: Int = R.layout.fragment_start
    override var displayToolbar = false

    //region overriden methods
    override fun ignite(bundle: Bundle?) {
        val navController = findNavController()
        btn_start.setOnClickListener {
            val totalArticles = edt_total_articles.text.toString().trim().toInt()
            if( totalArticles < 10 || totalArticles > 100){
                Toast.makeText(activity,R.string.invalid_article_count,Toast.LENGTH_LONG).show()
            }else{
                val bundle = bundleOf(getString(R.string.total_articles) to totalArticles)
                navController.navigate(R.id.toProductListFragment,bundle)

            }
        }
    }
    //endregion
}
