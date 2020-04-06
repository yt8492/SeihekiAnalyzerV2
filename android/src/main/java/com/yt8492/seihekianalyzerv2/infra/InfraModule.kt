package com.yt8492.seihekianalyzerv2.infra

import com.yt8492.seihekianalyzerv2.common.scraper.DLsiteScraper
import com.yt8492.seihekianalyzerv2.common.scraper.DLsiteScraperWithJsoup
import com.yt8492.seihekianalyzerv2.domain.service.AnalyzeService
import com.yt8492.seihekianalyzerv2.infra.domain.impl.service.AnalyzeServiceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InfraModule {

    @Singleton
    @Provides
    fun provideAnalyzeService(): AnalyzeService = AnalyzeServiceImpl()

    @Singleton
    @Provides
    fun provideDLsiteScraper(): DLsiteScraper = DLsiteScraperWithJsoup
}
