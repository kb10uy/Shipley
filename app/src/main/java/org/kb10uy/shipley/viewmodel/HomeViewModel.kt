package org.kb10uy.shipley.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import org.kb10uy.shipley.model.ExampleData

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    private val _items = MutableLiveData<List<ExampleData>>(listOf())

    private fun updateDataSet() {
        onItemsDataSetChanged.value = Unit
    }

    val text: LiveData<String> = _text

    val onItemsDataSetChanged = LiveEvent<Unit>()

    fun getItem(index: Int): ExampleData =
        _items.value?.get(index) ?: throw IllegalStateException("not initialized")

    fun setItems(items: List<ExampleData>) {
        _items.value = items
        updateDataSet()
    }

    fun getItemsCount(): Int = _items.value?.size ?: 0
}
