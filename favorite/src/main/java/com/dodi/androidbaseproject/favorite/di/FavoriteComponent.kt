package com.dodi.androidbaseproject.favorite.di

import com.dodi.androidbaseproject.di.AppModule
import com.dodi.androidbaseproject.favorite.features.FavoriteFragment
import com.dodi.core.di.CoreComponent
import dagger.Component

@FavoriteScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, FavoriteViewModelModule::class]
)
interface FavoriteComponent {
    fun inject(fragment: FavoriteFragment)
}