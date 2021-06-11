package com.example.newsapimvvm_coroutine_flow.data

import com.example.newsapimvvm_coroutine_flow.model.ArticlesItem
import com.example.newsapimvvm_coroutine_flow.model.ResponseNews
import com.example.newsapimvvm_coroutine_flow.network.ApiResult
import com.example.newsapimvvm_coroutine_flow.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepository @Inject constructor(val api: ApiService) {

    suspend fun newsApi(): Flow<ApiResult<List<ArticlesItem?>?>> {

        return flow {

            try {
                val dataNews = api.getNews().articles
                emit(ApiResult.Success(dataNews))
            }catch (e : Throwable){
                emit(ApiResult.Error(e))
            }


        }.flowOn(Dispatchers.IO)


    }

}