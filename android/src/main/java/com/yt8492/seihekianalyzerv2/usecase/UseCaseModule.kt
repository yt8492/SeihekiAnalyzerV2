package com.yt8492.seihekianalyzerv2.usecase

import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import com.yt8492.seihekianalyzerv2.usecase.impl.SeihekiAnalyzeUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideSeihekiAnalyzeUseCase(impl: SeihekiAnalyzeUseCaseImpl): SeihekiAnalyzeUseCase = impl
}