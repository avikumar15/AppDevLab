package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MusicActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et1, et2, et3;
    Button btnNext;
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        bindViews();
        setActivity();

        btnNext.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    private void setActivity() {
        tv1.setVisibility(View.GONE);
        et1.setVisibility(View.GONE);
        tv2.setVisibility(View.GONE);
        et2.setVisibility(View.GONE);
        tv3.setVisibility(View.GONE);
        et3.setVisibility(View.GONE);
        btnNext.setText("Open Music App");
    }

    private void bindViews() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        tv1 = findViewById(R.id.tvOverEt1);
        tv2 = findViewById(R.id.tvOverEt2);
        tv3 = findViewById(R.id.tvOverEt3);
        btnNext = findViewById(R.id.btn);
    }

    @Override
    public void onClick(View view) {
        String uri = "spotify:track:3sqRODOZ1Z6hR4F2tnFAlg";
        Intent musicIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(musicIntent);
    }

}