package com.dodi.androidbaseproject.features.main

import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.dodi.androidbaseproject.R
import com.dodi.androidbaseproject.databinding.ItemTeamBinding
import com.dodi.core.abstraction.base.BaseAdapter
import com.dodi.core.abstraction.utils.load
import com.dodi.core.data.model.TeamModel

class TeamAdapter : BaseAdapter<TeamModel, ItemTeamBinding>(
    DIFF_CALLBACK
) {
    lateinit var viewModel: MainViewModel
    lateinit var lifecycleOwner: LifecycleOwner

    var itemListener :((item: TeamModel) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TeamModel>() {
            override fun areItemsTheSame(
                oldItem: TeamModel,
                newItem: TeamModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: TeamModel,
                newItem: TeamModel
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getLayout(): Int = R.layout.item_team


    override fun onBindViewHolder(
        holder: BaseAdapter.Companion.BaseViewHolder<ItemTeamBinding>,
        position: Int
    ) {
        val item = getItem(position) ?: return

        holder.apply {
            binding.tvTitle.text = item.strTeam
            binding.ivPoster.load(item.strTeamBadge.toString())
        }


    }
}