package com.dodi.androidbaseproject.favorite.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dodi.androidbaseproject.di.ViewModelKey
import com.dodi.androidbaseproject.favorite.features.FavoriteViewModel
import com.dodi.androidbaseproject.features.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class FavoriteViewModelModule {
    @Binds
    abstract fun bindFavoriteViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}