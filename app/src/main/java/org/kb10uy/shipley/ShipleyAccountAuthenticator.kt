package org.kb10uy.shipley

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.kb10uy.shipley.view.AuthenticatorActivity

class ShipleyAccountAuthenticator(private val context: Context) :
    AbstractAccountAuthenticator(context) {
    companion object {
        const val ACCOUNT_TYPE = "org.kb10uy.shipley"
    }

    override fun addAccount(
        response: AccountAuthenticatorResponse?,
        accountType: String?,
        authTokenType: String?,
        requiredFeatures: Array<out String>?,
        options: Bundle?
    ): Bundle {
        val intent = Intent(context, AuthenticatorActivity::class.java)
        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)

        val bundle = Bundle()
        bundle.putParcelable(AccountManager.KEY_INTENT, intent)
        return bundle
    }

    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        val manager = AccountManager.get(context)
        val name = account?.name
        val accessToken = manager.getPassword(account)

        manager.setAuthToken(account, authTokenType, accessToken)

        val bundle = Bundle()
        bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, ACCOUNT_TYPE)
        bundle.putString(AccountManager.KEY_ACCOUNT_NAME, name)
        bundle.putString(AccountManager.KEY_AUTHTOKEN, accessToken)
        return bundle
    }

    override fun editProperties(
        response: AccountAuthenticatorResponse?,
        accountType: String?
    ): Bundle? {
        return null
    }

    override fun confirmCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        options: Bundle?
    ): Bundle? {
        return null
    }

    override fun getAuthTokenLabel(authTokenType: String?): String? {
        return null
    }

    override fun updateCredentials(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle? {
        return null
    }

    override fun hasFeatures(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        features: Array<out String>?
    ): Bundle? {
        return null
    }

}
