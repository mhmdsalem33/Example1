package com.example.example1.domain.repository

import com.example.example1.data.common.ResponseState
import com.example.example1.data.network.MealServices
import com.example.example1.domain.models.CategoriesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepository @Inject constructor(private val mealServices: MealServices) {
    suspend fun getCategoriesRemoteData(): Flow<ResponseState<CategoriesResponse>> =
        flow {
            emit(ResponseState.Loading())
            val response = mealServices.getCategoriesRemoteData()
            if (response.isSuccessful) {
                response.body()?.let {
                     emit(ResponseState.Success(it))
                } ?: emit(ResponseState.NullData())
            } else {
                emit(ResponseState.Error(response.message() ?: ""))
            }
        }
}