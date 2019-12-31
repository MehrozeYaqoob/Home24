package com.home24.di.ArticleFeature

import com.home24.ArticleFeature.repository.ArticleRepository
import com.home24.ArticleFeature.repository.ArticleServices
import org.koin.dsl.module
import retrofit2.Retrofit

val articleRepositoryModule = module {

    single{get<Retrofit>().create(ArticleServices::class.java)}
    single{ ArticleRepository(get()) }
}