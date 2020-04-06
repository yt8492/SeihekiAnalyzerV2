package com.yt8492.seihekianalyzerv2.common.domain.model

sealed class Work {

    abstract val name: String

    data class OnSale(
        override val name: String,
        val url: Url,
        val tags: List<Tag>
    ) : Work()

    data class Discontinued(
        override val name: String
    ) : Work()
}
