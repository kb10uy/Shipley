package org.kb10uy.shipley.view.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("homeRecyclerItemsUpdate")
fun RecyclerView.onItemsChanged() {
    adapter?.notifyDataSetChanged()
}
