package com.home24.di.articlefeature

import com.home24.articlefeature.repository.ArticleRepository
import com.home24.articlefeature.repository.ArticleServices
import org.koin.dsl.module
import retrofit2.Retrofit

val articleRepositoryModule = module {

    single{get<Retrofit>().create(ArticleServices::class.java)}
    single{ ArticleRepository(get()) }
}