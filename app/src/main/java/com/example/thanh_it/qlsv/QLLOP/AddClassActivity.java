package com.example.thanh_it.qlsv.QLLOP;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.thanh_it.qlsv.DBQLDATA.DBQL;
import com.example.thanh_it.qlsv.R;

public class AddClassActivity extends AppCompatActivity {
    private DBQL mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        final EditText addma = findViewById(R.id.malop);
        final EditText addten = findViewById(R.id.malop);
        Button clearform = findViewById(R.id.clear);
        final Button addclass = findViewById(R.id.addclass);
        mDatabase = new DBQL(this);
        clearform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addma.setText("");
                addten.setText("");
            }
        });
        addclass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase Database = mDatabase.getWritableDatabase();
                ContentValues values = new ContentValues();
                String setid = addma.getText().toString();
                String setname = addten.getText().toString();
                values.put("MALOP",setid);
                values.put("TENLOP",setname);
                Database.insert("LOP",null,values);
                Database.close();
        }
        });
    }
}
