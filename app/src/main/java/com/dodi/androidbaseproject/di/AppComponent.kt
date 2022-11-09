package com.dodi.androidbaseproject.di

import com.dodi.androidbaseproject.features.main.MainActivity
import com.dodi.core.di.CoreComponent
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class,ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory{
        fun create(coreComponent: CoreComponent): AppComponent
    }

    //inject setiap activity maupun fragment disini
    fun inject(activity: MainActivity)
}