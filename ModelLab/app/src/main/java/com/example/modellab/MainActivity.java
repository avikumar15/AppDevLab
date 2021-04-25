package com.example.modellab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.modellab.db.DbHelper;

import java.util.ArrayList;
import java.util.List;

import static com.example.modellab.Utils.EMAIL;
import static com.example.modellab.Utils.MOBILE;
import static com.example.modellab.Utils.NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AppInterface {

    private static final String TAG = "MAINACTIVITY";
    int data=2;
    Button btn;

    AppInterface appInterface;
    SharedPreferences sharedPref;

    RecyclerView rv;
    MyRecyclerAdapter myRecyclerAdapter;
    List<DataModel> dataList = new ArrayList<>();

    DbHelper dbHelper;
    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState!=null) {
            data = savedInstanceState.getInt("current");
        }

        data = 3;

        bindViews();

//        btn.setOnClickListener(this);

        sharedPref = getSharedPreferences(Utils.SP_KEY, Context.MODE_PRIVATE);

        populateList();

    }

    private void populateList() {

        dbHelper = new DbHelper(this);
        dbRead = dbHelper.getReadableDatabase();
        dbWrite = dbHelper.getWritableDatabase();

        // populate
        myRecyclerAdapter.notifyDataSetChanged();
    }

    public void startService(String input) {

        Intent serviceIntent = new Intent(this, ServiceClass.class);
        serviceIntent.putExtra("INTENT_TEXT", input);

        ContextCompat.startForegroundService(this, serviceIntent);
    }

    Menu menu;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                help();
                return true;
            case R.id.settings:
                settings();
                return true;
            case R.id.exit:
                Log.i(TAG, "EXIT");
                finish();
                return true;
            case R.id.control:
                control();
                return true;
            case R.id.prefs:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void control() {

    }

    private void settings() {

    }

    private void help() {

    }

    private void bindViews() {
        appInterface = (AppInterface) this;

        setRecycler();
    }

    private void setRecycler() {
        rv = findViewById(R.id.rv);
        myRecyclerAdapter = new MyRecyclerAdapter(dataList);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(myRecyclerAdapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("current", data);
    }

    private void removeOnclickListeners() {
        btn.setOnClickListener(null);
    }


    @Override
    public void onClick(View view) {

    }

    @Override
    public void onComplete() {

    }
}