package org.kb10uy.shipley.view.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("bind:itemsUpdate")
fun RecyclerView.onItemsChanged(unit: Unit?) {
    adapter?.notifyDataSetChanged()
}
