package com.paige.trysomethingnew.di.module

import android.content.Context
import com.paige.trysomethingnew.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val context: Context) {
    @ApplicationScope
    @Provides
    fun applicationContext() = context.applicationContext
}