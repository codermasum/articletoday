package com.example.helloarticles.model.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.helloarticles.ArticleApplication
import com.example.helloarticles.model.di.APIComponent
import com.example.helloarticles.model.reponse.Post
import com.example.helloarticles.model.reponse.ResponseArticles
import com.example.helloarticles.utils.Results
import retrofit2.*
import java.io.ObjectInput
import java.util.*
import javax.inject.Inject

class ArticleRepository :BaseRepository(){
    companion object {
        const val GENERAL_ERROR_CODE = 499
    }

    @Inject
    lateinit var retrofit: Retrofit
    init {
        var apiComponent : APIComponent = ArticleApplication.apiComponent
        apiComponent.inject(this)
    }

    suspend fun fetchPostInfoList(): Results<ArrayList<Post>> {
        val apikey = "8cKjN8dGwJCP3i1BxeTtlJEUazqpaG9D"
        val apiService: APIService = retrofit.create(APIService::class.java)
        var result: Results<ArrayList<Post>> = handleSuccess(arrayListOf())
        try {
            val postListInfo : Response<ResponseArticles> =  apiService.makeRequest(apikey)
            postListInfo?.let {
                it.body()?.let { articleResponse ->
                   result= handleSuccess(articleResponse.results) as Results<ArrayList<Post>>
                }
                it.errorBody()?.let { responseErrorBody ->
                    if (responseErrorBody is HttpException) {
                        responseErrorBody.response()?.code()?.let { errorCode ->
                            result = handleException(errorCode)
                        }
                    } else result = handleException(GENERAL_ERROR_CODE)
                }
            }
        } catch (error: HttpException) {
            return handleException(error.code())
        }

        return  result

    }


}