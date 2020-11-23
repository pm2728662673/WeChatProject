package cn.edu.xctf.pm.wechatproject.db;

import android.service.autofill.Dataset;

import org.litepal.crud.DataSupport;

/**
 * user
 * @Author pangmin
 * @Date 2020/11/22 1:15
 **/
public class User extends DataSupport {

    private int id;

    private String username;

    private String password;

    public User() {

    }

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
