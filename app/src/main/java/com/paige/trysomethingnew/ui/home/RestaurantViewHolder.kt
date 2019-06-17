package com.paige.trysomethingnew.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.paige.trysomethingnew.R
import com.paige.trysomethingnew.db.entity.Restaurant

class RestaurantViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    @BindView(R.id.name_restaurant)
    lateinit var name: TextView

    @BindView(R.id.img_restaurant)
    lateinit var restaurantImage: ImageView

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(restaurant: Restaurant) {
        name.text = restaurant.name
        Glide.with(restaurantImage.context)
            .load(restaurant.imageUrl)
            .centerCrop()
            .into(restaurantImage)
    }

    companion object {
        fun create(parent: ViewGroup): RestaurantViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.restaurant_item_view, parent, false)
            return RestaurantViewHolder(view)
        }
    }
}