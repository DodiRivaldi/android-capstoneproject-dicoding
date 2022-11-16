package com.dodi.core.abstraction.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B : ViewBinding>(val viewBinder: (LayoutInflater) -> B) :
    AppCompatActivity() {
    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinder(layoutInflater)
        setContentView(binding.root)

        binding.oncreate(savedInstanceState)
        observerViewModel()
    }

    protected abstract fun B.oncreate(savedInstanceState: Bundle?)

    protected abstract fun observerViewModel()
}