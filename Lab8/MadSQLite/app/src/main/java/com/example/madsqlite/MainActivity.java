package com.example.madsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.madsqlite.db.DbHelper;
import com.example.madsqlite.db.ReaderContract;

import static com.example.madsqlite.Utils.createToast;

public class MainActivity extends AppCompatActivity {

    Intent intent;

    EditText etProductID;
    EditText etProductName;
    EditText etMRP;
    EditText etPrice;

    Button btn;

    DbHelper dbHelper;
    SQLiteDatabase dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DbHelper(this);
        bindViews();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEntry();
            }
        });
    }

    private void addEntry() {
        if(etProductID.getText().toString().equals("") || etProductName.getText().toString().equals("") ||
            etPrice.getText().toString().equals("") || etMRP.getText().toString().equals("")) {
            createToast(this, "Please make sure fields are not empty!");
            return;
        } else if(etProductID.length()<3) {
            createToast(this, "Please ensure the length of ID>=3!");
            return;
        }

        dbWrite = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ReaderContract.TableEntry.COLUMN_NAME_PID, etProductID.getText().toString());
        values.put(ReaderContract.TableEntry.COLUMN_NAME_PNAME, etProductName.getText().toString());
        values.put(ReaderContract.TableEntry.COLUMN_NAME_PRICE, etPrice.getText().toString());
        values.put(ReaderContract.TableEntry.COLUMN_NAME_MRP, etMRP.getText().toString());

        long newRowId = dbWrite.insert(ReaderContract.TableEntry.TABLE_NAME, null, values);

        if(newRowId != -1) {
            createToast(this, "Inserted Value. Product is Added!");
        } else {
            createToast(this, "Duplicate Product ID. Aborting!");
        }

    }

    private void bindViews() {
        etProductID = findViewById(R.id.etPID);
        etProductName = findViewById(R.id.etProductName);
        etMRP = findViewById(R.id.etMRP);
        etPrice = findViewById(R.id.etPrice);
        btn = findViewById(R.id.btnAdd);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.display:
                intent = new Intent(this, DisplayActivity.class);
                startActivity(intent);
                return true;
            case R.id.search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                return true;
            case R.id.edit:
                intent = new Intent(this, EditActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
