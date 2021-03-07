package com.example.intentuse;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.intentuse.Utils.NAME_KEY;
import static com.example.intentuse.Utils.ROLL_KEY;
import static com.example.intentuse.Utils.createToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String name, roll;
    TextView tvName, tvRoll;
    RadioButton rbCall, rbWebPage, rbYouTube;
    Button btnBack, btnGo;
    Integer chosenOption = -1;
    ArrayList<Intent> intents = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViewsAndSetOnClick();

        if(getIntent()!=null) {
            name = getIntent().getStringExtra(NAME_KEY);
            roll = getIntent().getStringExtra(ROLL_KEY);

            tvName.setText("Welcome "+name+"!");
            tvRoll.setText("Your Roll Number is: "+roll);

            createToast(this, "Welcome "+name+"!\nRoll: "+roll);

            intents.add(new Intent(this, StartActivity.class));
            intents.add(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:04312503000")));
            intents.add(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.nitt.edu/")));
            intents.add(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCEPOEe5azp3FbUjvMwttPqw")));

        } else {
            createToast(this, "Something went wrong");
        }
    }

    private void bindViewsAndSetOnClick() {
        tvName = findViewById(R.id.tvName);
        tvRoll = findViewById(R.id.tvRoll);

        rbCall = findViewById(R.id.rbCall);
        rbWebPage = findViewById(R.id.rbWebPage);
        rbYouTube = findViewById(R.id.rbYoutube);

        btnBack = findViewById(R.id.btnBack);
        btnGo = findViewById(R.id.btnGo);

        rbCall.setOnClickListener(this);
        rbWebPage.setOnClickListener(this);
        rbYouTube.setOnClickListener(this);

        btnBack.setOnClickListener(this);
        btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbCall:
                chosenOption = 1;
                break;
            case R.id.rbWebPage:
                chosenOption = 2;
                break;
            case R.id.rbYoutube:
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
        if(chosenOption==-1) {
            createToast(this, "Chose an option and then click submit");
            return;
        }
        startActivity(intents.get(chosenOption));
    }

    private void goBack() {
        startActivity(intents.get(0));
    }
}
