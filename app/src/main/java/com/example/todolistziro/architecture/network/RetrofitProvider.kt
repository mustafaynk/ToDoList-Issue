package com.example.todolistziro.architecture.network

import com.example.todolistziro.architecture.apis.IssueApi
import com.example.todolistziro.architecture.apis.LoginApi
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitProvider {

    private val TIMEOUT = 30L

    private lateinit var retrofit: Retrofit

    private fun provideOkHttpBuilder(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun provideJacksonObjectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL)

        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
        return objectMapper
    }

    fun provideLoginApi(): LoginApi {
        retrofit = Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(provideJacksonObjectMapper()))
            .baseUrl(Utils.Urls.LOGIN_URL)
            .client(provideOkHttpBuilder())
            .build()
        return retrofit.create(LoginApi::class.java)
    }

    fun provideIssueApi(): IssueApi {
        retrofit = Retrofit.Builder()
            .addConverterFactory(JacksonConverterFactory.create(provideJacksonObjectMapper()))
            .baseUrl(Utils.Urls.ISSUE_API_URL)
            .client(provideOkHttpBuilder())
            .build()
        return retrofit.create(IssueApi::class.java)
    }

}