package me.yorick.poc.grpc.client;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;
import me.yorick.poc.grpc.ModelLoadRequest;
import me.yorick.poc.grpc.ModelLoadResponse;
import me.yorick.poc.grpc.ModelLoaderGrpc;
import me.yorick.poc.grpc.ModelLoaderGrpc.ModelLoaderBlockingStub;

public class LoadModelClient {

	public static void main(String[] args) {
		String host = "localhost";
		int port = 8181;
		Channel channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
		ModelLoaderBlockingStub blockingStub = ModelLoaderGrpc.newBlockingStub(channel);
		ModelLoadResponse response = blockingStub.load(ModelLoadRequest.newBuilder().setConfigPath("/opt/yorick").build());
		System.out.println(response.getSuccess()+" "+response.getModelId());
	}

}
