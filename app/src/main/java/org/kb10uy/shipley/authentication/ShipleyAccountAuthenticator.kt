package org.kb10uy.shipley.authentication

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import org.kb10uy.shipley.view.AuthenticatorActivity

/**
 * Mastodon アカウントを管理する Authenticator
 */
class ShipleyAccountAuthenticator(private val context: Context) :
    AbstractAccountAuthenticator(context) {
    companion object {
        const val ACCOUNT_TYPE = "org.kb10uy.shipley"
        const val TYPE_FULL_ACCESS = "fullAccess"
        const val KEY_DOMAIN_NAME = "domainName"
        const val KEY_DISPLAY_NAME = "displayName"
        const val KEY_AVATAR = "avatar"
    }

    /**
     * 「アカウントを追加」の後に呼ばれるやつ
     */
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

    /**
     * AccountManager にアクセストークンがキャッシュされていない時に呼ばれるやつ。
     * だが、アクセストークンしか保持していないので原則として呼ばれない。
     */
    override fun getAuthToken(
        response: AccountAuthenticatorResponse?,
        account: Account?,
        authTokenType: String?,
        options: Bundle?
    ): Bundle {
        val bundle = Bundle()
        bundle.putInt(AccountManager.KEY_ERROR_CODE, -1)
        bundle.putString(AccountManager.KEY_ERROR_MESSAGE, "Invalid account state. Re-register it.")
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
