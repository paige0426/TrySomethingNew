package com.paige.trysomethingnew.api.response

import com.google.gson.annotations.SerializedName
import com.paige.trysomethingnew.db.entity.Restaurant

data class YelpResponse(
    @SerializedName("businesses")
    val restaurantList : List<Restaurant>
)