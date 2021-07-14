package com.example.helloarticles.model.di

import com.example.helloarticles.model.network.ArticleRepository
import com.example.helloarticles.view.ArticlesActivity
import com.example.helloarticles.viewmodel.ArticleViewModel
import com.example.helloarticles.viewmodel.ArticleViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, APIModule::class])
interface APIComponent {
    fun inject(articleRepository: ArticleRepository)
    fun inject(articleViewModel: ArticleViewModel)
    fun inject(articleActivity: ArticlesActivity)
    fun inject(articleViewModelFactory: ArticleViewModelFactory)
}
