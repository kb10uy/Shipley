package org.kb10uy.shipley.mastodon

import retrofit2.Response
import retrofit2.http.GET

interface MastodonApi {
    @GET("api/v1/accounts/verify_credentials")
    suspend fun accountVerifyCredentials() : Response<Account>
}
