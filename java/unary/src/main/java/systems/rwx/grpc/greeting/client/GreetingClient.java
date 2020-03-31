package systems.rwx.grpc.greeting.client;

import com.proto.device.CommandServiceGrpc;
import com.proto.device.Device;
import com.proto.device.DeviceCommand;
import com.proto.device.DeviceFeedback;
import com.proto.dummy.DummyServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("hell grpc client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        System.out.println("creating stub");
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
//        DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        CommandServiceGrpc.CommandServiceBlockingStub commandClient = CommandServiceGrpc.newBlockingStub(channel);

        Device device = Device.newBuilder()
                .setTag("T01")
                .setValue(567)
                .build();

        DeviceCommand deviceCommand = DeviceCommand.newBuilder()
                .setDevice(device)
                .build();


        DeviceFeedback deviceFeedback = commandClient.command(deviceCommand);

        System.out.println(deviceFeedback.getStatus());

        // TODO
        System.out.println("shutting down");
        channel.shutdown();


    }
}
