package com.home24.articlefeature.repository

import com.home24.data.table.ArticleResponse
import com.home24.infrastructure.exception.Failure
import com.home24.infrastructure.functional.Either
import com.home24.infrastructure.platform.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleUseCaseTest{

    @Mock
    private lateinit var articleRepository: ArticleRepository

    @Mock
    private lateinit var none: BaseUseCase.None

    @Test
    fun run_SUCCESS() = runBlocking {
        val articleResponse: ArticleResponse = mock()
        val result: Either.Right<ArticleResponse> = mock()
        Mockito.`when`(result.b).thenReturn(articleResponse)

        Mockito.`when`(articleRepository.fetchArticles(anyInt())).thenReturn(result)
        val articleUseCase = ArticleUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), articleRepository, Dispatchers.Unconfined)
        Mockito.`when`(articleUseCase.run(none, anyInt())).thenReturn(result)

        articleUseCase.invoke(none, anyInt()){
            assertEquals(it, result)
            assertNotNull(result.b)
        }

    }

    @Test
    fun run_Error() = runBlocking {
        val failure = Mockito.mock(Failure::class.java)
        val result: Either.Left<Failure> = mock()
        Mockito.`when`(result.a).thenReturn(failure)

        Mockito.`when`(articleRepository.fetchArticles(anyInt())).thenReturn(result)
        val articleUseCase = ArticleUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), articleRepository, Dispatchers.Unconfined)
        Mockito.`when`(articleUseCase.run(none,anyInt())).thenReturn(result)

        articleUseCase.invoke(none,anyInt()){
            assertEquals(it, result)
            assertNotNull(result.a)
        }

    }
}