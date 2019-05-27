package com.paige.trysomethingnew.api.response

import com.google.gson.annotations.SerializedName
import com.paige.trysomethingnew.api.model.Restaurant

data class YelpResponse(
    @SerializedName("businesses")
    val restaurantList : List<Restaurant>
)