package com.home24.di.ArticleFeature

import com.home24.ArticleFeature.repository.ArticleUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val articleUseCaseModule = module {
    single{ ArticleUseCase(get(), get(), Dispatchers.Main) }
}