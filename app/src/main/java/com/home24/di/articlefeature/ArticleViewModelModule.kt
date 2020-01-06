package com.home24.di.articlefeature

import com.home24.articlefeature.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleViewModelModule = module {
    viewModel { ArticleViewModel(get(), get()) }
}