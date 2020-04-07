package com.yt8492.seihekianalyzerv2.server.adapter.converter

import com.yt8492.seihekianalyzerv2.common.domain.model.AnalyzeResult
import com.yt8492.seihekianalyzerv2.common.domain.model.Tag
import com.yt8492.seihekianalyzerv2.common.domain.model.TagCount
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.WorkNameAndUrl
import com.yt8492.seihekianalyzerv2.proto.AnalyzeResult as AnalyzeResultProto
import com.yt8492.seihekianalyzerv2.proto.Tag as TagProto
import com.yt8492.seihekianalyzerv2.proto.TagCount as TagCountProto
import com.yt8492.seihekianalyzerv2.proto.Url as UrlProto
import com.yt8492.seihekianalyzerv2.proto.Urls
import com.yt8492.seihekianalyzerv2.proto.WorkNameAndUrl as WorkNameAndUrlProto

fun UrlProto.toDomainModel(): Url? = if (this.isExists) {
    Url(this.value)
} else {
    null
}

fun Url.toProto(): UrlProto =
    UrlProto {
        isExists = true
        value = this@toProto.value
    }

fun WorkNameAndUrlProto.toDomainModel(): WorkNameAndUrl =
    WorkNameAndUrl(
        this.name,
        this.url.toDomainModel()
    )

fun List<Url>.toProto(): Urls =
    Urls {
        addAllUrls(this@toProto.map(Url::toProto))
    }

fun TagProto.toDomainModel(): Tag =
    Tag(this.value)

fun Tag.toProto(): TagProto =
    TagProto {
        value = this@toProto.value
    }

fun TagCountProto.toDomainModel(): TagCount =
    TagCount(
        this.tag.toDomainModel(),
        this.count
    )

fun TagCount.toProto(): TagCountProto =
    TagCountProto {
        tag = this@toProto.tag.toProto()
        count = this@toProto.count
    }

fun AnalyzeResultProto.toDomainModel(): AnalyzeResult =
    AnalyzeResult(
        this.totalCount,
        this.successCount,
        this.tagCountsList.map(TagCountProto::toDomainModel)
    )

fun AnalyzeResult.toProto(): AnalyzeResultProto =
    AnalyzeResultProto {
        totalCount = this@toProto.totalCount
        successCount = this@toProto.successCount
        addAllTagCounts(this@toProto.tagCounts.map(TagCount::toProto))
    }
