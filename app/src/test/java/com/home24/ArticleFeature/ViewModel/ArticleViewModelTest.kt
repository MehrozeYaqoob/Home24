package com.home24.articlefeature.viewmodel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.home24.articlefeature.repository.ArticleRepository
import com.home24.articlefeature.repository.ArticleUseCase
import com.home24.data.table.ArticleResponse
import com.home24.data.table.Articles
import com.home24.data.table.Embedded
import com.home24.infrastructure.exception.Failure
import com.home24.infrastructure.functional.Either
import com.home24.infrastructure.platform.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest{
    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var successObserver: Observer<List<Articles>>

    @Mock
    private lateinit var failureObserver: Observer<Failure>

    @Mock
    private lateinit var none: BaseUseCase.None

    @JvmField
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fetchArticles_SUCCESS()= runBlocking {

        val list = emptyList<Articles>()
        val articleResponse = Mockito.mock(ArticleResponse::class.java)
        val _Embedd = Mockito.mock(Embedded::class.java)

        val result = Either.Right(articleResponse)

        val articleRepository = Mockito.mock(ArticleRepository::class.java)
        Mockito.`when`(articleRepository.fetchArticles(anyInt())).thenReturn(result)
        val articleUseCase = ArticleUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), articleRepository, Dispatchers.Unconfined)
        val articleViewModel = ArticleViewModel(application, articleUseCase)

        Mockito.`when`(articleUseCase.run(none,anyInt())).thenReturn(result)
        articleViewModel.articlesList.observeForever(successObserver)
        Mockito.`when`(articleResponse._embedded).thenReturn(_Embedd)

        articleViewModel.loadArticles(anyInt())
        Mockito.verify(successObserver).onChanged(list)
    }

}