package org.kb10uy.shipley.presenter

import androidx.recyclerview.widget.DiffUtil

/**
 * RecyclerView でバインドするオブジェクトが実装する
 */
interface RecyclerViewItem {
    fun isSameObject(rhs: RecyclerViewItem): Boolean {
        return this == rhs
    }

    fun isSameContent(rhs: RecyclerViewItem): Boolean {
        return this == rhs
    }
}

/**
 * RecyclerViewItem を DiffUtil で差分取るときのやつ
 * c.f. https://qiita.com/kubode/items/92c1190a6421ba055cc0
 */
class RecyclerViewItemCallback<T : RecyclerViewItem>(
    private val oldList: List<T>,
    private val newList: List<T>
) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isSameObject(newList[newItemPosition])
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].isSameContent(newList[newItemPosition])
    }

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size
}
