package com.micles92.importer.model.entity;

import javax.persistence.*;

/**
 * Created by mlesniak on 2017-09-14.
 */
@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "AUTHORITY")
    private String userAuthority;

    public enum USER_AUTHORITY  { ADMIN, USER, MODERATOR};

    public User() {
    }

    public User(String login, String password, String email, String userAuthority) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userAuthority = userAuthority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAuthority() {
        return userAuthority;
    }

    public void setUserAuthority(String userAuthority) {
        this.userAuthority = userAuthority;
    }

}
