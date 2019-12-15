package com.yt8492.seihekianalyzerv2.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.25.0)",
    comments = "Source: api.proto")
public final class RecommendServiceGrpc {

  private RecommendServiceGrpc() {}

  public static final String SERVICE_NAME = "api.RecommendService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.yt8492.seihekianalyzerv2.proto.UserNotificationToken,
      com.yt8492.seihekianalyzerv2.proto.Empty> getRegisterTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterToken",
      requestType = com.yt8492.seihekianalyzerv2.proto.UserNotificationToken.class,
      responseType = com.yt8492.seihekianalyzerv2.proto.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.yt8492.seihekianalyzerv2.proto.UserNotificationToken,
      com.yt8492.seihekianalyzerv2.proto.Empty> getRegisterTokenMethod() {
    io.grpc.MethodDescriptor<com.yt8492.seihekianalyzerv2.proto.UserNotificationToken, com.yt8492.seihekianalyzerv2.proto.Empty> getRegisterTokenMethod;
    if ((getRegisterTokenMethod = RecommendServiceGrpc.getRegisterTokenMethod) == null) {
      synchronized (RecommendServiceGrpc.class) {
        if ((getRegisterTokenMethod = RecommendServiceGrpc.getRegisterTokenMethod) == null) {
          RecommendServiceGrpc.getRegisterTokenMethod = getRegisterTokenMethod =
              io.grpc.MethodDescriptor.<com.yt8492.seihekianalyzerv2.proto.UserNotificationToken, com.yt8492.seihekianalyzerv2.proto.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yt8492.seihekianalyzerv2.proto.UserNotificationToken.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yt8492.seihekianalyzerv2.proto.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new RecommendServiceMethodDescriptorSupplier("RegisterToken"))
              .build();
        }
      }
    }
    return getRegisterTokenMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static RecommendServiceStub newStub(io.grpc.Channel channel) {
    return new RecommendServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static RecommendServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new RecommendServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static RecommendServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new RecommendServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class RecommendServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerToken(com.yt8492.seihekianalyzerv2.proto.UserNotificationToken request,
        io.grpc.stub.StreamObserver<com.yt8492.seihekianalyzerv2.proto.Empty> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterTokenMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.yt8492.seihekianalyzerv2.proto.UserNotificationToken,
                com.yt8492.seihekianalyzerv2.proto.Empty>(
                  this, METHODID_REGISTER_TOKEN)))
          .build();
    }
  }

  /**
   */
  public static final class RecommendServiceStub extends io.grpc.stub.AbstractStub<RecommendServiceStub> {
    private RecommendServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RecommendServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommendServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RecommendServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerToken(com.yt8492.seihekianalyzerv2.proto.UserNotificationToken request,
        io.grpc.stub.StreamObserver<com.yt8492.seihekianalyzerv2.proto.Empty> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterTokenMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class RecommendServiceBlockingStub extends io.grpc.stub.AbstractStub<RecommendServiceBlockingStub> {
    private RecommendServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RecommendServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommendServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RecommendServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.yt8492.seihekianalyzerv2.proto.Empty registerToken(com.yt8492.seihekianalyzerv2.proto.UserNotificationToken request) {
      return blockingUnaryCall(
          getChannel(), getRegisterTokenMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class RecommendServiceFutureStub extends io.grpc.stub.AbstractStub<RecommendServiceFutureStub> {
    private RecommendServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private RecommendServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected RecommendServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new RecommendServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.yt8492.seihekianalyzerv2.proto.Empty> registerToken(
        com.yt8492.seihekianalyzerv2.proto.UserNotificationToken request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterTokenMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_TOKEN = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final RecommendServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(RecommendServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_TOKEN:
          serviceImpl.registerToken((com.yt8492.seihekianalyzerv2.proto.UserNotificationToken) request,
              (io.grpc.stub.StreamObserver<com.yt8492.seihekianalyzerv2.proto.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class RecommendServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    RecommendServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.yt8492.seihekianalyzerv2.proto.SeihekiAnalyzerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("RecommendService");
    }
  }

  private static final class RecommendServiceFileDescriptorSupplier
      extends RecommendServiceBaseDescriptorSupplier {
    RecommendServiceFileDescriptorSupplier() {}
  }

  private static final class RecommendServiceMethodDescriptorSupplier
      extends RecommendServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    RecommendServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (RecommendServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new RecommendServiceFileDescriptorSupplier())
              .addMethod(getRegisterTokenMethod())
              .build();
        }
      }
    }
    return result;
  }
}
