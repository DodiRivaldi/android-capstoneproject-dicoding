package com.dodi.androidbaseproject.features.team

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
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
import javax.inject.Inject

class TeamFragment : BaseFragment<FragmentTeamBinding>({ FragmentTeamBinding.inflate(it) }) {
    @Inject
    lateinit var factory: ViewModelFactory
    private val viewModel: TeamViewModel by viewModels { factory }
    private val adapter by lazy { TeamAdapter() }

    override fun FragmentTeamBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.apply {
            rvTeam.adapter = this@TeamFragment.adapter
            rvTeam.hasFixedSize()
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                   // viewModel.search("%${p0.toString()}%")
                    if (p0 != null){
                        //viewModel.search("%${p0}%")
                        lifecycleOwner?.let { it ->
                            viewModel.customSearch(p0).observe(it) {items ->
                                items.let {
                                    adapter.submitList(items)
                                }
                            }
                        }
                    }

                    return true
                }
            })

            adapter.lifecycleOwner = this@TeamFragment
            adapter.viewModel = this@TeamFragment.viewModel
            adapter.listener = { _, _, item ->
                DetailActivity.navigate(requireActivity(), item)
            }
            adapter.favoriteListener = { item, isFavorite ->
                viewModel.insertFavorite(item, isFavorite)
                if (!isFavorite) {
                    requireContext().showToast(getString(R.string.favorited))
                } else {
                    requireContext().showToast(getString(R.string.unfavorited))
                }
            }
        }
    }

    override fun observeViewModel() {
        observe(viewModel.getTeamData(), ::handleteams)


    }

    private fun handleteams(teams: Resource<List<TeamModel>>) {
        binding?.apply {
            when (teams) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    pbTeam.visible()
                }
                is Resource.Success -> {
                    pbTeam.gone()
                    errorLayout.gone()
                    adapter.submitList(teams.data)
                }
                is Resource.Error -> {
                    pbTeam.gone()
                    if (teams.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            teams.message ?: getString(R.string.error_message)
                    } else {
                        requireContext().showToast(getString(R.string.error_message))
                        adapter.submitList(teams.data)
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