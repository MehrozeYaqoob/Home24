package com.home24.articlefeature.repository

import com.home24.infrastructure.extensions.requestBlocking

class ArticleRepository(private val accountServices: ArticleServices) : ArticleOperations {
    override fun fetchArticles(limit: Int) = accountServices.fetchArticles(limit).requestBlocking()
}