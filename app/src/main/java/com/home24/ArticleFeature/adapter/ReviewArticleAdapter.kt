package com.home24.articlefeature.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.home24.R
import com.home24.data.table.Articles
import com.home24.databinding.ItemReviewArticleBinding
import com.home24.infrastructure.extensions.dataBind


class ReviewArticleAdapter : ListAdapter<Articles, ReviewArticleAdapter.ViewHolder>(Articles.DIFF_CALLBACK) {

    lateinit var recyclerView: RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.dataBind(R.layout.item_review_article))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(private val itemBinding:ItemReviewArticleBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Articles?) {
            if(recyclerView.layoutManager is GridLayoutManager)
                itemBinding.textView.visibility = View.GONE
            else
                itemBinding.textView.visibility = View.VISIBLE

            itemBinding.article = item
            itemBinding.executePendingBindings()
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this@ReviewArticleAdapter.recyclerView = recyclerView
    }

}