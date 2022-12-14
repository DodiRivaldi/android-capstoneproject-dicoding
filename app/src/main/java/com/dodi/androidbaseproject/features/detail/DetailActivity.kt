package com.dodi.androidbaseproject.features.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.dodi.androidbaseproject.MyApp
import com.dodi.androidbaseproject.R
import com.dodi.androidbaseproject.databinding.ActivityDetailBinding
import com.dodi.androidbaseproject.features.ViewModelFactory
import com.dodi.androidbaseproject.features.main.MainActivity
import com.dodi.core.abstraction.base.BaseActivity
import com.dodi.core.abstraction.utils.observe
import com.dodi.core.abstraction.utils.showToast
import com.dodi.core.data.model.TeamModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>({ ActivityDetailBinding.inflate(it) }) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: DetailViewModel by viewModels { factory }

    @ExperimentalCoroutinesApi
    override fun ActivityDetailBinding.oncreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this@DetailActivity)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(true)
        }
        val items = intent.getParcelableExtra<TeamModel>(EXTRA_DATA)
        items?.let {
            viewModel.setSelected(it)
        }
        supportActionBar?.title = items?.strTeam
    }

    override fun onBackPressed() {
        super.onBackPressed()
        MainActivity.navigate(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        MainActivity.navigate(this)
        return true
    }

    private fun isFavorite(state: Boolean) {
        binding.fabFav.setOnClickListener {
            viewModel.insertFavorite(state)
            if (!state) {
                showToast(getString(R.string.favorited))
            } else {
                showToast(getString(R.string.unfavorited))
            }
        }
        binding.fabFav.setImageDrawable(
            ContextCompat.getDrawable(
                this@DetailActivity,
                if (state) R.drawable.ic_favorite_filled else R.drawable.ic_favorite_border
            )
        )
    }

    override fun observerViewModel() {
        observe(viewModel.item) { binding.item = it }
        observe(viewModel.isFavorite, ::isFavorite)
    }

    companion object {
        private const val EXTRA_DATA = "EXTRA_DATA"

        fun navigate(activity: Activity, teamModel: TeamModel) {
            Intent(activity, DetailActivity::class.java).apply {
                putExtra(EXTRA_DATA, teamModel)
            }.also {
                activity.startActivity(it)
            }
        }
    }

}