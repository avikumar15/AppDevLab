package com.example.madsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.madsqlite.db.DbHelper;
import com.example.madsqlite.db.ReaderContract;

import static com.example.madsqlite.Utils.createToast;

public class EditActivity extends AppCompatActivity {

    Intent intent;

    ConstraintLayout itemLayout;

    EditText etPid;

    TextView tvPid;
    EditText tvPname;
    EditText tvPprice;
    EditText tvPmrp;

    Button btnShow;
    Button btnEdit;

    DbHelper dbHelper;
    SQLiteDatabase dbWritable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        farziKaam();
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit();
            }
        });
    }

    private void edit() {
        if(tvPid.getText().toString().equals("") || tvPmrp.getText().toString().equals("") ||
                tvPname.getText().toString().equals("") || tvPprice.getText().toString().equals("")) {
            createToast(this, "Please make sure fields are not empty!");
            return;
        } else if(tvPid.length()<3) {
            createToast(this, "Please ensure the length of ID>=3!");
            return;
        }

        dbHelper.update(dbWritable, tvPid.getText().toString(), tvPname.getText().toString(),
                tvPprice.getText().toString(), tvPmrp.getText().toString());
        createToast(this, "Value Updated!");
    }

    private void show() {

        String pid = etPid.getText().toString();
        if(pid.equals("")) {
            createToast(this, "Empty PID not allowed");
            return;
        }

        dbHelper = new DbHelper(this);
        dbWritable = dbHelper.getWritableDatabase();

        Cursor cursor = dbWritable.rawQuery("SELECT * FROM "+ ReaderContract.TableEntry.TABLE_NAME, null);

        boolean flag = false;
        String pname="", ppid="", pprice="", pmrp="";

        while(cursor.moveToNext()) {

            ppid = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PID));
            pname = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PNAME));
            pprice = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PRICE));
            pmrp = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_MRP));

            if(ppid.equals(pid)) {
                flag = true;
                break;
            }

        }
        cursor.close();

        if(!flag) {
            createToast(this, "Entry not found with that pid!");
        } else {
            createToast(this, "Found! Edit the content.");
            tvPmrp.setText(pmrp);
            tvPprice.setText(pprice);
            tvPid.setText(pid);
            tvPname.setText(pname);
            itemLayout.setVisibility(View.VISIBLE);
            btnEdit.setVisibility(View.VISIBLE);
        }
    }

    private void farziKaam() {
        itemLayout = findViewById(R.id.clShow);
        itemLayout.setVisibility(View.INVISIBLE);
        etPid = findViewById(R.id.etPidEdit);
        tvPid = findViewById(R.id.etIdShowView);
        tvPname = findViewById(R.id.etNameShowView);
        tvPprice = findViewById(R.id.etPriceShowView);
        tvPmrp = findViewById(R.id.etMRPShowView);
        btnShow = findViewById(R.id.btnEdit);
        btnEdit = findViewById(R.id.btnEditNow);
        btnEdit.setVisibility(View.INVISIBLE);
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