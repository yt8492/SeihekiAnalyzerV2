package com.yt8492.seihekianalyzerv2.ui.analyze

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.yt8492.seihekianalyzerv2.ui.bindingmodel.TagCountBindingModel

class TagCountRecyclerAdapter : ListAdapter<TagCountBindingModel, TagCountViewHolder>(CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagCountViewHolder =
        TagCountViewHolder.create(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

    override fun onBindViewHolder(holder: TagCountViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val CALLBACK = object : DiffUtil.ItemCallback<TagCountBindingModel>() {
            override fun areItemsTheSame(
                oldItem: TagCountBindingModel,
                newItem: TagCountBindingModel
            ): Boolean {
                return oldItem.tag == newItem.tag
            }

            override fun areContentsTheSame(
                oldItem: TagCountBindingModel,
                newItem: TagCountBindingModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
