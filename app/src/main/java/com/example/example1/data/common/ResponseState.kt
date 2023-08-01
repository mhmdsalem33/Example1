package com.example.example1.data.common

sealed class ResponseState<T>(
    val data     : T?       = null ,
    val message  : String ? = null ,
    val errors   : T?       = null
)
{
    class Success<T>(data : T)        :ResponseState<T>(data = data)
    class Error<T>(message: String)   :ResponseState<T>(message = message)
    class ErrorList<T>(errors: T)     :ResponseState<T>(errors = errors)
    class Loading<T>      : ResponseState<T>()
    class Idle<T>         : ResponseState<T>()
    class EmptyData<T>    : ResponseState<T>()
    class NullData<T>     : ResponseState<T>()
    class Unauthorized<T> : ResponseState<T>()
}

