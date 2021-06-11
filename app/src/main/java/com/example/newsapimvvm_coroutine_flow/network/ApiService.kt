package com.example.newsapimvvm_coroutine_flow.network

import com.example.newsapimvvm_coroutine_flow.model.ResponseNews
import retrofit2.http.GET

interface ApiService {


    @GET("everything?q=tesla&from=2021-05-11&sortBy=publishedAt&apiKey=d32dd7d06e3b40b8ab47fb94dfbe8ac4")
    suspend fun getNews():ResponseNews
}