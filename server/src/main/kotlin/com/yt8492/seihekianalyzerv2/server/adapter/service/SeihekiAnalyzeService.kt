package com.yt8492.seihekianalyzerv2.server.adapter.service

import com.yt8492.seihekianalyzerv2.proto.AnalyzeResult as AnalyzeResultProto
import com.yt8492.seihekianalyzerv2.proto.SeihekiAnalyzerCoroutineGrpc
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeResult
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import com.yt8492.seihekianalyzerv2.proto.Url as UrlProto
import com.yt8492.seihekianalyzerv2.server.adapter.converter.toDomainModel
import com.yt8492.seihekianalyzerv2.server.adapter.converter.toProto
import com.yt8492.seihekianalyzerv2.proto.Urls as UrlsProto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SeihekiAnalyzeService(
    private val seihekiAnalyzeUseCase: SeihekiAnalyzeUseCase
) : SeihekiAnalyzerCoroutineGrpc.SeihekiAnalyzerImplBase() {
    override val initialContext: CoroutineContext
        get() = Dispatchers.Default + Job()

    override suspend fun analyze(request: UrlsProto): AnalyzeResultProto {
        println("analyze called")
        println("request size: ${request.urlsCount}")
        val urls = request.urlsList.map(UrlProto::toDomainModel)
        return when (val result = seihekiAnalyzeUseCase.execute(urls)) {
            is SeihekiAnalyzeResult.Success -> {
                println("analyze success")
                result.result.toProto()
            }
            is SeihekiAnalyzeResult.Failure -> {
                result.cause.printStackTrace()
                throw result.cause
            }
        }
    }
}