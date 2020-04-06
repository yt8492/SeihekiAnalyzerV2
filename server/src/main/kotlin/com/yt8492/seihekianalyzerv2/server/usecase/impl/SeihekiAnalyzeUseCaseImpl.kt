package com.yt8492.seihekianalyzerv2.server.usecase.impl

import com.yt8492.seihekianalyzerv2.common.domain.model.*
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeResult
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import com.yt8492.seihekianalyzerv2.server.domain.repository.WorkRepository
import java.lang.Exception

class SeihekiAnalyzeUseCaseImpl(
    private val workRepositoryOnDB: WorkRepository,
    private val workRepositoryOnScraper: WorkRepository
) : SeihekiAnalyzeUseCase {
    override suspend fun execute(workNameAndUrls: List<WorkNameAndUrl>): SeihekiAnalyzeResult {
        try {
            val urls = workNameAndUrls.mapNotNull { it.url }
            val worksFromDB = workRepositoryOnDB.findAllByUrls(urls)
            val scrapeRequireUrls = urls.filterNot { url ->
                worksFromDB.any {
                    it.url == url
                }
            }
            val worksFromScraper = workRepositoryOnScraper.findAllByUrls(scrapeRequireUrls)
            worksFromScraper.forEach {
                workRepositoryOnDB.save(it)
            }
            val works = worksFromDB + worksFromScraper
            val tagCounts = works.fold(mutableMapOf<Tag, Int>()) { tagCounts, work ->
                work.tags.forEach { tag ->
                    val count = tagCounts.getOrDefault(tag, 0)
                    tagCounts[tag] = count + 1
                }
                tagCounts
            }.map {
                TagCount(it.key, it.value)
            }.sortedByDescending {
                it.count
            }
            val analyzeResult = AnalyzeResult(workNameAndUrls.size, works.size, tagCounts)
            return SeihekiAnalyzeResult.Success(analyzeResult)
        } catch (e: Exception) {
            return SeihekiAnalyzeResult.Failure(e)
        }
    }
}
