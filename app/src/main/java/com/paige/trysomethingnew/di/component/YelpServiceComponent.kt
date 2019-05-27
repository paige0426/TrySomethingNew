package com.paige.trysomethingnew.di.component

import com.paige.trysomethingnew.api.service.YelpApiService
import com.paige.trysomethingnew.di.module.NetworkModule
import com.paige.trysomethingnew.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(modules = [ NetworkModule::class ])
interface YelpServiceComponent {
    fun getYelpApiService() : YelpApiService
}