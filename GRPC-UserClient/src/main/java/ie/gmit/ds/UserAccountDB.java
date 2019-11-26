package ie.gmit.ds;

import java.util.Collection;
import java.util.HashMap;

public class UserAccountDB {

    private static UserAccountDB userAccountDBInstance;
    private static HashMap<Integer, UserAccount> userAccDB = new HashMap<>();

    private UserAccountDB(){
    }

    public static UserAccountDB getInstance(){
        if(userAccountDBInstance ==null){
            userAccountDBInstance = new UserAccountDB();
        }
        return  userAccountDBInstance;
    }

    public void addNewUser(UserAccount acc){userAccDB.put(acc.getUserID(), acc);}

    public Collection<UserAccount> getUsers() {
        return userAccDB.values();
    }

    public UserAccount getUserById(int userID) {
        return userAccDB.get(userID);
    }

    public void deleteUserAccount(UserAccount acc){ userAccDB.remove(acc.getUserID());}

    public void deleteUserAccountById(int accId){ userAccDB.remove(accId);}

    public void updateUserAccount(int id, UserAccount acc){userAccDB.put(id, acc);}

}
