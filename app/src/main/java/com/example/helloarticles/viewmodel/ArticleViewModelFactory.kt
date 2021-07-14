package com.example.helloarticles.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.helloarticles.ArticleApplication
import com.example.helloarticles.model.di.APIComponent
import com.example.helloarticles.model.di.APIModule
import com.example.helloarticles.model.di.DaggerAPIComponent
import com.example.helloarticles.model.network.APIURL
import com.example.helloarticles.model.network.ArticleRepository
import javax.inject.Inject

class ArticleViewModelFactory : ViewModelProvider.Factory {
    @Inject
    lateinit var articleRepository: ArticleRepository

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var apiComponent : APIComponent = ArticleApplication.apiComponent
        apiComponent.inject(this)
        if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            return ArticleViewModel(articleRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}