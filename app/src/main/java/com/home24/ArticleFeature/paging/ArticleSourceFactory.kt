package com.home24.ArticleFeature.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.home24.ArticleFeature.repository.ArticleUseCase
import com.home24.data.table.Articles


class ArticleSourceFactory(private val articleUseCase: ArticleUseCase) : DataSource.Factory<Int, Articles>() {

    private lateinit var dataSourceClass: ArticleDataSource
    var dataSourceLiveData: MutableLiveData<ArticleDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Articles> {
        dataSourceClass = ArticleDataSource(articleUseCase)
        dataSourceLiveData.postValue(dataSourceClass)
        return dataSourceClass
    }

    fun setTotalArticlesToLoad(totalArticlesToLoad : Int){
        dataSourceClass.setTotalArticlesToLoad(totalArticlesToLoad)
    }
}