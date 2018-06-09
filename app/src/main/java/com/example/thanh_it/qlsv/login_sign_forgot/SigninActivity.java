package com.example.thanh_it.qlsv.login_sign_forgot;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thanh_it.qlsv.DBQLDATA.DBQL;
import com.example.thanh_it.qlsv.R;

public class SigninActivity extends AppCompatActivity {
    EditText user, pass, repass, phone;
    private Button signin;
    private DBQL mDBQL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.repass);
        phone = findViewById(R.id.phone);
        signin = findViewById(R.id.signinnow);
        mDBQL = new DBQL(this);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase sqLiteDB = mDBQL.getWritableDatabase();
                ContentValues values = new ContentValues();
                String username = user.getText().toString(); //lấy khung tên đăng nhập
                String password = pass.getText().toString(); //lấy khung mk
                String repassword = repass.getText().toString(); //lấy khung nhập lại mk
                String numberphone = phone.getText().toString(); // lấy khung sđt
                if(username.equalsIgnoreCase("")||password.equalsIgnoreCase("")||repassword.equalsIgnoreCase("")){
                    Toast.makeText(SigninActivity.this, "Phải điền đầy đủ thông tin", android.widget.Toast.LENGTH_SHORT).show();
                    //check rỗng
                }else if(!password.equals(repassword)){
                    Toast.makeText(SigninActivity.this, "Mật khẩu phải khớp nhau", android.widget.Toast.LENGTH_SHORT).show();
                    //Check 2 pass có khớp không
                }else{
                    values.put("USER",username);
                    values.put("PASS",password);
                    values.put("PHONE",numberphone);
                    sqLiteDB.insert("ACCOUNT",null,values);
                    sqLiteDB.close();
                    Toast.makeText(SigninActivity.this, "Đăng kí thành công!", android.widget.Toast.LENGTH_SHORT).show();
                    // gán user, pass, sđt vào bảng ACCOUNT và đóng db lại
                }
            }
        });
    }
}