package org.kb10uy.shipley.presenter

import org.kb10uy.shipley.model.Account

class AccountListItem(private val account: Account) : RecyclerViewItem {
    val displayName get() = account.displayName
    val screenName get() = account.screenName
    val avatar get() = account.avatar

    override fun isSameContent(rhs: RecyclerViewItem): Boolean {
        val rhs = rhs as AccountListItem
        return displayName == rhs.displayName
            && screenName == rhs.screenName
            && avatar == rhs.avatar
    }
}
