package com.example.lawbeatapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lawbeatapplication.dataclass.CategoryResponse
import com.example.lawbeatapplication.repository.NewsRepository

class CategoryViewModel(private val repository: NewsRepository): ViewModel() {
    var categoryData = MutableLiveData<CategoryResponse>()
    suspend fun getCategory() {
        val response = repository.getCategory()
        categoryData.postValue(response.body())

    }
}