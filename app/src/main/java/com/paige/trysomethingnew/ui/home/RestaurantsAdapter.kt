package com.paige.trysomethingnew.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.paige.trysomethingnew.api.model.Restaurant

class RestaurantsAdapter : ListAdapter<Restaurant, RestaurantViewHolder>(RESTAURANT_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.bind(restaurant)
    }

    companion object {
        private val RESTAURANT_COMPARATOR = object : DiffUtil.ItemCallback<Restaurant>() {
            override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
                return oldItem == newItem
            }
        }
    }
}