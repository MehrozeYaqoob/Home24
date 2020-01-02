package com.home24.ArticleFeature.repository

import com.home24.infrastructure.extensions.requestBlocking

class ArticleRepository(private val accountServices: ArticleServices) : ArticleOperations {
    override fun fetchArticles() = accountServices.fetchArticles().requestBlocking()
}