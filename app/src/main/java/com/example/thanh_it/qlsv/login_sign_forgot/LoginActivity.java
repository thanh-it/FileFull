package com.example.thanh_it.qlsv.login_sign_forgot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanh_it.qlsv.FirstActivity;
import com.example.thanh_it.qlsv.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login = (Button) findViewById(R.id.login);
        final Button signin = (Button) findViewById(R.id.signin);
        final Button forgot = (Button) findViewById(R.id.forgot);
        EditText username = findViewById(R.id.user);
        EditText password = findViewById(R.id.pass);
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
        EditText username = findViewById(R.id.user);
        EditText password = findViewById(R.id.pass);
        if(username.getText().toString().equalsIgnoreCase("admin_he_thong")&&password.getText().toString().equalsIgnoreCase("LaptrinhAndroid")){
            Intent intent = new Intent(LoginActivity.this, FirstActivity.class);
            Toast.makeText(LoginActivity.this, "Welcome to DeathClick", android.widget.Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }else{
            Toast.makeText(LoginActivity.this, "Liên hệ nhóm DeathClick để có quyền vào hệ thống", android.widget.Toast.LENGTH_SHORT).show();
        }
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
