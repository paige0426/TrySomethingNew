package com.paige.trysomethingnew.di.module

import com.paige.trysomethingnew.di.scope.FragmentScope
import com.paige.trysomethingnew.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    @FragmentScope
    abstract fun provideHomeFragmentContributor(): HomeFragment
}