package com.paige.trysomethingnew.api.repositories

import com.paige.trysomethingnew.api.model.Restaurant
import com.paige.trysomethingnew.api.service.YelpApiService
import com.paige.trysomethingnew.db.dao.RestaurantDao
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantDao: RestaurantDao, private val yelpApiService: YelpApiService) {
    private val restaurantRepositoryJob = Job()
    private val restaurantRepositoryScope = CoroutineScope(restaurantRepositoryJob + Dispatchers.Main)

    fun fetchRestaurants(term: String, lat: Double, lon: Double, function: (List<Restaurant>) -> Unit) {
        restaurantRepositoryScope.launch {
            requestAndSaveData(term, lat, lon)
            function(getRestaurants())
        }
    }

    suspend fun getRestaurants(): List<Restaurant> {
        return restaurantDao.getAllRestaurants()
    }

    private suspend fun requestAndSaveData(term: String, lat: Double, lon: Double) {
        val yelpResponse = yelpApiService.loadRestaurants(term, lat, lon).await()
        restaurantDao.insert(yelpResponse.restaurantList)
        Timber.i(yelpResponse.restaurantList.toString())
    }
}