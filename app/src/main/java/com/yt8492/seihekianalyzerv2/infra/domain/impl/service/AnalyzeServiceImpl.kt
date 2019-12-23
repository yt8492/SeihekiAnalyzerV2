package com.yt8492.seihekianalyzerv2.infra.domain.impl.service

import com.yt8492.seihekianalyzerv2.common.domain.model.AnalyzeResult
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.domain.service.AnalyzeService
import com.yt8492.seihekianalyzerv2.infra.converter.toDomainModel
import com.yt8492.seihekianalyzerv2.infra.converter.toProto
import com.yt8492.seihekianalyzerv2.proto.SeihekiAnalyzerCoroutineGrpc

class AnalyzeServiceImpl(
    private val stub: SeihekiAnalyzerCoroutineGrpc.SeihekiAnalyzerCoroutineStub
) : AnalyzeService {
    override suspend fun analyze(urls: List<Url>): AnalyzeResult {
        val urlsProto = urls.toProto()
        val analyzeResult = stub.analyze(urlsProto).toDomainModel()
        return analyzeResult
    }
}