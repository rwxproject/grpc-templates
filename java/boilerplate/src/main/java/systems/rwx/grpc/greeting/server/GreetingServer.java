package systems.rwx.grpc.greeting.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GreetingServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("hell grpc server");


        Server server = ServerBuilder.forPort(50051)
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            System.out.println("received shutdown request");
            server.shutdown();
            System.out.println("successfully shutdown");
        }));

        server.awaitTermination();
    }
}
