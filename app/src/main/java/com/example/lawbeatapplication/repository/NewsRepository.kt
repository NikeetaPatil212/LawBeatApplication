package com.example.lawbeatapplication.repository

import com.example.lawbeatapplication.api.ApiInterface

class NewsRepository {
    private val retrofitService by lazy {
        ApiInterface.getInstance().create(ApiInterface::class.java)
    }

    suspend fun getCategory() = retrofitService.getCategory()
    suspend fun getNews(tid: Int) = retrofitService.getNews(10, 1, tid)
}