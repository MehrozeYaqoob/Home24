package com.home24.ArticleFeature.paging

import android.widget.Toast
import androidx.paging.PageKeyedDataSource
import com.home24.ArticleFeature.repository.ArticleUseCase
import com.home24.data.table.Articles
import com.home24.infrastructure.exception.Failure
import com.home24.infrastructure.platform.BaseUseCase

class ArticleDataSource(private val articleUseCase: ArticleUseCase) : PageKeyedDataSource<Int, Articles>() {

    private var pageNumber = 1
    private var totalArticlesToLoad = 0

    override fun loadInitial(params: LoadInitialParams<Int>,callback: LoadInitialCallback<Int, Articles>) {

        articleUseCase.invoke(BaseUseCase.None()) {
            pageNumber++
            it.either(::handleFailure) {
                callback.onResult(it._embedded.articles, 0, it.articlesCount, 1, pageNumber)
            }
        }
    }

    private fun handleFailure(failure: Failure) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
        articleUseCase.invoke(BaseUseCase.None()) {
            pageNumber++
            it.either(::handleFailure) {
                callback.onResult(it._embedded.articles, params.key + 1)

            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Articles>) {
    }

    fun setTotalArticlesToLoad(totalArticlesToLoad : Int){
        this.totalArticlesToLoad = totalArticlesToLoad
    }
}