package com.example.lawbeatapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.lawbeatapplication.dataclass.NewsResponse
import com.example.lawbeatapplication.dataclass.PagingResource
import com.example.lawbeatapplication.dataclass.Resource
import com.example.lawbeatapplication.repository.NewsRepository
import retrofit2.Response

class NewsViewModel(private val repository: NewsRepository, val tid:Int) : ViewModel() {

    val userData: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()

    suspend fun getNews(tid: Int) {

        val response = repository.getNews(tid)

        userData.postValue(handleUserResponse(response))
    }

    private fun handleUserResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    val flow = Pager(PagingConfig(pageSize = 10)) {
        PagingResource(tid)
    }.flow
        .cachedIn(viewModelScope)
}