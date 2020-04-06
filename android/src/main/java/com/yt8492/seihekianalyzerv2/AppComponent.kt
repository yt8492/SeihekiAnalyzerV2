package com.yt8492.seihekianalyzerv2

import com.yt8492.seihekianalyzerv2.infra.InfraModule
import com.yt8492.seihekianalyzerv2.ui.analyze.AnalyzeFragmentModule
import com.yt8492.seihekianalyzerv2.usecase.UseCaseModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        InfraModule::class,
        UseCaseModule::class,
        AnalyzeFragmentModule::class
    ]
)
interface AppComponent : AndroidInjector<App>
