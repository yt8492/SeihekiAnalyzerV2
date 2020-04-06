package com.yt8492.seihekianalyzerv2.server.adapter.db

import com.squareup.sqldelight.Transacter
import com.yt8492.seihekianalyzerv2.common.domain.model.Tag
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.common.domain.model.Work
import com.yt8492.seihekianalyzerv2.server.db.*
import com.yt8492.seihekianalyzerv2.server.domain.repository.WorkRepository

class WorkRepositoryOnDB(
    private val workDB: WorkQueries,
    private val workUrlDB: WorkUrlQueries,
    private val urlDB: UrlQueries,
    private val tagDB: TagQueries,
    private val urlTagDB: UrlTagQueries
) : WorkRepository {
    override suspend fun findByUrl(url: Url): Work.OnSale? {
        val work = workDB.selectAllByUrlWithUrlAndTag(url.value).executeAsList()
        return if (work.isNotEmpty()) {
            Work.OnSale(
                work.first().name,
                url,
                work.map {
                    Tag(it.tag)
                }
            )
        } else {
            null
        }
    }

    override suspend fun findAllByUrls(urls: List<Url>): List<Work.OnSale> {
        val urlWithTags = urls.flatMap {
            workDB.selectAllByUrlWithUrlAndTag(it.value).executeAsList()
        }
        return urlWithTags.groupBy {
            it.url
        }.map {
            val name = it.value.first().name
            val url = Url(it.key)
            val tags = it.value.map { urlWithTag ->
                Tag(urlWithTag.tag)
            }
            Work.OnSale(
                name,
                url,
                tags
            )
        }
    }

    override suspend fun save(work: Work) {
        transaction(workDB, workUrlDB, urlDB, urlTagDB, tagDB) {
            when (work) {
                is Work.OnSale -> {
                    workDB.insert(work.name, true)
                    val workEntity = workDB.selectByName(work.name).executeAsOne()
                    urlDB.insert(work.url.value)
                    val urlEntity = urlDB.selectByUrl(work.url.value).executeAsOne()
                    val tagEntities = work.tags.map {
                        tagDB.insert(it.value)
                        tagDB.selectByTag(it.value).executeAsOne()
                    }
                    workUrlDB.insert(workEntity.id, urlEntity.id)
                    tagEntities.forEach {
                        urlTagDB.insert(urlEntity.id, it.id)
                    }
                }
                is Work.Discontinued -> {
                    workDB.insert(work.name, false)
                }
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
