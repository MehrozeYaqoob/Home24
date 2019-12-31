package com.home24.infrastructure.platform
import androidx.multidex.MultiDexApplication
import com.home24.BuildConfig
import com.home24.di.ArticleFeature.articleRepositoryModule
import com.home24.di.ArticleFeature.articleUseCaseModule
import com.home24.di.ArticleFeature.articleViewModelModule
//import com.home24.di.ArticleFeature.getProductRepositoryModule
//import com.home24.di.ArticleFeature.getProductUseCaseModule
//import com.home24.di.ArticleFeature.getProductViewModelModule
import com.home24.infrastructure.networks.networkModule
import com.mobiquity.infrastructure.platform.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class Home24Application : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG)
                androidLogger(Level.DEBUG)
            androidContext(this@Home24Application)
            modules(
                listOf(
                    platformModule,
                    networkModule,
                    articleRepositoryModule,
                    articleViewModelModule,
                    articleUseCaseModule
                )
            )
        }

    }


}

