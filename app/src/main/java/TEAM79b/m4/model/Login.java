package TEAM79b.m4.model;

import java.util.Map;
import java.util.HashMap;

public class Login {
    private static final Login _instance = new Login();
    public static Login getInstance() {
        return _instance;
    }

    private Map<String, String> userAccounts;
    private int totalUsers;

    private Login() {
        userAccounts = new HashMap<>();
    }

    public Map<String, String> getUserAccounts() {
        return userAccounts;
    }

    public void addUser(String email, String pass) {
        userAccounts.put(email, pass);
        totalUsers++;
    }


}
