package cn.edu.xctf.pm.wechatproject.dao;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.edu.xctf.pm.wechatproject.db.User;


/**
 * 数据库操作语句
 * @Author pangmin
 * @Date 2020/11/22 2:57
 **/
public class SqlManager {

    /**
     * 通过用户名查找用户
     * @param username 用户名
     * @return  User对象
     */
    public User findUserByUName(String username) {
        List<User> users = DataSupport.findAll(User.class);
        User reUser = new User();
        for (User user : users) {
            if (username.equals(user.getUsername())) {
                Log.v("query", "username:" + user.getUsername());
                Log.v("query", "password" + user.getPassword());
                reUser = user;break;
            }
            else {
                user.setId(0);
                user.setUsername(null);
                user.setPassword(null);
                Log.v("query", "username:" + user.getUsername());
                Log.v("query", "password" + user.getPassword());
                reUser = user;
            }
        }
        return reUser;
    }

    /**
     * 通过查找用户名返回结果
     * @param username 用户名
     * @return boolean型判断值
     */
    public boolean findResultByUName(String username) {
        boolean judge = false;
        List<User> users = DataSupport.findAll(User.class);
        for (User user : users) {
            Log.v("query", "username:" + user.getUsername());
            Log.v("query", "password" + user.getPassword());
            if (username.equals(user.getUsername())) {
                judge = false;break;
            } else {
                judge = true;
            }
        }
        return judge;
    }

    /**
     * 添加用户
     * @param username 用户名
     * @param password 用户密码
     */
    public void insertUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.save();
    }

    /**
     * 本方法可单独更改数据，查找原数据填入即可
     * 通过用户id更新用户数据
     * @param id 用户id
     * @param updateId 更改id
     * @param updateUName 更改用户名
     * @param updatePwd 更改密码
     */
    public void updateUser(int id, int updateId, String updateUName, String updatePwd) {
        User user = new User();
        user = DataSupport.find(User.class, id);
        user.setId(updateId);
        user.setUsername(updateUName);
        user.setPassword(updatePwd);
        user.save();
    }

    /**
     * 通过id删除用户
     * @param id 用户id
     */
    public void deleteUser(int id) {
        DataSupport.delete(User.class, id);
    }
}
