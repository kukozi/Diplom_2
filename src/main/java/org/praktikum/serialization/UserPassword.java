package org.praktikum.serialization;

public class UserPassword {

    private String password;

    public UserPassword(String password) {
        this.password = password;
    }

    public UserPassword() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
