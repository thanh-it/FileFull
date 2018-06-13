package com.example.thanh_it.qlsv.QLLOP;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.thanh_it.qlsv.R;

import java.util.ArrayList;
import java.util.List;

public class ViewClassActivity extends AppCompatActivity {
    private List<Lop> mClass = new ArrayList<>();
    private ListView listclass;
    private DBQL mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_class);
        listclass = findViewById(R.id.viewlistclass);
        getListClass();
        listclass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                DBQL sqLiteDatabase = new DBQL(ViewClassActivity.this);
                final SQLiteDatabase sqLite = sqLiteDatabase.getWritableDatabase();
                final long id1 = mClass.get(position).getId();
                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewClassActivity.this);
                dialog.setMessage("YOU WANT PICK EDIT OR UPDATE");
                dialog.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Dialog dialog1 = new Dialog(ViewClassActivity.this);
                        dialog1.setContentView(R.layout.editclass);
                        EditText newma = dialog1.findViewById(R.id.malopnew);
                        EditText newname = dialog1.findViewById(R.id.tenlopnew);

                        final String setid = newma.getText().toString();
                        final String setname = newname.getText().toString();
                        final Button upnew = dialog1.findViewById(R.id.updatecl);
                        upnew.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ContentValues values = new ContentValues();
                                values.put("MALOP",setid);
                                values.put("TENLOP",setname);
                                sqLite.update("LOP",values,"ID=?",new String[]{id1+""});
                                getListClass();
                            }
                        });
                        dialog1.show();
                        getListClass();
                    }
                });
                dialog.setNegativeButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int aa =  sqLite.delete("LOP"," ID = ?",new String[]{id1+""});
                        mClass.remove(aa);
                        getListClass();
                    }
                });
                AlertDialog alertDialog = dialog.create();
                alertDialog.show();

            }
        });
    }

    private void getListClass() {
        mClass.clear();
        DBQL Database = new DBQL(this);
        SQLiteDatabase liteDatabase = Database.getReadableDatabase();
        Cursor cursor = liteDatabase.rawQuery("Select * from LOP",null);
        if(cursor == null)
        {
            return;
        }
        if (cursor.moveToFirst()) {
            //Loop through the table rows
            do {
                Lop mlop = new Lop();
                mlop.setId(cursor.getInt(0));
                mlop.setMalop(cursor.getString(1));
                mlop.setTenlop(cursor.getString(2));
                mClass.add(mlop);
            } while (cursor.moveToNext());
        }
        cursor.close();
        liteDatabase.close();
        Database.close();
        String[] names = new String[mClass.size()];
        TestDapter adapter = new TestDapter(names,this);

        listclass.setAdapter(adapter);
    }
    private class TestDapter extends BaseAdapter {
        private Context mContext;
        String[] mClasss;

        public TestDapter(String[] names, Context Context) {
            mClasss = names;/// giu lieu truyen vao listview
            mContext = Context;
        }

        @Override
        public int getCount() {
            return mClasss.length; // tinh tong so cac item tren listview
        }

        @Override
        public Object getItem(int position) {
            return mClasss[position];// tra ve giu lieu theo vi tri cua listview
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater vusView = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vusView.inflate(R.layout.showlistclass, null);
            TextView stt = v.findViewById(R.id.stt);
            TextView ma = v.findViewById(R.id.idlop);
            TextView ten = v.findViewById(R.id.namelop);
            String mstt = String.valueOf(mClass.get(position).getId());
            stt.setText(mstt);
            ma.setText(mClass.get(position).getMalop());
            ten.setText(mClass.get(position).getTenlop());
            return v;
        }
    }

}
