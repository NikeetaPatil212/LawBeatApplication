package com.example.lawbeatapplication.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lawbeatapplication.repository.NewsRepository
import com.example.lawbeatapplication.viewmodel.NewsViewModel

class NewsFactory(private val repository: NewsRepository, val tid:Int): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(repository,tid) as T
    }
}