package com.home24.articlefeature.repository

import com.home24.data.table.ArticleResponse
import com.home24.infrastructure.exception.Failure
import com.home24.infrastructure.functional.Either

interface ArticleOperations {
    fun fetchArticles(limit : Int): Either<Failure, ArticleResponse>
}