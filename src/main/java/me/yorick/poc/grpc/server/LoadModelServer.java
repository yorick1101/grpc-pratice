package me.yorick.poc.grpc.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import me.yorick.poc.grpc.ModelLoadRequest;
import me.yorick.poc.grpc.ModelLoadResponse;
import me.yorick.poc.grpc.ModelLoaderGrpc;

public class LoadModelServer {

	public static void main(String[] args) throws IOException, InterruptedException {
	
		int port = 8181;
		ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
		Server server = serverBuilder.addService(new ModelLoaderServer()).build();
		server.start();
		
		Thread.currentThread().join();
	}
	
	private static class ModelLoaderServer extends  ModelLoaderGrpc.ModelLoaderImplBase{
		
		@Override
		public void load(ModelLoadRequest request, StreamObserver<ModelLoadResponse> responseObserver) {
			String id = ModelLoader.load(request.getConfigPath());
			ModelLoadResponse response = ModelLoadResponse.newBuilder().setModelId(id).setSuccess(true).build();
			responseObserver.onNext(response);
			responseObserver.onCompleted();
		}
		
	}
	
	private static class ModelLoader {
		public static String load(String configPath) {
			return "id:"+configPath;
		}
	}
}
