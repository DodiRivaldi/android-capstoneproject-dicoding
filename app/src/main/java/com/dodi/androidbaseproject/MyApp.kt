package com.dodi.androidbaseproject

import android.app.Application
import com.dodi.androidbaseproject.di.AppComponent
import com.dodi.androidbaseproject.di.DaggerAppComponent
import com.dodi.core.di.CoreComponent
import com.dodi.core.di.DaggerCoreComponent

open class MyApp : Application() {

    private val coreComponent : CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent : AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}