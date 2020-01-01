package com.home24.ArticleFeature.ui

import android.content.Context
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.home24.ArticleFeature.ViewModel.ArticleViewModel
import com.home24.ArticleFeature.paging.ArticlesAdapter
import com.home24.R
import com.home24.infrastructure.extensions.fault
import com.home24.infrastructure.extensions.observe
import com.home24.infrastructure.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleFragment : BaseFragment() {

    //region Layouts
    override val layoutResourceId = R.layout.fragment_article
    //endregion
    //region Injections
    private val productViewModel: ArticleViewModel by viewModel()
    private val articlesAdapter: ArticlesAdapter = ArticlesAdapter()
    private lateinit var layoutManager: LayoutManager
    private var totalArticles : Int? = 0
    //endregion

    //region Initializations
    override fun ignite(bundle: Bundle?) {

        bundle?.getInt("TotalArticles")?.let {  totalArticles = it}
        tvArticleCounter.text = "${productViewModel.likeArticleNumber}/$totalArticles"

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.recycleview_gray_divider)!!)
        articlesRecyclerView.addItemDecoration(divider)

        layoutManager = LayoutManager(context!!)
        articlesRecyclerView.layoutManager = layoutManager
        PagerSnapHelper().attachToRecyclerView(articlesRecyclerView)
        articlesRecyclerView.adapter = articlesAdapter

        productViewModel.run {
            observe(articlesList)
            {
                articlesAdapter.submitList(it)

            }
            fault(failure) {
                handleFailure(it)
            }
        }
    }

    override fun handleLikeDislike() {
        var counter = 0
        btn_like.setOnClickListener {
            layoutManager.canScroll = true
            articlesRecyclerView.smoothScrollToPosition(++counter)
            productViewModel.likeArticleNumber.postValue(productViewModel.likeArticleNumber.value?.plus(1))
        }

        btn_dislike.setOnClickListener {
            layoutManager.canScroll = true
            articlesRecyclerView.smoothScrollToPosition(++counter)
        }
    }

    override fun observeLikedArticle() {
        productViewModel.likeArticleNumber.observe(this, Observer { it ->
            updateArticleCounter(it)
        })
    }

    private fun updateArticleCounter(likedArticle : Int?){
        tvArticleCounter.text = "$likedArticle/$totalArticles"
    }
}

class LayoutManager(context: Context, var canScroll: Boolean = false) : LinearLayoutManager(context, VERTICAL, false) {
    override fun canScrollVertically(): Boolean {
        return canScroll
    }
}


