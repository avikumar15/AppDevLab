package com.example.colourgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1;
    Button btn2;
    TextView tvCurrentHighest;

    static String MODE="MODE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        tvCurrentHighest = findViewById(R.id.tvCurrentHighest);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        SharedPreferences sharedPref = getSharedPreferences(MainActivity.SP_NAME, Context.MODE_PRIVATE);
        int highScore = sharedPref.getInt(MainActivity.HIGH_SCORE, 0);

        tvCurrentHighest.setText("Current Highest: "+highScore);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        switch (view.getId()) {
            case R.id.btn1:
                intent.putExtra(MODE, 1);
                break;
            case R.id.btn2:
                intent.putExtra(MODE, 2);
                break;
        }
        startActivity(intent);
        finish();
    }
}