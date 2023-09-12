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
        val jwt = runBlocking {
            tokenDataStore.getAccessToken().first()
        } ?: return errorResponse(chain.request())

        val request = chain.request().newBuilder().header(AUTHORIZATION, "Bearer $jwt").build()
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
        private const val AUTHORIZATION = "Authorization"
    }
}