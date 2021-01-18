package com.solar.recyclerview.adapter.normal

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.solar.recyclerview.adapter.holder.ItemType

/**
 *  This is Basic Class for RecyclerView Adapter
 */

abstract class AbstractBaseAdapter<T : ItemType, VH : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<VH>() {

    open val list: MutableList<T> by lazy { mutableListOf<T>() }

    fun add(item: T) {
        list.add(item)
        notifyItemInserted(itemCount)
    }

    fun addAt(index: Int, item: T) {
        if (index < list.size) {
            list.add(index, item)
            notifyItemInserted(index)
        }
    }

    fun addAll(list: List<T>) {
        val previous = this.list.size
        this.list.addAll(list)
        Log.d("addAll", "previous $previous")
        Log.d("addAll", "listSize ${list.size}")
        notifyItemRangeInserted(previous, list.size)
    }

    fun remove(item: T, isAnim: Boolean = false) {
        val index = list.indexOf(item)
        if (index != -1) {
            list.removeAt(index)

            if (isAnim) notifyItemRangeRemoved(index, 1)
            else notifyDataSetChanged()
        }
    }

    fun removeAt(position: Int, isAnim: Boolean = false) {
        if (position < list.size) {
            list.removeAt(position)

            if (isAnim) notifyItemRangeRemoved(position, 1)
            else notifyDataSetChanged()
        }
    }

    fun update(index: Int, item: T) {
        if (index < list.size) {
            list[index] = item
            notifyItemChanged(index)
        }
    }

    fun submitList(list: List<T>) {
        this.list.run {
            clear()
            addAll(list)
        }
        notifyDataSetChanged()
    }

    fun getItem(index: Int): T = list[index]

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int = list[position].layoutRes
}