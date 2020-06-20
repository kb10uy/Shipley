package org.kb10uy.shipley.view

import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.kb10uy.shipley.R
import org.kb10uy.shipley.databinding.ComponentExampleDataBinding
import org.kb10uy.shipley.databinding.FragmentHomeBinding
import org.kb10uy.shipley.model.ExampleData
import org.kb10uy.shipley.view.adapter.HomeTimelineAdapter
import org.kb10uy.shipley.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val root = binding.root;

        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
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

        // val itemTouchHelper = ItemTouchHelper(HomeTimelineSwipeCallback())
        // itemTouchHelper.attachToRecyclerView(binding.recyclerHomeTimeline)
    }

    class HomeTimelineSwipeCallback: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            TODO("Not yet implemented")
        }

        override fun onChildDraw(
            c: Canvas,
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            dX: Float,
            dY: Float,
            actionState: Int,
            isCurrentlyActive: Boolean
        ) {
            if (actionState != ItemTouchHelper.ACTION_STATE_SWIPE) {
                return
            }

            if (dX > 0) {
                // 右スワイプ

            }

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }
}
