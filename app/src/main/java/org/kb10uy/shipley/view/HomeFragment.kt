package org.kb10uy.shipley.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.kb10uy.shipley.databinding.FragmentHomeBinding
import org.kb10uy.shipley.model.ExampleData
import org.kb10uy.shipley.view.adapter.HomeTimelineAdapter
import org.kb10uy.shipley.viewmodel.HomeViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val homeViewModel: HomeViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root;

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textHome.text = it
        })

        val list = listOf(
            ExampleData("User1", "Text Text"),
            ExampleData("User1", "Text Text"),
            ExampleData("User2", "Text Text")
        )
        homeViewModel.setItems(list)

        setupHomeTimeline(binding)
        return root
    }

    private fun setupHomeTimeline(binding: FragmentHomeBinding) {
        val adapter = HomeTimelineAdapter(homeViewModel, this.viewLifecycleOwner)
        binding.recyclerHomeTimeline.adapter = adapter
        binding.recyclerHomeTimeline.layoutManager = LinearLayoutManager(this.context)
    }
}
