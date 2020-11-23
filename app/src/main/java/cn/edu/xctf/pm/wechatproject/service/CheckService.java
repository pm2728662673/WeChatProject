package cn.edu.xctf.pm.wechatproject.service;

import cn.edu.xctf.pm.wechatproject.dao.SqlManager;
import cn.edu.xctf.pm.wechatproject.db.User;

/**
 * 本类用于处理判断逻辑
 * @Author pangmin
 * @Date 2020/11/22 5:10
 **/
public class CheckService {
    SqlManager sm = new SqlManager();

    /**
     * 判断密码与验证密码是否一致
     * @param password 用户密码
     * @param rePwd 验证密码
     * @return 返回boolean判断值
     */
    public boolean checkPwd(String password, String rePwd) {
        if (!(password == null && rePwd == null)) {
            if (password.equals(rePwd)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 判断密码是否为空
     * @param password 用户密码
     * @return 返沪boolean型判断值
     */
    public boolean checkPwdIsEmpty(String password) {
        boolean judge = false;
        if (password == null || "".equals(password)) {
            judge = false;
        } else {
            judge = true;
        }
        return judge;
    }

    /**
     * 判断用户密码与验证密码是否为空
     * @param password 用户密码
     * @param rePwd 验证密码
     * @return 返回boolean型判断值
     */
    public boolean checkTwoPwdIsEmpty(String password, String rePwd) {
        boolean judge = false;
        if (password == null || rePwd == null || "".equals(password) || "".equals(rePwd)) {
            judge = false;
        } else {
            judge = true;
        }
        return judge;
    }

    /**
     * 判断用户名是否为空
     * @param username 用户名
     * @return 返回boolean型判断值
     */
    public boolean checkUNameIsEmpty(String username) {
        boolean judge = false;
        if (username == null || "".equals(username)) {
            judge = false;
        } else {
            judge = true;
        }
        return judge;
    }

    /**
     * 判断用户名与密码是否匹配
     * @param username 用户名
     * @param password 用户密码
     * @return 返回boolean型判断值
     */
    public boolean checkPwdMatchName(String username, String password) {
        User user = sm.findUserByUName(username);
        boolean judge = false;
        if (password.equals(user.getPassword())) {
            judge = true;
        } else {
            judge = false;
        }
        return judge;
    }
}
