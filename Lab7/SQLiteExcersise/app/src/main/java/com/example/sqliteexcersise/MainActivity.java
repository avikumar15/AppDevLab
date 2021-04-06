package com.example.sqliteexcersise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.sqliteexcersise.Utils.USER_ID;
import static com.example.sqliteexcersise.Utils.createToast;

public class MainActivity extends AppCompatActivity {

    String currentUserName="", userPass="";

    TextView tvWelcomeMain;
    RecyclerView rv;
    MyRecyclerAdapter myRecyclerAdapter;

    List<User> userList = new ArrayList<>();

    DbHelper dbHelper;
    SQLiteDatabase dbRead;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        populateList();


        myRecyclerAdapter = new MyRecyclerAdapter(userList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myRecyclerAdapter);

        if(getIntent()!=null) {
            currentUserName = getIntent().getStringExtra(Utils.USER_NAME);
            userPass = getIntent().getStringExtra(Utils.USER_PASS);
        }

        tvWelcomeMain.setText("Welcome, "+currentUserName+".");
    }

    private void populateList() {
        dbHelper = new DbHelper(this);
        dbRead = dbHelper.getReadableDatabase();

        Cursor cursor = dbRead.rawQuery("SELECT * FROM "+ LabReaderContract.LabEntry.TABLE_NAME, null);

        while(cursor.moveToNext()) {

            String uname, name, email, phone;

            uname = cursor.getString(
                    cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_USERNAME));
            name = cursor.getString(
                    cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_NAME));
            email = cursor.getString(
                    cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_EMAIL));
            phone = cursor.getString(
                    cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_PHONE));

            userList.add(new User(name, uname, email, phone));
        }
        cursor.close();
    }

    private void bindViews() {
        tvWelcomeMain = findViewById(R.id.tvWelcomeMain);
        rv = findViewById(R.id.rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_pass:

                intent = new Intent(this, ChangePasswordActivity.class);
                intent.putExtra(Utils.USER_NAME, currentUserName);
                intent.putExtra(Utils.USER_PASS, userPass);
                startActivity(intent);

                return true;
            case R.id.logout:

                logout();

                return true;
            case R.id.exit:

                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout() {
        intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

}