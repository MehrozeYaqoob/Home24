package com.home24.ArticleFeature.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.home24.ArticleFeature.paging.ArticleSourceFactory
import com.home24.ArticleFeature.repository.ArticleUseCase
import com.home24.data.table.Articles
import com.home24.infrastructure.platform.BaseViewModel

class ArticleViewModel(androidApplication: Application, private val articleUseCase: ArticleUseCase)
    : BaseViewModel(androidApplication) {

    lateinit var articlesList: LiveData<PagedList<Articles>>
    var likeArticleNumber : MutableLiveData<Int> = MutableLiveData()
    var articlesInteracted : MutableLiveData<Int> = MutableLiveData()

    init {
        likeArticleNumber.postValue(0)
    }

    fun buildPageList(totalArticlesToLoad : Int){
        val articleSourceFactory = ArticleSourceFactory(articleUseCase, totalArticlesToLoad)

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPrefetchDistance(20)
            .setPageSize(10).build()
        articlesList = LivePagedListBuilder(articleSourceFactory, pagedListConfig).build()

    }
}