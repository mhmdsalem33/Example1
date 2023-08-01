package com.example.example1.presentation.ui.activites.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.example1.data.common.ResponseState
import com.example.example1.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val mainMvvm : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observeCategoriesRemoteData()


    }

    private fun observeCategoriesRemoteData() {
        lifecycleScope.launchWhenStarted {
            mainMvvm.getCategoriesResponse.collect{
               when(it){
                   is ResponseState.Loading -> {
                       Log.d("testApp" , "categories is loading")
                   }
                   is ResponseState.Success -> {
                       val response = it.data?.categories
                       Log.d("testApp" , "categories is success $response")
                   }
                   is ResponseState.Error   -> {
                       Log.d("testApp" , "categories is error ${it.message}")
                   }
                   is ResponseState.NullData -> {
                       Log.d("testApp" , "null data")
                   }
                   else -> Unit
               }
            }
        }
    }
}