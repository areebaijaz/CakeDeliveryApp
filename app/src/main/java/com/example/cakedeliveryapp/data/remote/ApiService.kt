package com.example.cakedeliveryapp.data.remote

import com.example.cakedeliveryapp.data.model.CakeResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("cakes")
     suspend fun getCakes() : Response<CakeResponse>
}