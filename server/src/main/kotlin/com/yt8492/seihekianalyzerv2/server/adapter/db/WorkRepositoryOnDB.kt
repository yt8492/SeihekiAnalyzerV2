package com.yt8492.seihekianalyzerv2.server.adapter.db

import com.squareup.sqldelight.Transacter
import com.yt8492.seihekianalyzerv2.server.db.TagQueries
import com.yt8492.seihekianalyzerv2.server.db.UrlQueries
import com.yt8492.seihekianalyzerv2.server.db.UrlTagQueries
import com.yt8492.seihekianalyzerv2.server.db.UrlWithTagQueries
import com.yt8492.seihekianalyzerv2.server.domain.repository.WorkRepository
import com.yt8492.seihekianalyzerv2.common.domain.model.Tag
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work

class WorkRepositoryOnDB(
    private val urlWithTagDB: UrlWithTagQueries,
    private val urlDB: UrlQueries,
    private val tagDB: TagQueries,
    private val urlTagDB: UrlTagQueries
) : WorkRepository {
    override suspend fun findByUrl(url: Url): Work? {
        val urlWithTags = urlWithTagDB.selectAllByUrl(url.value).executeAsList()
        return if (urlWithTags.isNotEmpty()) {
            Work(url, urlWithTags.map { Tag(it.tag) })
        } else {
            null
        }
    }

    override suspend fun findAllByUrls(urls: List<Url>): List<Work> {
        val urlWithTags = urls.flatMap {
            urlWithTagDB.selectAllByUrl(it.value).executeAsList()
        }
        return urlWithTags.groupBy {
            it.url
        }.map {
            val url = Url(it.key)
            val tags = it.value.map { urlWithTag ->
                Tag(urlWithTag.tag)
            }
            Work(url, tags)
        }
    }

    override suspend fun save(work: Work) {
        transaction(urlDB, tagDB, urlTagDB, urlWithTagDB) {
            urlDB.insert(work.url.value)
            val urlId = urlDB.selectByUrl(work.url.value).executeAsOne().id
            work.tags.forEach {
                tagDB.insert(it.value)
                val tagId = tagDB.selectByTag(it.value).executeAsOne().id
                urlTagDB.insert(urlId, tagId)
            }
        }
    }

    private fun transaction(vararg transacters: Transacter, body: Transacter.Transaction.() -> Unit) {
        when {
            transacters.isEmpty() -> {
                throw IllegalArgumentException("transacters must not be empty")
            }
            transacters.size == 1 -> {
                transacters.first().transaction {
                    body()
                }
            }
            else -> {
                val first = transacters.first()
                transacters.drop(1)
                    .let {
                        val trans = it.toTypedArray()
                        first.transaction {
                            transaction(*trans, body = body)
                        }
                    }
            }
        }
    }
}