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
public final class SeihekiAnalyzerGrpc {

  private SeihekiAnalyzerGrpc() {}

  public static final String SERVICE_NAME = "api.SeihekiAnalyzer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.yt8492.seihekianalyzerv2.proto.Urls,
      com.yt8492.seihekianalyzerv2.proto.AnalyzeResult> getAnalyzeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Analyze",
      requestType = com.yt8492.seihekianalyzerv2.proto.Urls.class,
      responseType = com.yt8492.seihekianalyzerv2.proto.AnalyzeResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.yt8492.seihekianalyzerv2.proto.Urls,
      com.yt8492.seihekianalyzerv2.proto.AnalyzeResult> getAnalyzeMethod() {
    io.grpc.MethodDescriptor<com.yt8492.seihekianalyzerv2.proto.Urls, com.yt8492.seihekianalyzerv2.proto.AnalyzeResult> getAnalyzeMethod;
    if ((getAnalyzeMethod = SeihekiAnalyzerGrpc.getAnalyzeMethod) == null) {
      synchronized (SeihekiAnalyzerGrpc.class) {
        if ((getAnalyzeMethod = SeihekiAnalyzerGrpc.getAnalyzeMethod) == null) {
          SeihekiAnalyzerGrpc.getAnalyzeMethod = getAnalyzeMethod =
              io.grpc.MethodDescriptor.<com.yt8492.seihekianalyzerv2.proto.Urls, com.yt8492.seihekianalyzerv2.proto.AnalyzeResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Analyze"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yt8492.seihekianalyzerv2.proto.Urls.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.yt8492.seihekianalyzerv2.proto.AnalyzeResult.getDefaultInstance()))
              .setSchemaDescriptor(new SeihekiAnalyzerMethodDescriptorSupplier("Analyze"))
              .build();
        }
      }
    }
    return getAnalyzeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SeihekiAnalyzerStub newStub(io.grpc.Channel channel) {
    return new SeihekiAnalyzerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SeihekiAnalyzerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SeihekiAnalyzerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SeihekiAnalyzerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SeihekiAnalyzerFutureStub(channel);
  }

  /**
   */
  public static abstract class SeihekiAnalyzerImplBase implements io.grpc.BindableService {

    /**
     */
    public void analyze(com.yt8492.seihekianalyzerv2.proto.Urls request,
        io.grpc.stub.StreamObserver<com.yt8492.seihekianalyzerv2.proto.AnalyzeResult> responseObserver) {
      asyncUnimplementedUnaryCall(getAnalyzeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAnalyzeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.yt8492.seihekianalyzerv2.proto.Urls,
                com.yt8492.seihekianalyzerv2.proto.AnalyzeResult>(
                  this, METHODID_ANALYZE)))
          .build();
    }
  }

  /**
   */
  public static final class SeihekiAnalyzerStub extends io.grpc.stub.AbstractStub<SeihekiAnalyzerStub> {
    private SeihekiAnalyzerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SeihekiAnalyzerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeihekiAnalyzerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SeihekiAnalyzerStub(channel, callOptions);
    }

    /**
     */
    public void analyze(com.yt8492.seihekianalyzerv2.proto.Urls request,
        io.grpc.stub.StreamObserver<com.yt8492.seihekianalyzerv2.proto.AnalyzeResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAnalyzeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SeihekiAnalyzerBlockingStub extends io.grpc.stub.AbstractStub<SeihekiAnalyzerBlockingStub> {
    private SeihekiAnalyzerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SeihekiAnalyzerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeihekiAnalyzerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SeihekiAnalyzerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.yt8492.seihekianalyzerv2.proto.AnalyzeResult analyze(com.yt8492.seihekianalyzerv2.proto.Urls request) {
      return blockingUnaryCall(
          getChannel(), getAnalyzeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SeihekiAnalyzerFutureStub extends io.grpc.stub.AbstractStub<SeihekiAnalyzerFutureStub> {
    private SeihekiAnalyzerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SeihekiAnalyzerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SeihekiAnalyzerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SeihekiAnalyzerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.yt8492.seihekianalyzerv2.proto.AnalyzeResult> analyze(
        com.yt8492.seihekianalyzerv2.proto.Urls request) {
      return futureUnaryCall(
          getChannel().newCall(getAnalyzeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ANALYZE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SeihekiAnalyzerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SeihekiAnalyzerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ANALYZE:
          serviceImpl.analyze((com.yt8492.seihekianalyzerv2.proto.Urls) request,
              (io.grpc.stub.StreamObserver<com.yt8492.seihekianalyzerv2.proto.AnalyzeResult>) responseObserver);
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

  private static abstract class SeihekiAnalyzerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SeihekiAnalyzerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.yt8492.seihekianalyzerv2.proto.SeihekiAnalyzerProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SeihekiAnalyzer");
    }
  }

  private static final class SeihekiAnalyzerFileDescriptorSupplier
      extends SeihekiAnalyzerBaseDescriptorSupplier {
    SeihekiAnalyzerFileDescriptorSupplier() {}
  }

  private static final class SeihekiAnalyzerMethodDescriptorSupplier
      extends SeihekiAnalyzerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SeihekiAnalyzerMethodDescriptorSupplier(String methodName) {
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
      synchronized (SeihekiAnalyzerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SeihekiAnalyzerFileDescriptorSupplier())
              .addMethod(getAnalyzeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
