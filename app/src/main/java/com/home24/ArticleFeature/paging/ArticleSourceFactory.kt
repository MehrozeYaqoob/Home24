package com.home24.ArticleFeature.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.home24.ArticleFeature.repository.ArticleUseCase
import com.home24.data.table.Articles


/**
 * This class shields the android RV from the mechanism by which pages are extracted from the backend.
 *
 * This class is a wrapper over the behaviour through which we would like to perform pagination.
 * The actual logic of obtaining pages is in the class EstablishmentDataSource
 */
class ArticleSourceFactory(private val articleUseCase: ArticleUseCase) :
    DataSource.Factory<Int, Articles>() {

    var dataSourceLiveData: MutableLiveData<ArticleDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, Articles> {
        val dataSourceClass = ArticleDataSource(articleUseCase )
        dataSourceLiveData.postValue(dataSourceClass)
        return dataSourceClass
    }

}