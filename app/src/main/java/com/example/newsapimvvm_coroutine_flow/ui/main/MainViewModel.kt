package com.example.newsapimvvm_coroutine_flow.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapimvvm_coroutine_flow.data.NewsRepository
import com.example.newsapimvvm_coroutine_flow.model.ArticlesItem
import com.example.newsapimvvm_coroutine_flow.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repo: NewsRepository) : ViewModel() {

    var _responseNews = MutableLiveData<List<ArticlesItem?>?>()
    val responseNews: MutableLiveData<List<ArticlesItem?>?> = _responseNews

    var _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable>? = _error

    val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>? = _loading


    init {

        this.getNews()
    }

    private  fun getNews() {

        viewModelScope.launch {
            repo.newsApi().onStart {

                _loading.value = true
            }
                .onCompletion {
                    _loading.value = false
                }
                .collect {
                    when (it) {

                        is ApiResult.Success -> {
                            _responseNews.postValue(it.data)
                        }
                        is ApiResult.Error -> {
                            _error.postValue(it.throwable)
                        }
                    }
                }
        }
    }
}