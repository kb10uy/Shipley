package org.kb10uy.shipley.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import org.kb10uy.shipley.R
import org.kb10uy.shipley.databinding.ComponentExampleDataBinding
import org.kb10uy.shipley.viewmodel.HomeViewModel

// https://kcpoipoi.hatenablog.com/entry/2020/01/13/185349

class HomeTimelineAdapter(
    val viewModel: HomeViewModel,
    private val lifecycleOwner: LifecycleOwner
) :
    RecyclerView.Adapter<HomeTimelineAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.component_example_data, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val content = viewModel.getItem(position)
        (holder.binding as ComponentExampleDataBinding).also {
            it.item = content
            it.lifecycleOwner = lifecycleOwner
        }
    }

    override fun getItemCount(): Int = viewModel.getItemsCount()

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ViewDataBinding = DataBindingUtil.bind(view)!!
    }
}
