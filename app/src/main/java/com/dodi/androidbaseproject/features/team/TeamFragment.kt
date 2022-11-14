package com.dodi.androidbaseproject.features.team

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.dodi.androidbaseproject.MyApp
import com.dodi.androidbaseproject.R
import com.dodi.androidbaseproject.databinding.FragmentTeamBinding
import com.dodi.androidbaseproject.features.ViewModelFactory
import com.dodi.androidbaseproject.features.detail.DetailActivity
import com.dodi.core.abstraction.base.BaseFragment
import com.dodi.core.abstraction.utils.gone
import com.dodi.core.abstraction.utils.observe
import com.dodi.core.abstraction.utils.showToast
import com.dodi.core.abstraction.utils.visible
import com.dodi.core.data.Resource
import com.dodi.core.data.model.TeamModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class TeamFragment : BaseFragment<FragmentTeamBinding>({FragmentTeamBinding.inflate(it)}) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: TeamViewModel by viewModels { factory }
    private val adapter by lazy { TeamAdapter() }

    override fun FragmentTeamBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.apply {
            rvTeam.adapter = this@TeamFragment.adapter
            rvTeam.hasFixedSize()
            search.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }
                    return true
                }
            })

            adapter.lifecycleOwner = this@TeamFragment
            adapter.viewModel = this@TeamFragment.viewModel
            adapter.listener = {_, _, item ->
                DetailActivity.navigate(requireActivity(), item)
            }
            adapter.favoriteListener = {item, isFavorite ->
              //            viewModel.setToFavorite(item, isFavorite)
            }
            adapter.shareListener = {
                // to share act requireActivity().shareMovieTv(it)
            }
        }
    }


    override fun observeViewModel() {
        observe(viewModel.getTeamData(), ::handleMovies)
    }

    private fun handleMovies(movies: Resource<List<TeamModel>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    adapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        errorLayout.visible()
                       error.message.text =
                            movies.message ?: getString(R.string.error_message)
                    } else {
                        requireContext().showToast(getString(R.string.error_message))
                        adapter.submitList(movies.data)
                    }
                }
            }
        }
    }

    private fun handleSearch(movies: Resource<List<TeamModel>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    adapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            movies.message ?: getString(R.string.error_message)
                    } else {
                        requireContext().showToast(getString(R.string.error_message))
                        adapter.submitList(movies.data)
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

}