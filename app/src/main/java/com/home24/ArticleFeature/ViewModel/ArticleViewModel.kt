package com.home24.ArticleFeature.ViewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.home24.ArticleFeature.repository.ArticleUseCase
import com.home24.data.table.Articles
import com.home24.infrastructure.platform.BaseUseCase
import com.home24.infrastructure.platform.BaseViewModel

class ArticleViewModel(androidApplication: Application, private val articleUseCase: ArticleUseCase) : BaseViewModel(androidApplication) {

    var articlesList = MutableLiveData<List<Articles>>()
    var likeArticleNumber: MutableLiveData<Int> = MutableLiveData()
    var articlesInteracted: MutableLiveData<Int> = MutableLiveData()
    val articlesMapForReview = mutableMapOf<Articles, Int>()

    init {
        likeArticleNumber.postValue(0)
    }

    fun loadArticles(totalArticles: Int) = articleUseCase(BaseUseCase.None(), totalArticles) {
        it.either(::handleFailure) {
            articlesList.postValue(it._embedded.articles)
        }
    }

    fun clearList(){
        articlesList.postValue(emptyList())
    }

}