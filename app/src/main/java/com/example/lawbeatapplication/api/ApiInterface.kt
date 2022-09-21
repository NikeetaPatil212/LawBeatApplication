package com.example.lawbeatapplication.api

import com.example.lawbeatapplication.dataclass.CategoryResponse
import com.example.lawbeatapplication.dataclass.NewsResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @POST("lawbeat-categories")
    suspend fun getCategory(
    ): Response<CategoryResponse>

    @GET("lawbeat-news-details")
    suspend fun getNews(
        @Query("items_per_page") items_per_page: Int = 50,
        @Query("page") page: Int = 1,
        @Query("tid") tid:Int,
        @Query("uid") uid : Int = 66,
    ): Response<NewsResponse>

    companion object {

        var retrofitService: Retrofit? = null

        fun getInstance(): Retrofit {

            if (retrofitService == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient =
                    OkHttpClient.Builder().addInterceptor(interceptor).build()
                retrofitService = Retrofit.Builder()
                    .baseUrl("https://lawgical.php-dev.in/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
            }
            return retrofitService!! as Retrofit
        }
    }
}