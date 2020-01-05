package com.home24.ArticleFeature.ui

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.home24.ArticleFeature.ViewModel.ArticleViewModel
import com.home24.ArticleFeature.paging.ArticlesAdapter
import com.home24.ArticleFeature.paging.ReviewArticleAdapter
import com.home24.R
import com.home24.data.table.Articles
import com.home24.infrastructure.extensions.fault
import com.home24.infrastructure.extensions.observe
import com.home24.infrastructure.platform.BaseFragment
import kotlinx.android.synthetic.main.fragment_review.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReviewFragment : BaseFragment() {

    //region Layouts
    override val layoutResourceId = R.layout.fragment_review
    //endregion
    //region Injections
    private val productViewModel: ArticleViewModel by sharedViewModel()
    private val articlesAdapter: ReviewArticleAdapter = ReviewArticleAdapter()
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager
    //endregion

    //region Initializations
    override fun ignite(bundle: Bundle?) {
        setHasOptionsMenu(true)
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        divider.setDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.recycleview_gray_divider
            )!!
        )
        articlesRecyclerView.addItemDecoration(divider)

        gridLayoutManager = GridLayoutManager(context, 2)
        linearLayoutManager = LinearLayoutManager(context)

        articlesRecyclerView.layoutManager = gridLayoutManager
        PagerSnapHelper().attachToRecyclerView(articlesRecyclerView)
        articlesRecyclerView.adapter = articlesAdapter
        articlesAdapter.submitList(productViewModel.articlesMapForReview.keys.toList())
    }

    override fun handleLikeDislike() {

    }

    override fun observeLikedArticle() {

    }

    override fun observeReviewButton() {


    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.review_article_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_change_list -> articlesRecyclerView.layoutManager =
                if (articlesRecyclerView.layoutManager is GridLayoutManager) linearLayoutManager else gridLayoutManager
        }
        return super.onOptionsItemSelected(item)
    }
}



