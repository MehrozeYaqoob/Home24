package com.home24.ArticleFeature.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.home24.ArticleFeature.ViewModel.ArticleViewModel

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
    private val productViewModel: ArticleViewModel by sharedViewModel()

    override fun ignite(bundle: Bundle?) {
        val navController = findNavController()
        productViewModel.clearList()
        btn_start.setOnClickListener {
            val totalArticles = edt_total_articles.text.toString().toInt()
            if( totalArticles < 1 || totalArticles > 100){
                Toast.makeText(activity,R.string.invalid_article_count,Toast.LENGTH_LONG).show()
            }else{
                val bundle = bundleOf(getString(R.string.total_articles) to totalArticles)
                navController.navigate(R.id.toProductListFragment,bundle)
            }
        }
    }

    override fun handleLikeDislike() {
    }

    override fun observeLikedArticle() {
    }

    override fun observeReviewButton() {
    }
}
