package org.kb10uy.shipley.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DiffUtil
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import org.kb10uy.shipley.model.Account
import org.kb10uy.shipley.model.AccountList
import org.kb10uy.shipley.presenter.AccountListItem
import org.kb10uy.shipley.presenter.RecyclerViewItemCallback

class AccountListViewModel : ViewModel() {
    private val _accounts = MutableLiveData<List<AccountListItem>>(listOf())
    private val _accountsDiffNotifier = MutableLiveData<DiffUtil.DiffResult>()

    fun reloadAccounts() {
        val newAccounts = AccountList.fetchAccounts().map { AccountListItem(it) }
        val diff = DiffUtil.calculateDiff(
            RecyclerViewItemCallback(
                _accounts.value!!,
                newAccounts
            )
        )
        _accounts.value = newAccounts
        _accountsDiffNotifier.value = diff
    }

    val accounts: LiveData<List<AccountListItem>> = _accounts
    val accountsDiffNotifier: LiveData<DiffUtil.DiffResult> = _accountsDiffNotifier
}
