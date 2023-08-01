package com.example.example1.data.network

import com.example.example1.domain.models.CategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface MealServices {
    @GET("categories.php")
    suspend fun getCategoriesRemoteData() : Response<CategoriesResponse>
}