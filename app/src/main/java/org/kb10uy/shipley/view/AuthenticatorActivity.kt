package org.kb10uy.shipley.view

import android.accounts.Account
import android.accounts.AccountManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.kb10uy.shipley.authentication.ShipleyAccountAuthenticator
import org.kb10uy.shipley.databinding.ActivityAuthenticatorBinding
import org.kb10uy.shipley.http.ClientPool
import org.kb10uy.shipley.mastodon.AccessTokenInterceptor
import org.kb10uy.shipley.mastodon.MastodonApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AuthenticatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.run {
            buttonVerifyRegister.setOnClickListener {
                val domainName = editTextDomainName.text.toString()
                val accessToken = editTextAccessToken.text.toString()

                if (verifyAndRegister(domainName, accessToken)) {
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }
    }

    private fun verifyAndRegister(domain: String, accessToken: String): Boolean {
        try {
            val client = ClientPool.newBuilder()
                .addInterceptor(AccessTokenInterceptor(accessToken))
                .build()
            val gson = GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://$domain/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()

            val mastodonApi = retrofit.create(MastodonApi::class.java)

            val response = runBlocking(Dispatchers.IO) { mastodonApi.accountVerifyCredentials() }
            if (response.isSuccessful) {
                val mastodonAccount = response.body()!!

                val accountManager = AccountManager.get(this)
                val account = Account(
                    "${mastodonAccount.username}@${domain}",
                    ShipleyAccountAuthenticator.ACCOUNT_TYPE
                )
                val accountBundle = Bundle().apply {
                    putString(ShipleyAccountAuthenticator.KEY_DOMAIN_NAME, domain)
                }

                accountManager.addAccountExplicitly(account, "", accountBundle)
                accountManager.setAuthToken(
                    account,
                    ShipleyAccountAuthenticator.TYPE_FULL_ACCESS,
                    accessToken
                )

                return true
            } else {
                Toast.makeText(applicationContext, "Invalid", Toast.LENGTH_SHORT).show()
                return false
            }
        } catch (e: Exception) {
            Toast.makeText(applicationContext, "Invalid: ${e.message}", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}
