package org.kb10uy.shipley.mastodon

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Authorization: Bearer *** を付加するやつ
 * `@Header` でやれって？やだよ Bearer 毎回手動で付けるなんて
 */
class AccessTokenInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        val request = builder.addHeader("Authorization", "Bearer $token").build()

        return chain.proceed(request)
    }
}
