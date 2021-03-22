package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import java.util.ArrayList;

import static com.example.intentexample.Utils.OPTION_KEY;
import static com.example.intentexample.Utils.createToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvRoll;
    RadioButton rbAlarm, rbMusic, rbMail;
    Button btnBack, btnGo;

    Integer chosenOption = -1;

    ArrayList<Intent> intents = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViewsAndSetOnClick();

        tvRoll.setText("Welcome User!");
        createToast(this, "Welcome User!");

        populateIntents();

    }

    private void populateIntents() {
        intents.add(new Intent());
        intents.add(new Intent(this, AlarmActivity.class));
        intents.add(new Intent(this,MusicActivity.class));
        intents.add(new Intent(this, MailActivity.class));
    }

    private void bindViewsAndSetOnClick() {
        tvRoll = findViewById(R.id.tvRoll);

        rbAlarm = findViewById(R.id.rbAlarm);
        rbMusic = findViewById(R.id.rbMusic);
        rbMail = findViewById(R.id.rbMail);

        btnBack = findViewById(R.id.btnBack);
        btnGo = findViewById(R.id.btnGo);

        rbAlarm.setOnClickListener(this);
        rbMusic.setOnClickListener(this);
        rbMail.setOnClickListener(this);

        btnBack.setOnClickListener(this);
        btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbAlarm:
                chosenOption = 1;
                break;
            case R.id.rbMusic:
                chosenOption = 2;
                break;
            case R.id.rbMail:
                chosenOption = 3;
                break;
            case R.id.btnBack:
                goBack();
                break;
            case R.id.btnGo:
                letsGo();
                break;
        }
    }

    private void letsGo() {
        if (chosenOption == -1) {
            createToast(this, "Chose an option and then click submit");
        } else {
            startActivity(intents.get(chosenOption));
            finish();
        }
    }

    private void goBack() {
        finish();
    }
}
