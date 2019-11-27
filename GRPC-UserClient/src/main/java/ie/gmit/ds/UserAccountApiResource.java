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

    private final UserAccountDB userAccountsDB;

    public UserAccountApiResource(){
        userAccountsDB = UserAccountDB.getInstance();
        client  = UserClient.getInstance();
        // Add some test users to the database
        populateDB();
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginUser(UserAccount acc) {
        // Confirm if User Exists in DB - Not Found Error if false
        int providedID = acc.getUserID();
        if(userAccountsDB.getUserById(providedID) == null){
            return Response.status(Status.NOT_FOUND)
                    .entity("Error! Please Try Again...").build();
        }

        // Password passed in from the POST
        String providedPW = acc.getPassword();
        // Get User from the DB
        UserAccount userLogin = userAccountsDB.getUserById(providedID);

        // Found the User - Validate User
        System.out.println("Account "+userLogin.getUserName()+" found");
        System.out.println("Validating.. ");

        // Validate provided user pw against the user from teh DB
        // Validate method params(String userPassword(from POST), byte[] hashedPassword(From DB), byte[] salt(From DB))
        boolean validLogin = client.validate(providedPW, userLogin.getHashedPassword(), userLogin.getSalt());
        System.out.println("Validate "+validLogin);

        // Successful log in
        if(validLogin){
            return Response.status(Status.OK).entity("Log in Successful").build();
        }
        // Unsuccessful login
        else{
            return  Response.status(Status.BAD_REQUEST).entity("Login Error.. Incorrect data. ").build();
        }
    }

    @GET
    public Collection<UserAccount> getUsers() {
        return userAccountsDB.getUsers();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserAccount(UserAccount acc){

        // Perform createUser
        userAccountsDB.addNewUser(setHashes(acc));

        return Response.status(Status.CREATED).type(MediaType.TEXT_PLAIN).entity("UserAccount Created for "+acc.getUserName()+".").build();
        
    }

    @GET
    @Path("{id}")
    public UserAccount getUserById(@PathParam("id") int id) {
        return userAccountsDB.getUserById(id);
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUserAccount(@PathParam("id") int id, UserAccount acc){

        UserAccount accToUpdate = userAccountsDB.getUserById(id);
        // Found the Account
        if(accToUpdate != null){
            // Update user password if they send one
            if(acc.getPassword() != null){
                acc = setHashes(acc);
            }
            userAccountsDB.updateUserAccount(id, acc);

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

        UserAccount accToDelete = userAccountsDB.getUserById(id);
        if(accToDelete!=null){
            userAccountsDB.deleteUserAccountById(id);

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
        userAccountsDB.addNewUser(setHashes(new UserAccount(01, "qwe", "qwe@qwe.com", "qwe")));
        userAccountsDB.addNewUser(setHashes(new UserAccount(02, "asd", "asd@asd.com", "asd")));
    }
}
