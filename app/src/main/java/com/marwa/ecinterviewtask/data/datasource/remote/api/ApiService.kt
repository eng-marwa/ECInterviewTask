package com.marwa.ecinterviewtask.data.datasource.remote.api


import com.marwa.ecinterviewtask.data.model.CompetitionsResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiService {

    companion object {
        fun createApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("competitions")
    suspend fun getCompetitions(): Response<CompetitionsResponse>


}