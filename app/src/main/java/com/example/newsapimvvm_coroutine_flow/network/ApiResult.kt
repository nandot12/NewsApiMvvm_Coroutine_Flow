package com.example.newsapimvvm_coroutine_flow.network

open class ApiResult<out R> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val throwable: Throwable) : ApiResult<Nothing>()
}