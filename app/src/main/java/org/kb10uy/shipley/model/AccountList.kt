package org.kb10uy.shipley.model

import android.accounts.AccountManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.kb10uy.shipley.ShipleyApplication
import org.kb10uy.shipley.authentication.ShipleyAccountAuthenticator

object AccountList {
    /**
     * アカウントのリストを更新する。内容は更新されない
     */
    fun fetchAccounts(): List<Account> {
        val accountManager = AccountManager.get(ShipleyApplication.instance.applicationContext)
        return accountManager
            .getAccountsByType(ShipleyAccountAuthenticator.ACCOUNT_TYPE)
            .map {
                val displayName =
                    accountManager.getUserData(it, ShipleyAccountAuthenticator.KEY_DISPLAY_NAME)
                        ?: "Fetching..."
                val avatar =
                    accountManager.getUserData(it, ShipleyAccountAuthenticator.KEY_AVATAR)
                        ?: ""
                val accessToken = runBlocking(Dispatchers.IO) {
                    accountManager.getAuthToken(
                        it,
                        ShipleyAccountAuthenticator.TYPE_FULL_ACCESS,
                        null,
                        false,
                        null,
                        null
                    ).result.run {
                        getString(AccountManager.KEY_AUTHTOKEN)!!
                    }
                }
                Account(displayName, it.name, avatar, accessToken)
            }
    }
}
