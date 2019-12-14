package com.yt8492.seihekianalyzerv2.server

import io.grpc.ServerBuilder

fun main() {
    println("start server")
    val port = 50051
    val server = ServerBuilder.forPort(port)
        .build()
        .start()
    Runtime.getRuntime().addShutdownHook(Thread {
        server.shutdown()
        println("server shutdown")
    })
    server.awaitTermination()
}