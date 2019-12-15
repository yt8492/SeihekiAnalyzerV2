// THIS IS AN AUTOGENERATED FILE. DO NOT EDIT THIS FILE DIRECTLY.
package com.yt8492.seihekianalyzerv2.proto

import com.github.marcoferrer.krotoplus.coroutines.StubDefinition
import com.github.marcoferrer.krotoplus.coroutines.client.clientCallUnary
import com.github.marcoferrer.krotoplus.coroutines.server.ServiceScope
import com.github.marcoferrer.krotoplus.coroutines.server.serverCallUnary
import com.github.marcoferrer.krotoplus.coroutines.server.serverCallUnimplementedUnary
import com.github.marcoferrer.krotoplus.coroutines.withCoroutineContext
import io.grpc.BindableService
import io.grpc.CallOptions
import io.grpc.Channel
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.stub.AbstractStub
import io.grpc.stub.StreamObserver
import io.grpc.stub.annotations.RpcMethod
import javax.annotation.Generated
import kotlin.String
import kotlin.Unit
import kotlin.jvm.JvmStatic

@Generated(
        value = ["by Kroto+ Proto-c Grpc Coroutines Plugin (version 0.5.0)"],
        comments = "Source: api.proto"
)
object RecommendServiceCoroutineGrpc {
    const val SERVICE_NAME: String = RecommendServiceGrpc.SERVICE_NAME

    @JvmStatic
    @get:RpcMethod(
            fullMethodName = "$SERVICE_NAME/RegisterToken",
            requestType = UserNotificationToken::class,
            responseType = Empty::class,
            methodType = MethodDescriptor.MethodType.UNARY
    )
    val registerTokenMethod: MethodDescriptor<UserNotificationToken, Empty>
        get() = RecommendServiceGrpc.getRegisterTokenMethod()

    fun newStub(channel: Channel): RecommendServiceCoroutineStub =
            RecommendServiceCoroutineStub.newStub(channel)
    suspend fun newStubWithContext(channel: Channel): RecommendServiceCoroutineStub =
            RecommendServiceCoroutineStub.newStubWithContext(channel)
    class RecommendServiceCoroutineStub private constructor(channel: Channel, callOptions:
            CallOptions = CallOptions.DEFAULT) :
            AbstractStub<RecommendServiceCoroutineStub>(channel, callOptions) {
        override fun build(channel: Channel, callOptions: CallOptions):
                RecommendServiceCoroutineStub = RecommendServiceCoroutineStub(channel,callOptions)

        suspend fun registerToken(request: UserNotificationToken =
                UserNotificationToken.getDefaultInstance()): Empty = clientCallUnary(request,
                RecommendServiceGrpc.getRegisterTokenMethod())

        suspend inline fun registerToken(block: UserNotificationToken.Builder.() -> Unit): Empty {
            val request = UserNotificationToken.newBuilder()
                .apply(block)
                .build()
            return registerToken(request)
        }

        companion object : StubDefinition<RecommendServiceCoroutineStub> {
            override val serviceName: String = RecommendServiceGrpc.SERVICE_NAME

            override fun newStub(channel: Channel): RecommendServiceCoroutineStub =
                    RecommendServiceCoroutineStub(channel)
            override suspend fun newStubWithContext(channel: Channel): RecommendServiceCoroutineStub
                    = RecommendServiceCoroutineStub(channel).withCoroutineContext()}
    }

    abstract class RecommendServiceImplBase : BindableService, ServiceScope {
        private val delegate: ServiceDelegate = ServiceDelegate()

        override fun bindService(): ServerServiceDefinition = delegate.bindService()
        open suspend fun registerToken(request: UserNotificationToken): Empty =
                serverCallUnimplementedUnary(RecommendServiceGrpc.getRegisterTokenMethod())

        private inner class ServiceDelegate : RecommendServiceGrpc.RecommendServiceImplBase() {
            override fun registerToken(request: UserNotificationToken, responseObserver:
                    StreamObserver<Empty>) {
                serverCallUnary(RecommendServiceGrpc.getRegisterTokenMethod(),responseObserver) {
                    registerToken(request)
                }
            }
        }
    }
}
