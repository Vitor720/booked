package com.ddapps.booked.data.network

import com.ddapps.booked.util.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    factory { provideBookApi(get()) }
    single { provideRetrofit() }
    factory { ResponseHandler()  }

}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()
}


fun provideBookApi(retrofit: Retrofit): ApiServiceInterface {
    return retrofit.create(ApiServiceInterface::class.java)
}

