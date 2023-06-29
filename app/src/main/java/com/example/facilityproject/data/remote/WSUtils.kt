package com.vendu.books.webservice

import android.content.Context
import android.text.TextUtils
import com.example.facilityproject.data.remote.WSCallback
import com.example.facilityproject.data.remote.WSConstants
import com.google.gson.GsonBuilder


import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


/**
 * Webservice utility class.
 */

object WSUtils {

    private var wsCallback: WSCallback? = null

    /**
     * Static method to to get api client instance
     *
     * @return ApiCallback instance
     */
    /**
     * interceptor for printing logs of request and response
     */// <-- this is the important line!
    val client: WSCallback?
        get() {

            try {
                if (wsCallback == null) {

                    val httpClient = OkHttpClient.Builder()
                    httpClient.connectTimeout(
                        WSConstants.WS_CONNECTION_TIMEOUT.toLong(),
                        TimeUnit.SECONDS
                    )
                    httpClient.readTimeout(WSConstants.WS_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    httpClient.addInterceptor(logging)


                    val client = Retrofit.Builder().baseUrl(WSConstants.WS_BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build()
                    wsCallback = client.create(WSCallback::class.java)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }

            return wsCallback
        }


}