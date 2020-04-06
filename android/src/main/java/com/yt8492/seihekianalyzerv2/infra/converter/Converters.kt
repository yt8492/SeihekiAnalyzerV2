package com.yt8492.seihekianalyzerv2.infra.converter

import com.yt8492.seihekianalyzerv2.common.domain.model.*
import com.yt8492.seihekianalyzerv2.proto.AnalyzeResult as AnalyzeResultProto
import com.yt8492.seihekianalyzerv2.proto.Tag as TagProto
import com.yt8492.seihekianalyzerv2.proto.TagCount as TagCountProto
import com.yt8492.seihekianalyzerv2.proto.Url as UrlProto
import com.yt8492.seihekianalyzerv2.proto.Urls
import com.yt8492.seihekianalyzerv2.proto.WorkNameAndUrl as WorkNameAndUrlProto
import com.yt8492.seihekianalyzerv2.proto.WorkNameAndUrls

fun UrlProto.toDomainModel(): Url? = if (this.isExists) {
    Url(this.value)
} else {
    null
}

fun Url?.toProto(): UrlProto =
    UrlProto {
        isExists = this@toProto != null
        value = this@toProto?.value ?: ""
    }

fun WorkNameAndUrl.toProto(): WorkNameAndUrlProto =
    WorkNameAndUrlProto {
        name = this@toProto.name
        this@toProto.url?.toProto()?.let {
            url = it
        }
    }

fun List<WorkNameAndUrl>.toProto(): WorkNameAndUrls =
    WorkNameAndUrls {
        addAllWorkNameAndUrls(this@toProto.map(WorkNameAndUrl::toProto))
    }

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
