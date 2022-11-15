package com.dodi.androidbaseproject.features.team

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import com.dodi.androidbaseproject.MyApp
import com.dodi.androidbaseproject.R
import androidx.appcompat.widget.SearchView
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
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                   /* lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }*/
                    observe(viewModel.searchTeams(p0.toString()), ::handleSearch)
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                   /* lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }*/
                    observe(viewModel.searchTeams(p0.toString()), ::handleSearch)

                    return true
                }
            })

            adapter.lifecycleOwner = this@TeamFragment
            adapter.viewModel = this@TeamFragment.viewModel
            adapter.listener = {_, _, item ->
                DetailActivity.navigate(requireActivity(), item)
            }
            adapter.favoriteListener = {item, isFavorite ->
                viewModel.insertFavorite(item, isFavorite)
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

    private fun handleSearch(teams: List<TeamModel>) {
        Log.d("HASILNYA",teams.size.toString())

        if (!teams.isNullOrEmpty()){
            adapter.submitList(teams)
            Log.d("HASIL",teams.size.toString())

        }else{
            Log.d("HASILKOSONG",teams.size.toString())
            requireContext().showToast(R.string.error_message.toString())
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApp).appComponent.inject(this)
    }

}