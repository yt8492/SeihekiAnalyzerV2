package com.yt8492.seihekianalyzerv2.ui.analyze

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AnalyzeFragmentModule {

    @ContributesAndroidInjector
    fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    fun analyzeResultFragment(): AnalyzeResultFragment
}