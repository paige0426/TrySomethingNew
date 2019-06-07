package com.paige.trysomethingnew.di.component

import android.app.Application
import com.paige.trysomethingnew.TrySomethingNewApplication
import com.paige.trysomethingnew.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModule::class,
    AppModule::class,
    DatabaseModule::class,
    FragmentModule::class,
    NetworkModule::class,
    ViewModelModule::class
])
interface AppComponent {
    fun inject(app: TrySomethingNewApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}