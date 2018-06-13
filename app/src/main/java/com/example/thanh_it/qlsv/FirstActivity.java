package com.example.thanh_it.qlsv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.thanh_it.qlsv.QLLOP.AddClassActivity;
import com.example.thanh_it.qlsv.QLLOP.ViewClassActivity;
import com.example.thanh_it.qlsv.QLSV.ViewStudentActivity;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button add = findViewById(R.id.addclass);
        Button showclass = findViewById(R.id.viewclass);
        Button showsv = findViewById(R.id.viewstudent);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, AddClassActivity.class);
                startActivity(intent);
            }
        });
        showclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ViewClassActivity.class);
                startActivity(intent);
            }
        });
        showsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, ViewStudentActivity.class);
                startActivity(intent);
            }
        });
    }

}
