package com.dodi.androidbaseproject.features.main

import android.os.Bundle
import androidx.activity.viewModels
import com.dodi.androidbaseproject.MyApp
import com.dodi.androidbaseproject.databinding.ActivityMainBinding
import com.dodi.androidbaseproject.features.ViewModelFactory
import com.dodi.core.abstraction.base.BaseActivity
import com.dodi.core.abstraction.utils.gone
import com.dodi.core.abstraction.utils.observe
import com.dodi.core.abstraction.utils.showToast
import com.dodi.core.abstraction.utils.visible
import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding>({ ActivityMainBinding.inflate(it) }) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: MainViewModel by viewModels { factory }
    private val adapter by lazy { TeamAdapter() }

    @ExperimentalCoroutinesApi
    override fun ActivityMainBinding.oncreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this@MainActivity)

        binding?.apply {
            rvCategory.adapter = this@MainActivity.adapter
            rvCategory.hasFixedSize()
        }

        adapter.lifecycleOwner = this@MainActivity
        adapter.viewModel = this@MainActivity.viewModel

    }

    override fun observerViewModel() {
        observe(
            viewModel.getTeamData(), ::showData
        )
    }

    private fun showData(itemList: Resource<List<TeamModel>>) {
        binding?.apply {
            when (itemList) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    adapter.submitList(itemList.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (itemList.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text = itemList.message ?: "Error"
                    } else {
                        showToast("Error")
                        adapter.submitList(itemList.data)
                    }
                }
            }
        }
    }

}