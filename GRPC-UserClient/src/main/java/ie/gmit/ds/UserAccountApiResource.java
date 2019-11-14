package ie.gmit.ds;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserAccountApiResource {

    private HashMap<Integer, UserAccount> userAccounts = new HashMap<Integer, UserAccount>();

    public UserAccountApiResource(){
        UserAccount testUser1 = new UserAccount(01, "qwe", "qwe@qwe.com", "qwe");
        UserAccount testUser2 = new UserAccount(02, "asd", "asd@asd.com", "asd");
        userAccounts.put(testUser1.userID, testUser1);
        userAccounts.put(testUser2.userID, testUser2);
    }

    @GET
    public Collection<UserAccount> getUsers() {
        return userAccounts.values();
    }
    @GET
    @Path("{userID}")
    public UserAccount getUserById(@PathParam("userID") Integer userID) {
        return userAccounts.get(userID);
    }

}
