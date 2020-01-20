package com.yt8492.seihekianalyzerv2.ui.analyze

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yt8492.seihekianalyzerv2.databinding.ItemTagCountBinding
import com.yt8492.seihekianalyzerv2.ui.bindingmodel.TagCountBindingModel

class TagCountViewHolder private constructor(
    private val binding: ItemTagCountBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        tagCount: TagCountBindingModel
    ) {
        binding.tagCount = tagCount
        binding.executePendingBindings()
    }

    companion object {
        fun create(
            inflater: LayoutInflater,
            container: ViewGroup,
            attachToRoot: Boolean
        ): TagCountViewHolder = TagCountViewHolder(
            ItemTagCountBinding.inflate(
                inflater,
                container,
                attachToRoot
            )
        )
    }
}