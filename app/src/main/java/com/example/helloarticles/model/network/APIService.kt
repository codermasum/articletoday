package com.example.helloarticles.model.network

import com.example.helloarticles.model.reponse.ResponseArticles
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("svc/mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun makeRequest(
        @Query("api-key")  api_key :String
    ): Response<ResponseArticles>
}
