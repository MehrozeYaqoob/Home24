package com.home24.ArticleFeature.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.home24.ArticleFeature.ViewModel.ArticleViewModel
import com.home24.ArticleFeature.adapter.ArticlesAdapter
import com.home24.R
import com.home24.infrastructure.extensions.fault
import com.home24.infrastructure.extensions.observe
import com.home24.infrastructure.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_article.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ArticleFragment : BaseFragment() {

    //region Layouts
    override val layoutResourceId = R.layout.fragment_article
    //endregion
    //region Injections
    private val productViewModel: ArticleViewModel by sharedViewModel()
    private val articlesAdapter: ArticlesAdapter = ArticlesAdapter()
    private lateinit var layoutManager: LayoutManager
    private var totalArticles : Int? = 0
    //endregion

    //region Initializations
    override fun ignite(bundle: Bundle?) {

        bundle?.getInt(getString(R.string.total_articles))?.let {  totalArticles = it }

        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(ContextCompat.getDrawable(context!!, R.drawable.recycleview_gray_divider)!!)
        articlesRecyclerView.addItemDecoration(divider)

        layoutManager = LayoutManager(context!!)
        articlesRecyclerView.layoutManager = layoutManager
        PagerSnapHelper().attachToRecyclerView(articlesRecyclerView)
        articlesRecyclerView.adapter = articlesAdapter

        productViewModel.run {
            observe(articlesList) { articlesAdapter.submitList(it?: emptyList())}
            fault(failure) { handleFailure(it) }
            loadArticles(totalArticles ?: 10)
        }
    }

    override fun handleLikeDislike() {
        var counter = 0

        btn_like.setOnClickListener {
            layoutManager.canScroll = true
            productViewModel.articlesMapForReview[articlesAdapter.currentList.get(counter)!!] = 1 // liked article

            articlesRecyclerView.smoothScrollToPosition(++counter)
            productViewModel.likeArticleNumber.postValue(productViewModel.likeArticleNumber.value?.plus(1))
            productViewModel.articlesInteracted.postValue(counter)
        }

        btn_dislike.setOnClickListener {
            layoutManager.canScroll = true
            productViewModel.articlesMapForReview[articlesAdapter.currentList.get(counter)!!] = 0 // disliked article
            articlesRecyclerView.smoothScrollToPosition(++counter)
            productViewModel.articlesInteracted.postValue(counter)

        }

        btn_review.setOnClickListener {
            findNavController().navigate(R.id.toReviewFragment)
        }
    }

    override fun observeLikedArticle() {
        productViewModel.likeArticleNumber.observe(this, Observer { it ->
            updateArticleCounter(it)
        })
    }

    override fun observeReviewButton() {
        productViewModel.articlesInteracted.observe(this, Observer { it ->
            if(it == totalArticles){
                btn_review.isEnabled = true
                layoutManager.canScroll = false
                layout_footer.visibility = View.GONE
            }
        })
    }

    private fun updateArticleCounter(likedArticle : Int?){
        tvArticleCounter.text = getString(R.string.article_counter,likedArticle,totalArticles)
    }
}

class LayoutManager(context: Context, var canScroll: Boolean = false) : LinearLayoutManager(context, VERTICAL, false) {
    override fun canScrollVertically(): Boolean {
        return canScroll
    }
}


