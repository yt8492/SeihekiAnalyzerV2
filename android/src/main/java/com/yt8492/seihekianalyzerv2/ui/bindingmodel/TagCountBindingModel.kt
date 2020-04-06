package com.yt8492.seihekianalyzerv2.ui.bindingmodel

import com.yt8492.seihekianalyzerv2.common.domain.model.Tag

data class TagCountBindingModel(
    val tag: Tag,
    val count: Int,
    val percentage: Double
)
