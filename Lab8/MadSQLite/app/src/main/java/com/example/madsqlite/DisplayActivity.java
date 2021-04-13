package com.example.madsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.madsqlite.db.DbHelper;
import com.example.madsqlite.db.ReaderContract;

import java.util.ArrayList;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    Intent intent;

    RecyclerView rv;
    MyRecyclerAdapter myRecyclerAdapter;
    List<ProductModel> productList = new ArrayList<>();

    DbHelper dbHelper;
    SQLiteDatabase dbRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        farziKaam();
        populateList();
    }

    private void populateList() {
        dbHelper = new DbHelper(this);
        dbRead = dbHelper.getReadableDatabase();

        Cursor cursor = dbRead.rawQuery("SELECT * FROM "+ ReaderContract.TableEntry.TABLE_NAME, null);

        while(cursor.moveToNext()) {

            String pname, pid, pprice, pmrp;

            pname = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PNAME));
            pid = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PID));
            pprice = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PRICE));
            pmrp = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_MRP));

            productList.add(new ProductModel(pname, pid, pprice, pmrp));
        }
        cursor.close();
        myRecyclerAdapter.notifyDataSetChanged();
    }

    private void farziKaam() {
        rv = findViewById(R.id.rv);
        myRecyclerAdapter = new MyRecyclerAdapter(productList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myRecyclerAdapter);
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