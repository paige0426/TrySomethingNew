package com.paige.trysomethingnew.di.module

import com.paige.trysomethingnew.ui.activity.MainActivity
import com.paige.trysomethingnew.di.scope.ActivityScope
import com.paige.trysomethingnew.ui.activity.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ActivityScope
    abstract fun provideMainActivityContributor(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    @ActivityScope
    abstract fun provideLoginActivityContributor(): LoginActivity
}