package com.home24.ArticleFeature.repository

import com.home24.data.table.ArticleResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleServices {
    @GET("api/v2.0/categories/100/articles?appDomain=1&locale=de_DE&")
     fun fetchArticles(@Query("limit") limit: Int): Call<ArticleResponse>

}