package cn.edu.xctf.pm.wechatproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.xctf.pm.wechatproject.dao.SqlManager;
import cn.edu.xctf.pm.wechatproject.service.CheckService;
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

/**
 * 注册界面
 * @Author pangmin
 * @Date 2020/11/22 01:26
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText username;
    private EditText password;
    private EditText checkPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        SQLiteStudioService.instance().start(this);

        username = findViewById(R.id.username);
        username.setOnClickListener(this);

        password = findViewById(R.id.password);
        password.setOnClickListener(this);

        checkPwd = findViewById(R.id.check_pwd);
        checkPwd.setOnClickListener(this);

        Button register_btn = findViewById(R.id.btn_register);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SqlManager sm = new SqlManager();
                CheckService cs = new CheckService();
                String name = username.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String check_pwd = checkPwd.getText().toString().trim();
                boolean judge_uNameIsEmpty = cs.checkUNameIsEmpty(name);
                boolean judge_pwdIsEmpty = cs.checkTwoPwdIsEmpty(pwd, check_pwd);
                boolean judge_uName = sm.findResultByUName(name);
                boolean judge_pwd = cs.checkPwd(pwd, check_pwd);
                if (!judge_uNameIsEmpty) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (!judge_uName) {
                    Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                }
                else if (!judge_pwdIsEmpty) {
                    Toast.makeText(RegisterActivity.this, "密码或验证密码不能为空", Toast.LENGTH_SHORT).show();
                }
                else if (!judge_pwd) {
                    Toast.makeText(RegisterActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                }
                else {
                    sm.insertUser(name, pwd);
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, IndexActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}