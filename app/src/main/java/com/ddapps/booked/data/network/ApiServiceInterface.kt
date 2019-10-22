package com.ddapps.booked.data.network

import com.ddapps.booked.models.Book
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("products.json")
    suspend fun loadBooks(): MutableList<Book>
}

