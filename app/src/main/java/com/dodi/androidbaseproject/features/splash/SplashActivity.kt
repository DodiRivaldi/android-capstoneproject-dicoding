package com.dodi.androidbaseproject.features.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bumptech.glide.Glide
import com.dodi.androidbaseproject.R
import com.dodi.androidbaseproject.databinding.ActivitySplashBinding
import com.dodi.androidbaseproject.features.main.MainActivity
import com.dodi.core.abstraction.base.BaseActivity

class SplashActivity : BaseActivity<ActivitySplashBinding>({ ActivitySplashBinding.inflate(it) }) {

    override fun ActivitySplashBinding.oncreate(savedInstanceState: Bundle?) {
        Glide.with(this@SplashActivity).load(R.drawable.ic_epl).into(binding.imgSplash)

        playAnimation()
        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.navigate(this@SplashActivity)
            finish()
        }, 3000)
    }

    override fun observerViewModel() {
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgSplash, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 3000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

}