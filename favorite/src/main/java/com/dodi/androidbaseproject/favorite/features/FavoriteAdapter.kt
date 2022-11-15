package com.dodi.androidbaseproject.favorite.features

import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import com.dodi.androidbaseproject.R
import com.dodi.core.abstraction.base.BasePagedListAdapter
import com.dodi.core.abstraction.utils.observe
import com.dodi.core.data.model.TeamModel
import com.dodi.core.databinding.ItemTeamBinding

class FavoriteAdapter : BasePagedListAdapter<TeamModel, ItemTeamBinding>(DIFF_CALLBACK) {
    lateinit var viewModel: FavoriteViewModel
    lateinit var lifecycleOwner: LifecycleOwner
    var favoriteListener : ((teamModel : TeamModel, state : Boolean)-> Unit)? = null
    var shareListner : ((teamModel : TeamModel)-> Unit)? = null

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TeamModel>(){
            override fun areItemsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }

    override fun getLayout(): Int = R.layout.item_team

    override fun onBindViewHolder(
        holder: BasePagedListAdapter.Companion.BaseViewHolder<ItemTeamBinding>,
        position: Int
    ) {
       val item = getItem(position) ?: return
        holder.apply {
            binding.root.setOnClickListener { listener?.invoke(it,position,item) }
            lifecycleOwner.observe(viewModel.isFavorite(item)){ state->
                binding.cbIsFav.setOnClickListener {
                    favoriteListener?.invoke(item,state)
                }
                binding.apply {
                    setVariable(BR.isFavorite, state)
                    executePendingBindings()
                }
            }
            binding.btnShare.setOnClickListener {
                shareListner?.invoke(item)
            }
            binding.apply {
                setVariable(BR.item,item)
                executePendingBindings()
            }
        }
    }
}