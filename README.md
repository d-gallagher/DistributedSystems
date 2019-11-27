"# DistributedSystems" 

QuickNote on Running UserClient/Password Service:

Execute Jar command in UserClient:

java -jar target/grpcUserClient-1.0-SNAPSHOT.jar server userAccountApiConfig.yaml

Execute Jar command in PasswordService:

java -jar target\grpc-passwordService-1.0-SNAPSHOT-jar-with-dependencies.jar
