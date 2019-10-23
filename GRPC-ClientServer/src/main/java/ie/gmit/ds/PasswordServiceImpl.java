package ie.gmit.ds;

import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;

public class PasswordServiceImpl extends PasswordServiceGrpc.PasswordServiceImplBase {
    /*  Adapted from Adapted from async lab:
        https://github.com/john-french/distributed-systems-labs/tree/master/grpc-async-inventory
        This class overrides the hash and validate methods provided in the proto file
    */
    PasswordServiceImpl(){}

    @Override
    public void hash(HashRequest request, StreamObserver<HashResponse> responseObserver){
        try{
            // Request User Provided Pw, send to char array
            String userPassword = request.getPassword();
            char[] stringToHash = userPassword.toCharArray();

            // Passwords class is using byte arrays to generate salt & hash user pw
            byte[] addSaltToHash = Passwords.getNextSalt();
            byte[] hashedPassword = Passwords.hash(stringToHash, addSaltToHash);

            // https://stackoverflow.com/questions/29018411/google-protobuf-bytestring-vs-byte
            // Copy hashed pw and salt byte[] to immutable ByteStrings
            // Response is built using generated protobuff classes
            responseObserver.onNext(HashResponse.newBuilder().setUserId(request.getUserId())
                    .setHashedPassword(ByteString.copyFrom(hashedPassword))
                    .setSalt(ByteString.copyFrom(addSaltToHash))
                    .build());
        }
        //send default if there is a problem
        catch(RuntimeException ex){
            responseObserver.onNext(HashResponse.newBuilder().getDefaultInstanceForType());
        }
        responseObserver.onCompleted();
    }//end hash

    @Override
    public void validate(ValidationRequest request, StreamObserver<BoolValue> responseObserver){
        try{
            // isExpectedPassword(char[] password, byte[] salt, byte[] expectedHash)
            char[] userPassword = request.getPassword().toCharArray();
            byte[] hashedPassword = request.getHashedPassword().toByteArray();
            byte[] salt = request.getSalt().toByteArray();

            // Validate the hash matches and return true
            if(Passwords.isExpectedPassword(userPassword, salt, hashedPassword)){
                responseObserver.onNext(BoolValue.newBuilder().setValue(true).build());
            }
            // Else return false
            else{
                responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
            }
        }

        catch(RuntimeException ex){
            responseObserver.onNext(BoolValue.newBuilder().setValue(false).build());
        }
    }
}
