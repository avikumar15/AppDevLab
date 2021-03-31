package com.example.sqliteexcersise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase dbWrite;
    SQLiteDatabase dbRead;

    EditText etTitle, etSubtitle;
    Button btn;

    TextView tv;

    final String[] projection = {
            BaseColumns._ID,
            LabReaderContract.LabEntry.COLUMN_NAME_TITLE,
            LabReaderContract.LabEntry.COLUMN_NAME_SUBTITLE
    };

    final String selection = LabReaderContract.LabEntry.COLUMN_NAME_TITLE + " = ?";
    final String[] selectionArgs = { "My Title" };

    final String sortOrder =
            LabReaderContract.LabEntry.COLUMN_NAME_SUBTITLE + " DESC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);
        dbWrite = dbHelper.getWritableDatabase();
        dbRead = dbHelper.getReadableDatabase();

        etTitle = findViewById(R.id.et1);
        etSubtitle = findViewById(R.id.et2);
        findViewById(R.id.et3).setVisibility(View.GONE);

        btn = findViewById(R.id.btn);

        tv = findViewById(R.id.tvelcome);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ContentValues values = new ContentValues();
                values.put(LabReaderContract.LabEntry.COLUMN_NAME_TITLE, etTitle.getText().toString());
                values.put(LabReaderContract.LabEntry.COLUMN_NAME_SUBTITLE, etSubtitle.getText().toString());

                long newRowId = dbWrite.insert(LabReaderContract.LabEntry.TABLE_NAME, null, values);

                if(newRowId != -1) {
                    Toast.makeText(getApplicationContext(), "Inserted Value", Toast.LENGTH_SHORT).show();
                }

                Cursor cursor = dbRead.rawQuery("SELECT * FROM "+ LabReaderContract.LabEntry.TABLE_NAME, null);

                ArrayList<Pair<String, String>> items = new ArrayList<>();
                while(cursor.moveToNext()) {
                    String title;
                    String subtitle;

                    title = cursor.getString(
                            cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_TITLE));

                    subtitle = cursor.getString(
                            cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_SUBTITLE));

                    items.add(new Pair<>(title, subtitle));
                }
                cursor.close();
                StringBuilder txt= new StringBuilder();
                for(int i=0; i<items.size(); i++) {
                    txt.append(items.get(i).first).append(" and ").append(items.get(i).second).append("\n");
                }
                tv.setText(txt);
            }
        });

    }


}
