package com.home24.di.articlefeature

import com.home24.articlefeature.repository.ArticleUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val articleUseCaseModule = module {
    single{ ArticleUseCase(get(), get(), Dispatchers.Main) }
}