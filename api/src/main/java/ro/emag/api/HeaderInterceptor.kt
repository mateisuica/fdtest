package ro.emag.api

import okhttp3.Interceptor
import okhttp3.Response


private const val KEY = "x-mobile-app-locale"
private const val VALUE = "ro_RO"


class HeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader(KEY, VALUE)
        return chain.proceed(builder.build())
    }
}