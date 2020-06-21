package org.kb10uy.shipley.http

import okhttp3.OkHttpClient

/**
 * OkHttpClient のシングルトンを保持する
 */
object ClientPool {
    private val originalClient by lazy {
        OkHttpClient()
    }

    fun newBuilder() : OkHttpClient.Builder {
        return originalClient.newBuilder()
    }
}
