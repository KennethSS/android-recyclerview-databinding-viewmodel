package com.solar.recyclerviewsample.adapter

import androidx.recyclerview.widget.DiffUtil
import com.solar.recyclerview.adapter.list.AbstractDataBindingListAdapter
import com.solar.recyclerviewsample.model.food.Food

class FoodListAdapter : AbstractDataBindingListAdapter<Food>(DIFF_UTIL_ITEM_CALLBACK) {

    companion object {
        private val DIFF_UTIL_ITEM_CALLBACK = object: DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean =
                oldItem.title == newItem.title
        }
    }
}