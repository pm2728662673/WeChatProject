package cn.edu.xctf.pm.wechatproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.xctf.pm.wechatproject.dao.SqlManager;
import cn.edu.xctf.pm.wechatproject.db.User;
import cn.edu.xctf.pm.wechatproject.service.CheckService;

/**
 * 登录主界面
 * @Author pangmin
 * @Date 2020/11/22 08:23
 */
public class IndexActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et_name;

    EditText et_pwd;

    Button btn_login;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_index);

        et_name = findViewById(R.id.et_username);
        et_name.setOnClickListener(this);

        et_pwd = findViewById(R.id.et_password);
        et_pwd.setOnClickListener(this);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlManager sm = new SqlManager();
                CheckService cs = new CheckService();
                String name = et_name.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                boolean judge_uNameIsEmpty = cs.checkUNameIsEmpty(name);
                boolean judge_uName = sm.findResultByUName(name);
                boolean judge_pwdIsEmpty = cs.checkPwdIsEmpty(pwd);
                boolean judge_pwd = cs.checkPwdMatchName(name, pwd);
                if (!judge_uNameIsEmpty) {
                    Toast.makeText(IndexActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (!judge_pwdIsEmpty) {
                    Toast.makeText(IndexActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (judge_uName) {
                    Toast.makeText(IndexActivity.this, "用户不存在", Toast.LENGTH_SHORT).show();
                }
                else if (!judge_pwd) {
                    Toast.makeText(IndexActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(IndexActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(IndexActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        register = findViewById(R.id.register_redirect);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}