package com.paige.trysomethingnew.api.repositories

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.paige.trysomethingnew.db.entity.Restaurant
import com.paige.trysomethingnew.api.service.YelpApiService
import com.paige.trysomethingnew.db.dao.RestaurantDao
import com.paige.trysomethingnew.ui.home.RestaurantBoundaryCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantDao: RestaurantDao, private val yelpApiService: YelpApiService) {
    private val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)

    private var offset = 0

    suspend fun loadMoreRestaurants (
        term: String,
        lat: Double,
        lon: Double,
        limit: Int = 20,
        offset: Int = 0
    ): List<Restaurant> {
        return requestAndSaveData(term, lat, lon, limit, offset)
    }

    fun loadMoreRestaurants (
        term: String,
        lat: Double,
        lon: Double
    ) {
        scope.launch {
            Timber.i("Fetch more data: $offset")
            requestAndSavePagedData(term, lat, lon, limit = 20, offset = offset)
        }
    }

    suspend fun getRestaurants(): List<Restaurant> {
        return restaurantDao.getAllRestaurants()
    }

    fun getPagedRestaurant(): LiveData<PagedList<Restaurant>> {
        val dataSourceFactory = restaurantDao.getRestaurantDataSource()
        return LivePagedListBuilder(dataSourceFactory, 20)
            .setBoundaryCallback(RestaurantBoundaryCallback(this))
            .build()
    }

    private suspend fun requestAndSaveData(
        term: String,
        lat: Double,
        lon: Double,
        limit: Int,
        offset: Int
    ): List<Restaurant> {
        val yelpResponse = yelpApiService.loadRestaurants(term, lat, lon, limit, offset).await()
        restaurantDao.insert(yelpResponse.restaurantList)
        Timber.i(yelpResponse.restaurantList.toString())
        return getRestaurants()
    }

    private suspend fun requestAndSavePagedData(
        term: String,
        lat: Double,
        lon: Double,
        limit: Int,
        offset: Int
    ) {
        val yelpResponse = yelpApiService.loadRestaurants(term, lat, lon, limit, offset).await()
        restaurantDao.insert(yelpResponse.restaurantList)
        Timber.i(yelpResponse.restaurantList.toString())
        this.offset += yelpResponse.restaurantList.size
    }
}