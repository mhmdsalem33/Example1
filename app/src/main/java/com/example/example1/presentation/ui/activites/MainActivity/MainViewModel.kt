package com.example.example1.presentation.ui.activites.MainActivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example1.data.common.ResponseState
import com.example.example1.domain.models.CategoriesResponse
import com.example.example1.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor( private val homeRepository: HomeRepository ) : ViewModel() {


    private val _getCategoriesResponse = MutableStateFlow<ResponseState<CategoriesResponse>>(ResponseState.Idle())
    val getCategoriesResponse  = _getCategoriesResponse.asStateFlow()

    init {
        getCategoriesRemoteData()
    }

    private fun getCategoriesRemoteData() = viewModelScope.launch(Dispatchers.IO){
        homeRepository.getCategoriesRemoteData().collect{
            _getCategoriesResponse.emit(it)
        }
    }

}