package com.yt8492.seihekianalyzerv2.server

import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import com.yt8492.seihekianalyzerv2.server.adapter.db.WorkRepositoryOnDB
import com.yt8492.seihekianalyzerv2.server.adapter.scrapter.WorkRepositoryOnScraper
import com.yt8492.seihekianalyzerv2.server.adapter.service.SeihekiAnalyzeService
import com.yt8492.seihekianalyzerv2.server.usecase.impl.SeihekiAnalyzeUseCaseImpl
import com.yt8492.seihekianalyzerv2.common.scraper.DLsiteScraperWithJsoup
import io.grpc.ServerBuilder

fun main() {
    println("start server")

    val driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    val database = Database(driver)
    val urlDB = database.urlQueries
    val tagDB = database.tagQueries
    val urlTagDB = database.urlTagQueries
    val urlWithTagDB = database.urlWithTagQueries
    val accountDB = database.accountQueries
    val accountNotificationTokenDB = database.accountNotificationTokenQueries
    val accountWithNotificationDB = database.accountWithNotificationTokenQueries

    val workRepositoryOnDB = WorkRepositoryOnDB(urlWithTagDB, urlDB, tagDB, urlTagDB)
    val workRepositoryOnScraper = WorkRepositoryOnScraper(DLsiteScraperWithJsoup)

    val seihekiAnalyzeUseCase = SeihekiAnalyzeUseCaseImpl(workRepositoryOnDB, workRepositoryOnScraper)

    val seihekiAnalyzeService = SeihekiAnalyzeService(seihekiAnalyzeUseCase)

    val port = 50051
    val server = ServerBuilder.forPort(port)
        .addService(seihekiAnalyzeService)
        .build()
        .start()
    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        println("server shutdown")
    })
    server.awaitTermination()
}