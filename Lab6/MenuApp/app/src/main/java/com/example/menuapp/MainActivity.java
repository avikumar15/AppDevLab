package com.example.menuapp;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.menuapp.Utils.createToast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MenuMain";
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void control() {
        Log.i(TAG, "CONTROL");

        createToast(this, "Control Item Selected");

        Intent intent = new Intent(this, ControlActivity.class);
        startActivity(intent);

    }

    private void settings() {
        Log.i(TAG, "SETTINGS");
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    private void help() {
        Log.i(TAG, "HELP");
        String url = "https://developer.android.com/guide";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


}
