package com.yt8492.seihekianalyzerv2.ui.bindingmodel

data class AnalyzeResultBindingModel(
    val totalCount: Int,
    val successCount: Int,
    val tagCounts: List<TagCountBindingModel>
)
