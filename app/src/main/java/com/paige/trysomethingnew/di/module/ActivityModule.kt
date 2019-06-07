package com.paige.trysomethingnew.di.module

import com.paige.trysomethingnew.MainActivity
import com.paige.trysomethingnew.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ActivityScope
    abstract fun provideMainActivityContributor(): MainActivity
}