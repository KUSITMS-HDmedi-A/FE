package com.core.network.utils

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class AuthInterceptor @Inject constructor(
    private val tokenDataStore: TokenDataStore
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenDataStore.getToken()
        } ?: return errorResponse(chain.request())

        //val testToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOiJVU0VSIiwiaWF0IjoxNjk0NTk4NzI2LCJleHAiOjE2OTcxOTA3MjZ9.XldOEKUVhwMu_57zZbQ2TPyVmX5nAAvQbx4IZCQL8dA"
        val testToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIzIiwicm9sZXMiOiJVU0VSIiwiaWF0IjoxNjk0ODkwMDEyLCJleHAiOjE2OTc0ODIwMTJ9.2IGFJtt3cuHkyWfjaEfIt0tOk-b7_TTApkYUjcj5TXg"
        val request = chain.request().newBuilder().header(AUTHORIZATION, "Bearer $testToken").build()
        return chain.proceed(request)
    }

    private fun errorResponse(request: Request): Response = Response.Builder()
        .request(request)
        .protocol(Protocol.HTTP_2)
        .code(1001)
        .message("")
        .body(ResponseBody.create(null, ""))
        .build()

    companion object {
        private const val AUTHORIZATION = "access"
    }
}