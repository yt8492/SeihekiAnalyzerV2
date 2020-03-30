package com.yt8492.seihekianalyzerv2.server.adapter.service

import com.yt8492.seihekianalyzerv2.proto.AnalyzeResult as AnalyzeResultProto
import com.yt8492.seihekianalyzerv2.proto.SeihekiAnalyzerCoroutineGrpc
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeResult
import com.yt8492.seihekianalyzerv2.common.usecase.analyze.SeihekiAnalyzeUseCase
import com.yt8492.seihekianalyzerv2.proto.WorkNameAndUrl as WorkNameAndUrlProto
import com.yt8492.seihekianalyzerv2.proto.WorkNameAndUrls
import com.yt8492.seihekianalyzerv2.server.adapter.converter.toDomainModel
import com.yt8492.seihekianalyzerv2.server.adapter.converter.toProto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class SeihekiAnalyzeService(
    private val seihekiAnalyzeUseCase: SeihekiAnalyzeUseCase
) : SeihekiAnalyzerCoroutineGrpc.SeihekiAnalyzerImplBase() {
     override val initialContext: CoroutineContext
        get() = Dispatchers.Default + Job()

    override suspend fun analyze(request: WorkNameAndUrls): AnalyzeResultProto {
        println("analyze called")
        println("request size: ${request.workNameAndUrlsCount}")
        val workNameAndUrls = request.workNameAndUrlsList.map(WorkNameAndUrlProto::toDomainModel)
        return when (val result = seihekiAnalyzeUseCase.execute(workNameAndUrls)) {
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