package com.example.thanh_it.qlsv.QLSV;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thanh_it.qlsv.DBQLDATA.DBQL;
import com.example.thanh_it.qlsv.R;

public class AddStudentActivity extends AppCompatActivity {
    private DBQL mDatabases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        final EditText mssv = findViewById(R.id.mssv);
        final EditText name = findViewById(R.id.namesv);
        final EditText date = findViewById(R.id.date);
        final EditText malop = findViewById(R.id.malopsv);
        final EditText ngayhoc = findViewById(R.id.ngayhoc);
        final Button xoa = findViewById(R.id.clearsv);
        final Button addsv = findViewById(R.id.addsv);
        mDatabases = new DBQL(this);
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mssv.setText("");
                name.setText("");
                date.setText("");
                malop.setText("");
                ngayhoc.setText("");
            }
        });
        addsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase Database = mDatabases.getWritableDatabase();
                ContentValues values = new ContentValues();
                String idsv = mssv.getText().toString();
                String namesv = name.getText().toString();
                String ngaysinhsv = date.getText().toString();
                String malopsv = malop.getText().toString();
                String ngayhocsv = ngayhoc.getText().toString();
                values.put("MSSV",idsv);
                values.put("NAME",namesv);
                values.put("SN",ngaysinhsv);
                values.put("MALOP",malopsv);
                values.put("NGAYHOC",ngayhocsv);
                Database.insert("STUDENT",null,values);
                Database.close();
                Intent intent = new Intent(AddStudentActivity.this,ViewStudentActivity.class);
                startActivity(intent);
            }
        });
    }
}
