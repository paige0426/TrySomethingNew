package com.paige.trysomethingnew.ui.home

import androidx.paging.PagedList
import com.paige.trysomethingnew.db.entity.Restaurant
import com.paige.trysomethingnew.api.repositories.RestaurantRepository

class RestaurantBoundaryCallback(private val restaurantRepository: RestaurantRepository) : PagedList.BoundaryCallback<Restaurant>() {
    override fun onZeroItemsLoaded() {
        restaurantRepository.loadMoreRestaurants("steak", 37.786882, -122.399972)
    }

    override fun onItemAtEndLoaded(itemAtEnd: Restaurant) {
        restaurantRepository.loadMoreRestaurants("steak", 37.786882, -122.399972)
    }
}