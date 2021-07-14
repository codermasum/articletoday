package com.example.helloarticles.model.network

import com.example.helloarticles.ArticleApplication
import com.example.helloarticles.model.di.APIComponent
import com.example.helloarticles.model.reponse.Post
import com.example.helloarticles.model.reponse.ResponseArticles
import com.example.helloarticles.utils.Results
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
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
        /**
         * Note : API key should be stored in secure way
         * e.g. with NDK in C/C++ code which is hard for reverse engineering
         * For time convenient I have used directly as String value.
         */
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