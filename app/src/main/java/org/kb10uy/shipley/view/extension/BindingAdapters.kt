@file:Suppress("UNUSED_PARAMETER")

package org.kb10uy.shipley.view.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.net.URI

@BindingAdapter("homeRecyclerItemsUpdate")
fun RecyclerView.onItemsChanged(_unit: Unit?) {
    adapter?.notifyDataSetChanged()
}

@BindingAdapter("imageByGlide")
fun ImageView.imageURI(uri: String?) {
    Glide.with(this).load(uri).into(this)
}
