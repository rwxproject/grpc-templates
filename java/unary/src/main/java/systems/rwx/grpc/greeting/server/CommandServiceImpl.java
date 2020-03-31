package systems.rwx.grpc.greeting.server;

import com.proto.device.CommandServiceGrpc;
import com.proto.device.Device;
import com.proto.device.DeviceCommand;
import com.proto.device.DeviceFeedback;
import io.grpc.stub.StreamObserver;

public class CommandServiceImpl extends CommandServiceGrpc.CommandServiceImplBase {

    @Override
    public void command(DeviceCommand request, StreamObserver<DeviceFeedback> responseObserver) {
        Device device = request.getDevice();
        String tag = device.getTag();
        // Create response
        int value = device.getValue();
        // TODO business logic implementation
        int status = value+1;
        DeviceFeedback feedback = DeviceFeedback.newBuilder()
                .setStatus(status)
                .build();
        // Create response
        responseObserver.onNext(feedback);
        // Complete RPC call
        responseObserver.onCompleted();
    }
}
