package com.example.helloarticles

import android.app.Application
import android.content.Context
import com.example.helloarticles.model.network.APIURL
import com.example.helloarticles.model.di.APIComponent
import com.example.helloarticles.model.di.APIModule
import com.example.helloarticles.model.di.DaggerAPIComponent

class ArticleApplication :Application() {

    companion object {
        var ctx: Context? = null
        lateinit var apiComponent: APIComponent
    }
    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        apiComponent = initDaggerComponent()

    }

    fun getMyComponent(): APIComponent {
        return apiComponent
    }

    fun initDaggerComponent(): APIComponent {
        apiComponent = DaggerAPIComponent
            .builder()
            .aPIModule(APIModule(APIURL.BASE_URL))
            .build()
        return  apiComponent

    }
}