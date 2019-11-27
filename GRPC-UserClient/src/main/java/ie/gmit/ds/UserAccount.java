package ie.gmit.ds;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UserAccount {

    //User Account credentials
    @NotNull
    int userID;
    @NotNull
    String userName;
    @NotNull
    String email;
    @NotNull
    String password;

    // Auth Credentials
    byte[] hashedPassword;
    byte[] salt;

    public UserAccount(/* Empty Constructor for Jackson deserialisation */){}

    // Add New Account
    public UserAccount(int userID, String userName, String email, String password) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    // login user
    public UserAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty
    public int getUserID() {
        return userID;
    }

    @JsonProperty
    public String getUserName() {
        return userName;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
//    @JsonIgnore //Ignore causes issues with parsing the json
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    @JsonProperty
    public byte[] getSalt() {
        return salt;
    }

    public void setHashedPassword(byte[] hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
