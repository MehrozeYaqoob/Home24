package com.home24.ArticleFeature.repository
import com.home24.data.table.ArticleResponse
import com.home24.infrastructure.platform.BaseUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope

class ArticleUseCase(ioScope: CoroutineScope, private val articleRepository: ArticleRepository, main : CoroutineDispatcher):
    BaseUseCase<ArticleResponse, BaseUseCase.None>(ioScope, main) {
    override suspend fun run(param: None)=articleRepository.fetchArticles()

}