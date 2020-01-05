package com.home24.di.ArticleFeature

import com.home24.ArticleFeature.ViewModel.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleViewModelModule = module {
    viewModel { ArticleViewModel(get(), get()) }
}