package com.marwa.ecinterviewtask

import com.google.gson.GsonBuilder
import com.marwa.ecinterviewtask.data.datasource.remote.api.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CompetitionsUnitTest {
    private lateinit var retrofit: Retrofit


    /**
     * This method is called before each test method is executed to instantiate retrofit
     */
    @Before
    fun init() {

        retrofit = Retrofit.Builder().baseUrl(BuildConfig.BASED_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
    }

    /**
     * Test the competitions endpoint
     */
    @Test
    fun testCompetitions() = runBlocking {
        val service = retrofit.create(ApiService::class.java)
        val response = service.getCompetitions()
        val errorBody = response.errorBody()
        assert(errorBody == null)
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
    }


}