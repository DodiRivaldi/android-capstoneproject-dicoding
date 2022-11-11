package com.dodi.androidbaseproject.features.team

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.dodi.androidbaseproject.R
import com.dodi.core.abstraction.base.BaseAdapter
import com.dodi.core.abstraction.utils.observe
import com.dodi.core.data.model.TeamModel
import com.dodi.core.databinding.ItemTeamBinding

class TeamAdapter : BaseAdapter<TeamModel, ItemTeamBinding>(
    DIFF_CALLBACK
) {
    lateinit var viewModel: TeamViewModel
    lateinit var lifecycleOwner: LifecycleOwner

    var favoriteListener: ((item: TeamModel, isFavorite: Boolean) -> Unit)? = null
    var shareListener: ((item: TeamModel) -> Unit)? = null

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
            binding.root.setOnClickListener { listener?.invoke(it, position, item) }
            lifecycleOwner.observe(viewModel.isFavorite(item)) { isFavorite ->
                binding.cbIsFav.setOnClickListener {
                    favoriteListener?.invoke(item, isFavorite)
                }
                binding.apply {
                    setVariable(BR.isFavorite, isFavorite)
                    executePendingBindings()
                }
            }
            binding.btnShare.setOnClickListener { shareListener?.invoke(item) }
            binding.apply {
                setVariable(BR.item, item)
                executePendingBindings()
            }
        }

    }
}