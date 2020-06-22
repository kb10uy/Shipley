package org.kb10uy.shipley.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import org.kb10uy.shipley.databinding.ComponentAccountListItemBinding
import org.kb10uy.shipley.viewmodel.AccountListViewModel

/**
 * ViewBinding で AccountsList にバインドする
 */
class AccountListAdapter(
    private val viewModel: AccountListViewModel,
    private val parentLifecycleOwner: LifecycleOwner
) : RecyclerView.Adapter<AccountListAdapter.AccountHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ComponentAccountListItemBinding.inflate(layoutInflater, parent, false)
        return AccountHolder(binding)
    }

    override fun getItemCount(): Int {
        return viewModel.accounts.value!!.size
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        holder.binding.lifecycleOwner = parentLifecycleOwner
        holder.binding.account = viewModel.accounts.value!![position]
        holder.binding.executePendingBindings()
    }

    class AccountHolder(val binding: ComponentAccountListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
