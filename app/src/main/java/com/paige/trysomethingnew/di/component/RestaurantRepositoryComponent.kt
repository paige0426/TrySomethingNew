package com.paige.trysomethingnew.di.component

import com.paige.trysomethingnew.api.repositories.RestaurantRepository
import com.paige.trysomethingnew.di.module.ContextModule
import com.paige.trysomethingnew.di.module.DatabaseModule
import com.paige.trysomethingnew.di.module.NetworkModule
import com.paige.trysomethingnew.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ContextModule::class, DatabaseModule::class, NetworkModule::class])
interface RestaurantRepositoryComponent {
    fun getRestaurantRepository(): RestaurantRepository
}