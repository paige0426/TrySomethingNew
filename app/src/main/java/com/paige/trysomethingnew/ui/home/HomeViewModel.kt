package com.paige.trysomethingnew.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.paige.trysomethingnew.db.entity.Restaurant
import com.paige.trysomethingnew.api.repositories.RestaurantRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val restaurantRepository: RestaurantRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _restaurantList = MutableLiveData<List<Restaurant>>()
    val restaurants: LiveData<List<Restaurant>>
        get() = _restaurantList

    var pagedListRestaurant: LiveData<PagedList<Restaurant>> = restaurantRepository.getPagedRestaurant()

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
//        viewModelScope.launch {
//            var restaurantList = restaurantRepository.getRestaurants()
//            if (restaurantList.isEmpty()) {
//                restaurantList = restaurantRepository.loadMoreRestaurants("steak", 37.786882, -122.399972)
//            }
//            _restaurantList.postValue(restaurantList)
//        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}