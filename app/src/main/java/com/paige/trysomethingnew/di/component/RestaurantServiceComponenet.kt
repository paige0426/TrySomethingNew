package com.paige.trysomethingnew.di.component

import com.paige.trysomethingnew.db.dao.RestaurantDao
import com.paige.trysomethingnew.di.module.ContextModule
import com.paige.trysomethingnew.di.module.DatabaseModule
import com.paige.trysomethingnew.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ ContextModule::class, DatabaseModule::class])
interface RestaurantServiceComponenet {
    fun restaurantDao() : RestaurantDao
}