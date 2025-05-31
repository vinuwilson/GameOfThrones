package com.createfuture.takehome.domain.usecase

import com.createfuture.takehome.data.models.ApiCharacterDto
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Inject

class GetCharactersList @Inject constructor() {

    var retrofit: Retrofit =
        Retrofit.Builder().baseUrl("https://yj8ke8qonl.execute-api.eu-west-1.amazonaws.com")
            .addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create())
            ).client(OkHttpClient.Builder().build()).build()
    var service: Service = retrofit.create(Service::class.java)

    suspend fun getCharactersList(): List<ApiCharacterDto> {
        return service.getCharacters("Bearer 754t!si@glcE2qmOFEcN")
    }

}


interface Service {
    @GET("/characters")
    suspend fun getCharacters(@Header("Authorization") token: String): List<ApiCharacterDto>
}