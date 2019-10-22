package com.ddapps.booked.data.network


open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(message: String?): Resource<T> {
        return Resource.error(message, null)
    }
}
