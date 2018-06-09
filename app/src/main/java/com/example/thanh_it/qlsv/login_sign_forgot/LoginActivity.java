package com.example.thanh_it.qlsv.login_sign_forgot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thanh_it.qlsv.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.login);
        final Button signin = (Button) findViewById(R.id.signin);
        final Button forgot = (Button) findViewById(R.id.forgot);
        //Đăng nhập vào hệ thống
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginok();
            }
        });
        //Mở layout đăng ký tài khoản
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign();
            }
        });
        //Mở layout quên mật khẩu
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotpass();
            }
        });
    }

    private void loginok() {
        //TODO code
    }

    private void forgotpass() {
        Intent intentforgot = new Intent(LoginActivity.this, ForgotActivity.class);
        //TODO code
        startActivity(intentforgot);
    }

    private void sign() {
        Intent intentsign = new Intent(LoginActivity.this, SigninActivity.class);
        //TODO code
        startActivity(intentsign);
    }
}
