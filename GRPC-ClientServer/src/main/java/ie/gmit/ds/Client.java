package ie.gmit.ds;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Client {

    private static final Logger logger =
            Logger.getLogger(Client.class.getName());
    private final ManagedChannel channel;
    private final PasswordServiceGrpc.PasswordServiceStub asyncInventoryService;
    private final PasswordServiceGrpc.PasswordServiceBlockingStub syncInventoryService;

    public Client(String host, int port) {
        channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        syncInventoryService = PasswordServiceGrpc.newBlockingStub(channel);
        asyncInventoryService = PasswordServiceGrpc.newStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);

    }

    /*
        Need to get user input for id/pw as in specs
        > store in variables for validation
        Need to perform pw validation
        Client will call user input and validation in main
     */
    public static void main(String[] args) throws Exception {
        Client client = new Client("localhost", 50551);
//        Item newItem = Item.newBuilder()
//                .setId("1234")
//                .setName("New Item")
//                .setDescription("Best New Item")
//                .build();
        try {
//            client.addNewInventoryItem(newItem);
//            client.getItems();
        } finally {
            // Don't stop process, keep alive to receive async response
            Thread.currentThread().join();
        }
    }
}
