package org.kb10uy.shipley.view

import android.accounts.Account
import android.accounts.AccountManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.kb10uy.shipley.R
import org.kb10uy.shipley.ShipleyAccountAuthenticator
import org.kb10uy.shipley.databinding.ActivityAuthenticatorBinding

class AuthenticatorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthenticatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = AccountManager.get(this)
        binding.run {
            buttonVerifyRegister.setOnClickListener {
                val domainName = editTextDomainName.text.toString()
                val accessToken = editTextAccessToken.text.toString()
                val account = Account(domainName, ShipleyAccountAuthenticator.ACCOUNT_TYPE)

                manager.addAccountExplicitly(account, accessToken, null)

                setResult(RESULT_OK)
                finish()
            }
        }
    }
}
