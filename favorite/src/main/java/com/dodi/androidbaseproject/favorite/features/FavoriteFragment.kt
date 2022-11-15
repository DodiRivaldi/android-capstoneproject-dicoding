package com.dodi.androidbaseproject.favorite.features

import android.content.Context
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.paging.PagedList
import com.dodi.androidbaseproject.favorite.R
import com.dodi.androidbaseproject.favorite.databinding.FragmentFavoriteBinding
import com.dodi.androidbaseproject.favorite.di.DaggerFavoriteComponent
import com.dodi.androidbaseproject.features.ViewModelFactory
import com.dodi.androidbaseproject.features.detail.DetailActivity
import com.dodi.core.abstraction.base.BaseFragment
import com.dodi.core.abstraction.utils.gone
import com.dodi.core.abstraction.utils.observe
import com.dodi.core.abstraction.utils.visible
import com.dodi.core.data.model.TeamModel
import com.dodi.core.di.CoreComponent
import com.dodi.core.di.DaggerCoreComponent
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class FavoriteFragment :
    BaseFragment<FragmentFavoriteBinding>({ FragmentFavoriteBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(requireActivity())
    }
    private val viewModel: FavoriteViewModel by viewModels { factory }
    private val adapter by lazy { FavoriteAdapter() }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder().coreComponent(coreComponent).build().inject(this)
    }

    private fun favoriteItems(teams : PagedList<TeamModel>){
        if (!teams.isNullOrEmpty()){
            binding?.emptyFavorite?.root?.gone()
            binding?.rvFavoriteTeam?.isVisible
            adapter.submitList(teams)
        }else{
            binding?.emptyFavorite?.root?.visible()
            binding?.rvFavoriteTeam?.gone()
        }
    }
    override fun FragmentFavoriteBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.rvFavoriteTeam?.adapter = this@FavoriteFragment.adapter
        adapter.lifecycleOwner = this@FavoriteFragment
        adapter.viewModel = this@FavoriteFragment.viewModel
        adapter.listener = {_,_,items->
            DetailActivity.navigate(requireActivity(),items)
        }
        adapter.favoriteListener = {items, state ->
            viewModel.insertFavorite(items,state)
        }
    }

    override fun observeViewModel() {
        observe(viewModel.data, ::favoriteItems)
    }

}