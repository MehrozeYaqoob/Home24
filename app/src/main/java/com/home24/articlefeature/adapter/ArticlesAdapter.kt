package com.home24.articlefeature.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.home24.R
import com.home24.data.table.Articles
import com.home24.databinding.ItemArticleBinding
import com.home24.infrastructure.extensions.dataBind

class ArticlesAdapter : ListAdapter<Articles, ArticlesAdapter.ViewHolder>(Articles.DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.dataBind(R.layout.item_article))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    inner class ViewHolder(private val itemBinding:ItemArticleBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: Articles?) {
            itemBinding.article = item
            itemBinding.executePendingBindings()
        }
    }

}