package com.example.thanh_it.qlsv.QLSV;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.thanh_it.qlsv.DBQLDATA.DBQL;
import com.example.thanh_it.qlsv.DBQLDATA.Lop;
import com.example.thanh_it.qlsv.DBQLDATA.Student;
import com.example.thanh_it.qlsv.QLLOP.ViewClassActivity;
import com.example.thanh_it.qlsv.R;

import java.util.ArrayList;
import java.util.List;

public class ViewStudentActivity extends AppCompatActivity {
    private List<Student> mStudent = new ArrayList<>();
    private ListView list_sv;
    private DBQL mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);
        Button opnewnewsv = findViewById(R.id.button);
        opnewnewsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewStudentActivity.this,AddStudentActivity.class);
                startActivity(intent);
            }
        });
        list_sv = findViewById(R.id.list_sv);
        getListSV();
        list_sv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DBQL sqLiteDatabase = new DBQL(ViewStudentActivity.this);
                final SQLiteDatabase sqLite = sqLiteDatabase.getWritableDatabase();
                final long id1 = mStudent.get(position).getId();
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewStudentActivity.this);
                dialog.setMessage("YOU WANT PICK EDIT OR UPDATE");
                dialog.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog dialog1 = new Dialog(ViewStudentActivity.this);
                        dialog1.setContentView(R.layout.editsv);
                        final EditText newma = dialog1.findViewById(R.id.sid);
                        final EditText newname = dialog1.findViewById(R.id.ntensv);
                        final EditText newday = dialog1.findViewById(R.id.day);
                        final EditText newlop = dialog1.findViewById(R.id.mlopsv);
                        final EditText newnhoc = dialog1.findViewById(R.id.nhoc);
                        final Button upnewsv = dialog1.findViewById(R.id.updatesv);
                        upnewsv.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ContentValues values = new ContentValues();
                                values.put("MSSV",newma.getText().toString());
                                values.put("NAME",newname.getText().toString());
                                values.put("SN",newday.getText().toString());
                                values.put("MALOP",newlop.getText().toString());
                                values.put("NGAYHOC",newnhoc.getText().toString());
                                sqLite.update("STUDENT",values,"ID=?",new String[]{id1+""});
                                getListSV();
                            }
                        });
                        dialog1.show();
                    }
                });
                dialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int aa =  sqLite.delete("STUDENT"," ID = ?",new String[]{id1+""});
                        mStudent.remove(aa);
                        getListSV();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

            }
        });
    }

    private void getListSV() {
        mStudent.clear();
        DBQL Database = new DBQL(this);
        SQLiteDatabase liteDatabase = Database.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("Select * from STUDENT",null);
        if(cursor == null)
        {
            return;
        }
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                Student mSV = new Student();
                mSV.setId(cursor.getInt(0));
                mSV.setMssv(cursor.getString(1));
                mSV.setTensv(cursor.getString(2));
                mSV.setNgaysinh(cursor.getString(3));
                mSV.setMalop(cursor.getString(4));
                mSV.setNgayhoc(cursor.getString(5));
                mStudent.add(mSV);
            } while (cursor.moveToNext());
        }
        cursor.close();
        liteDatabase.close();
        Database.close();
        String[] listsv = new String[mStudent.size()];
        ViewStudentActivity.TestDapter adapter = new ViewStudentActivity.TestDapter(listsv,this);

        list_sv.setAdapter(adapter);

    }
    private class TestDapter extends BaseAdapter {
        private Context mContext;
        String[] mStudents;

        public TestDapter(String[] listsv, Context Context) {
            mStudents = listsv;/// giu lieu truyen vao listview
            mContext = Context;
        }

        @Override
        public int getCount() {
            return mStudents.length; // tinh tong so cac item tren listview
        }

        @Override
        public Object getItem(int position) {
            return mStudents[position];// tra ve giu lieu theo vi tri cua listview
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater vusView = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vusView.inflate(R.layout.showsv, null);
            TextView stt = v.findViewById(R.id.sttsv);
            TextView ma = v.findViewById(R.id.ms);
            TextView ten = v.findViewById(R.id.tensv);
            TextView ns = v.findViewById(R.id.ns);
            TextView lop = v.findViewById(R.id.lop);
            TextView nh = v.findViewById(R.id.nh);
            String mstt = String.valueOf(mStudent.get(position).getId());
            stt.setText(mstt);
            ma.setText(mStudent.get(position).getMssv());
            ten.setText(mStudent.get(position).getTensv());
            ns.setText(mStudent.get(position).getNgaysinh());
            lop.setText(mStudent.get(position).getMalop());
            nh.setText(mStudent.get(position).getNgayhoc());
            return v;
        }
    }
}
