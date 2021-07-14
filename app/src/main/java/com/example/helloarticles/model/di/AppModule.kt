package com.example.helloarticles.model.di

import com.example.helloarticles.ArticleApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule constructor(articleApplication: ArticleApplication){

    var articleApplication: ArticleApplication

    init {
        this.articleApplication = articleApplication
    }

    @Provides
    fun provideMyRetroApplication(): ArticleApplication {
        return articleApplication
    }
}

