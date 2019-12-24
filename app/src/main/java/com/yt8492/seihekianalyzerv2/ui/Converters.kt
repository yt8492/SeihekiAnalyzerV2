package com.yt8492.seihekianalyzerv2.ui

import com.yt8492.seihekianalyzerv2.common.domain.model.AnalyzeResult
import com.yt8492.seihekianalyzerv2.ui.bindingmodel.AnalyzeResultBindingModel
import com.yt8492.seihekianalyzerv2.ui.bindingmodel.TagCountBindingModel

fun AnalyzeResult.toBindingModel(): AnalyzeResultBindingModel {
    val totalCount = this.totalCount
    val tagCounts = this.tagCounts.map {
        TagCountBindingModel(
            it.tag,
            it.count,
            (it.count.toDouble() / totalCount) * 100
        )
    }
    return AnalyzeResultBindingModel(totalCount, tagCounts)
}