package TEAM79b.m4.model;

import java.util.Arrays;
import java.util.List;

public class User {
    private String email;
    private String password;
    private UserTypes role;

    public static List<String> rolesList = Arrays.asList("Need User", "Donating User", "Location Employee", "Manager", "Admin", "Delivery Driver");

    // No-arg constructor to make firebase happy :)
    public User() {

    }

    public User(String email, String password, UserTypes role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserTypes role) {
        this.role = role;
    }

    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserTypes getUserType() {
        return role;
    }
}
