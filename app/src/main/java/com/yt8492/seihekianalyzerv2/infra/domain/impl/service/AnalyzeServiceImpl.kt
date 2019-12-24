package com.yt8492.seihekianalyzerv2.infra.domain.impl.service

import com.yt8492.seihekianalyzerv2.BuildConfig
import com.yt8492.seihekianalyzerv2.common.domain.model.AnalyzeResult
import com.yt8492.seihekianalyzerv2.common.domain.model.Url
import com.yt8492.seihekianalyzerv2.domain.service.AnalyzeService
import com.yt8492.seihekianalyzerv2.infra.converter.toDomainModel
import com.yt8492.seihekianalyzerv2.infra.converter.toProto
import com.yt8492.seihekianalyzerv2.proto.SeihekiAnalyzerCoroutineGrpc
import io.grpc.ManagedChannelBuilder

class AnalyzeServiceImpl : AnalyzeService {
    override suspend fun analyze(urls: List<Url>): AnalyzeResult {
        val channel = ManagedChannelBuilder.forAddress(
            BuildConfig.HOST,
            BuildConfig.PORT
        ).usePlaintext()
            .build()
        val stub = SeihekiAnalyzerCoroutineGrpc.newStub(channel)
        val urlsProto = urls.toProto()
        val analyzeResult = stub.analyze(urlsProto).toDomainModel()
        channel.shutdown()
        return analyzeResult
    }
}