package com.example.example1.domain.models


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CategoriesResponse(
    @SerializedName("categories")
    val categories: List<Category?>? = ArrayList()
)