package com.home24.ArticleFeature.repository

import com.home24.data.table.ArticleResponse
import com.home24.di.ArticleFeature.articleRepositoryModule
import com.home24.infrastructure.functional.Either
import com.home24.infrastructure.networks.networkModule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ArticleRepositoryTest: AutoCloseKoinTest() {

    val articleServices by inject<ArticleServices>()

    @Test
    fun fetchArticles_SUCCESS() {

        val serviceRepository = ArticleRepository(articleServices)
        val serviceCall: Call<ArticleResponse> = mock()
        val retrofitResponse: Response<ArticleResponse> = mock()
        val responseBody: ArticleResponse = mock()

        Mockito.`when`(serviceCall.execute()).thenReturn(retrofitResponse)
        Mockito.`when`(retrofitResponse.isSuccessful).thenReturn(true)
        Mockito.`when`(retrofitResponse.body()).thenReturn(responseBody)

        Mockito.`when`(articleServices.fetchArticles()).thenReturn(serviceCall)
        val response = serviceRepository.fetchArticles()
        assertTrue(response is Either.Right<*>)
    }

    @Test
    fun fetchArticles_ERROR() {
        val serviceRepository = ArticleRepository(articleServices)
        val serviceCall: Call<ArticleResponse> = mock()
        val retrofitResponse: Response<ArticleResponse> = mock()
        val responseBody: ArticleResponse = mock()

        Mockito.`when`(serviceCall.execute()).thenReturn(retrofitResponse)
        Mockito.`when`(retrofitResponse.isSuccessful).thenReturn(false)

        Mockito.`when`(articleServices.fetchArticles()).thenReturn(serviceCall)
        val response = serviceRepository.fetchArticles()
        assertTrue(response is Either.Left<*>)
    }

    @Before
    fun before() {
        startKoin {
            modules(listOf(
                networkModule,
                articleRepositoryModule
            ))
        }
        declareMock<ArticleServices>()
    }

}
inline fun <reified T : Any> mock() = Mockito.mock(T::class.java)