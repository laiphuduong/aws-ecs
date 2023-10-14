package com.example.awsecs.service;

import com.example.awsecs.proto.HelloRequest;
import com.example.awsecs.proto.HelloResponse;
import com.example.awsecs.proto.HelloServiceGrpc;
import com.example.awsecs.proto.LoginResponse;
import com.example.awsecs.proto.UserRequest;

import org.springframework.stereotype.Service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        String greeting = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append(" ")
                .append(request.getLastName())
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greeting)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void login(UserRequest request, StreamObserver<LoginResponse> responseObserver) {
    }
}
