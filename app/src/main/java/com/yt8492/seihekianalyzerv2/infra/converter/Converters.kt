package com.yt8492.seihekianalyzerv2.infra.converter

import com.yt8492.seihekianalyzerv2.common.domain.model.*
import com.yt8492.seihekianalyzerv2.proto.Urls
import com.yt8492.seihekianalyzerv2.proto.Work as WorkProto
import com.yt8492.seihekianalyzerv2.proto.TagCount as TagCountProto
import com.yt8492.seihekianalyzerv2.proto.AnalyzeResult as AnalyzeResultProto
import com.yt8492.seihekianalyzerv2.proto.Tag as TagProto
import com.yt8492.seihekianalyzerv2.proto.Url as UrlProto

fun UrlProto.toDomainModel(): Url =
    Url(this.value)

fun Url.toProto(): UrlProto =
    UrlProto {
        value = this@toProto.value
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
        this.tagCountsList.map(TagCountProto::toDomainModel)
    )

fun AnalyzeResult.toProto(): AnalyzeResultProto =
    AnalyzeResultProto {
        totalCount = this@toProto.totalCount
        addAllTagCounts(this@toProto.tagCounts.map(TagCount::toProto))
    }

fun WorkProto.toDomainModel(): Work =
    Work(
        this.url.toDomainModel(),
        this.tagsList.map(TagProto::toDomainModel)
    )

fun Work.toProto(): WorkProto =
    WorkProto {
        url = this@toProto.url.toProto()
        addAllTags(this@toProto.tags.map(Tag::toProto))
    }