package com.home24.ArticleFeature.ViewModel

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.home24.ArticleFeature.repository.ArticleRepository
import com.home24.ArticleFeature.repository.ArticleUseCase
import com.home24.data.table.ArticleResponse
import com.home24.data.table.Articles
import com.home24.infrastructure.functional.Either
import com.home24.infrastructure.platform.BaseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArticleViewModelTest{
    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var successObserver: Observer<PagedList<Articles>>

    @Mock
    private lateinit var none: BaseUseCase.None

    @JvmField
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fetchArticles_SUCCESS()= runBlocking {

        val articleResponse = Mockito.mock(ArticleResponse::class.java)
        val articlePageList = mockPagedList(emptyList<Articles>())

        val result = Either.Right(articleResponse)

        val articleRepository = Mockito.mock(ArticleRepository::class.java)
        Mockito.`when`(articleRepository.fetchArticles()).thenReturn(result)
        val articleUseCase = ArticleUseCase(CoroutineScope(Dispatchers.Unconfined + Job()), articleRepository, Dispatchers.Unconfined)
        val articleViewModel = ArticleViewModel(application, articleUseCase)

        articleViewModel.articlesList = MutableLiveData<PagedList<Articles>>()

        Mockito.`when`(articleUseCase.run(none)).thenReturn(result)
        articleViewModel.articlesList.observeForever(successObserver)

        articleViewModel.buildPageList(0)
        Mockito.verify(successObserver).onChanged(articlePageList)
    }

    fun <T> mockPagedList(list: List<T>): PagedList<T> {
        val pagedList = Mockito.mock(PagedList::class.java) as PagedList<T>
        Mockito.`when`(pagedList.get(ArgumentMatchers.anyInt())).then { invocation ->
            val index = invocation.arguments.first() as Int
            list[index]
        }
        Mockito.`when`(pagedList.size).thenReturn(list.size)
        return pagedList
    }

}