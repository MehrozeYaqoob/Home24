package com.home24.articlefeature.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.home24.articlefeature.repository.ArticleUseCase
import com.home24.data.table.Articles
import com.home24.infrastructure.platform.BaseUseCase
import com.home24.infrastructure.platform.BaseViewModel

class ArticleViewModel(androidApplication: Application, private val articleUseCase: ArticleUseCase) : BaseViewModel(androidApplication) {

    var articlesList = MutableLiveData<List<Articles>>()
    var likeArticleNumber: MutableLiveData<Int> = MutableLiveData()
    var articlesInteracted: MutableLiveData<Int> = MutableLiveData()

    init {
        likeArticleNumber.postValue(0)
    }

    /**
     * This will trigger usecase's baseclass invoke method, which will initial nework request to fetch articles
     * @param totalArticles Int
     */
    fun loadArticles(totalArticles: Int) = articleUseCase(BaseUseCase.None(), totalArticles) {
        it.either(::handleFailure) {
            articlesList.postValue(it._embedded.articles)
        }
    }
}