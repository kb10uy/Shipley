package org.kb10uy.shipley.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.kb10uy.shipley.R
import org.kb10uy.shipley.databinding.FragmentAccountListBinding
import org.kb10uy.shipley.view.adapter.AccountListAdapter
import org.kb10uy.shipley.viewmodel.AccountListViewModel
import org.kb10uy.shipley.viewmodel.HomeViewModel

class AccountListFragment : Fragment() {
    private lateinit var binding: FragmentAccountListBinding;
    private val viewModel: AccountListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountListBinding.inflate(inflater, container, false)
        setupList()
        viewModel.reloadAccounts()
        return binding.root
    }

    private fun setupList() {
        val adapter = AccountListAdapter(viewModel, viewLifecycleOwner)
        binding.recyclerViewAccounts.adapter = adapter
        binding.recyclerViewAccounts.layoutManager = LinearLayoutManager(this.context)
        viewModel.accountsDiffNotifier.observe(viewLifecycleOwner, Observer {
            it.dispatchUpdatesTo(binding.recyclerViewAccounts.adapter as AccountListAdapter)
        })
    }
}
