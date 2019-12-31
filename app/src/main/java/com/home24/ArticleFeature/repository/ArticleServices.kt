package com.home24.ArticleFeature.repository

import com.home24.BuildConfig
import com.home24.data.table.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET

interface ArticleServices {
    @GET("api/v2.0/categories/100/articles?appDomain=1&locale=de_DE&limit=10")
     fun fetchArticles(): Call<ArticleResponse>

}