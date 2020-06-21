@file:Suppress("UNUSED_PARAMETER")

package org.kb10uy.shipley.view.extension

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("homeRecyclerItemsUpdate")
fun RecyclerView.onItemsChanged(_unit: Unit?) {
    adapter?.notifyDataSetChanged()
}
