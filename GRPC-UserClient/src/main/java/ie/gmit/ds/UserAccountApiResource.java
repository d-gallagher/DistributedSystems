package ie.gmit.ds;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.io.Console;
import java.util.Collection;
import java.util.HashMap;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserAccountApiResource {

    private final UserClient client;

    private final UserAccountDB userAccounts;

    public UserAccountApiResource(){
        userAccounts = UserAccountDB.getInstance();
        client  = UserClient.getInstance();
        // Add some test users to the database
        populateDB();
    }

    @POST
    @Path("login")
    @Consumes( MediaType.APPLICATION_JSON)
    public Response loginUser(UserAccount acc) {

        UserAccount login = userAccounts.getUserById(acc.getUserID());

        if (login == null) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
                    .entity("Error! Please Try Again...").build();
        }

        //String userPassword, byte[] hashedPassword, byte[] salt
//        HashResult result = login.getHashResult();
//        boolean userLogIn = client.validate(acc.getPassword(), result.getHashedPw(), result.getSalt());

//        if(userLogIn){
//            return Response.status(Status.ACCEPTED).entity("Login Successful. " + login.getUserName())
//                    .build();
//        }else{
        return  Response.status(Status.BAD_REQUEST).entity("Login Error.. Incorrect data. ").build();
//        }
    }

    @GET
    public Collection<UserAccount> getUsers() {
        return userAccounts.getUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserAccount(UserAccount acc){

        // Perform createUser
        userAccounts.addNewUser(setHashes(acc));

        return Response.status(Status.CREATED).type(MediaType.TEXT_PLAIN).entity("UserAccount Created for "+acc.getUserName()+".").build();
        
    }

    @GET
    @Path("{id}")
    public UserAccount getUserById(@PathParam("id") int id) {
        return userAccounts.getUserById(id);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserAccount(@PathParam("id") int id, UserAccount acc){

        UserAccount accToUpdate = userAccounts.getUserById(id);
        // Found the Account
        if(accToUpdate != null){
            // Update user password if they send one
            if(acc.getPassword() != null){
                acc = setHashes(acc);
            }
            userAccounts.updateUserAccount(id, acc);

            // RETURN 200 - User found and deleted
            return Response.ok().build();
        }

        // RETURN 404 - No User Found
        return Response.status(Status.NOT_FOUND).build();

    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteUserAccount(@PathParam("id") int id){

        UserAccount accToDelete = userAccounts.getUserById(id);
        if(accToDelete!=null){
            userAccounts.deleteUserAccountById(id);

            // RETURN 200 - User found and deleted
            return Response.ok().build();
        }

        // RETURN 404 - No User Found
        return Response.status(Status.NOT_FOUND).build();
        
    }

    // Set userAccount hash for PW/Salt
    private UserAccount setHashes(UserAccount acc){
        // Create New Hash for the user PW/Salt
        HashResult result = client.sendHashRequest(acc.getUserID(), acc.getPassword());
        // Set Hash and Salt on user account
        acc.hashedPassword = result.getHashedPw();
        acc.salt = result.getSalt();

        return acc;
    }

    private void populateDB(){
        userAccounts.addNewUser(setHashes(new UserAccount(01, "qwe", "qwe@qwe.com", "qwe")));
        userAccounts.addNewUser(setHashes(new UserAccount(02, "asd", "asd@asd.com", "asd")));
    }
}
