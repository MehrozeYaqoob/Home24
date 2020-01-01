package com.home24.ArticleFeature.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.home24.ArticleFeature.paging.ArticleSourceFactory
import com.home24.ArticleFeature.repository.ArticleUseCase
import com.home24.data.table.ArticleResponse
import com.home24.data.table.Articles
import com.home24.infrastructure.platform.BaseUseCase
import com.home24.infrastructure.platform.BaseViewModel

class ArticleViewModel(androidApplication: Application, private val articleUseCase: ArticleUseCase, articleSourceFactory: ArticleSourceFactory)
    : BaseViewModel(androidApplication) {

    var articlesList: LiveData<PagedList<Articles>>
    var likeArticleNumber : MutableLiveData<Int> = MutableLiveData()
    var articlesInterated : MutableLiveData<Int> = MutableLiveData()

    init {
        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(20)
            .setPageSize(10).build()
        articlesList = LivePagedListBuilder(articleSourceFactory, pagedListConfig).build()

        likeArticleNumber.postValue(0)
    }

}