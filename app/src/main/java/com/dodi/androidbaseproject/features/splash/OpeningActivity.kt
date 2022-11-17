package com.dodi.androidbaseproject.features.splash

import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.bumptech.glide.Glide
import com.dodi.androidbaseproject.R
import com.dodi.androidbaseproject.databinding.ActivityOpeningBinding
import com.dodi.androidbaseproject.features.main.MainActivity
import com.dodi.core.abstraction.base.BaseActivity

class OpeningActivity : BaseActivity<ActivityOpeningBinding>({ ActivityOpeningBinding.inflate(it) }) {

    override fun ActivityOpeningBinding.oncreate(savedInstanceState: Bundle?) {
        Glide.with(this@OpeningActivity).load(R.drawable.ic_epl).into(binding.imgSplash)

        playAnimation()
        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.navigate(this@OpeningActivity)
            finish()
        }, PENDINGDURATION)
    }

    override fun observerViewModel() {
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imgSplash, View.TRANSLATION_Y, -30f, 30f).apply {
            duration = PENDINGDURATION
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
    }

    companion object{
        const val PENDINGDURATION : Long = 3000
    }


}