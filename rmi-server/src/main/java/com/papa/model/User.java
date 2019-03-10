package com.papa.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -5543758116726988621L;

    public long id;
    public String account;
    public String password;

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
