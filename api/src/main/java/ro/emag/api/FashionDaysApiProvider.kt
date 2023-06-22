package ro.emag.api

import com.google.gson.GsonBuilder
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ro.emag.Constants
import ro.emag.Constants.REQUEST_TIMEOUT
import java.util.concurrent.TimeUnit


object FashionDaysApiProvider {

    private val gsonConverterFactory: GsonConverterFactory = let {
        val gson = GsonBuilder().setLenient().create()
        GsonConverterFactory.create(gson)
    }

    private val localeInterceptor: HeaderInterceptor by lazy { HeaderInterceptor() }

    fun provideFashionDaysAPI(isDebug: Boolean): FashionDaysApi = let {
        val okHttpClient: OkHttpClient = let {
            val client = OkHttpClient.Builder()
                .addInterceptor(localeInterceptor)
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

            if (isDebug) {
                client.addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
            client.build()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
        retrofit.create(FashionDaysApi::class.java)
    }
}