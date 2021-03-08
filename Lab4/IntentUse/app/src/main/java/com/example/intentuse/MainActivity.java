package com.example.intentuse;

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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import static com.example.intentuse.Utils.NAME_KEY;
import static com.example.intentuse.Utils.ROLL_KEY;
import static com.example.intentuse.Utils.createToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String name, roll;
    TextView tvName, tvRoll;
    RadioButton rbCall, rbWebPage, rbYouTube;
    Button btnBack, btnGo;
    EditText etMessage;
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
            intents.add(new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:")));
            intents.add(new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:")));
            intents.add(new Intent(Intent.ACTION_WEB_SEARCH));

        } else {
            createToast(this, "Something went wrong");
        }
    }

    private void bindViewsAndSetOnClick() {
        tvName = findViewById(R.id.tvName);
        tvRoll = findViewById(R.id.tvRoll);

        rbCall = findViewById(R.id.rbMessage);
        rbWebPage = findViewById(R.id.rbMail);
        rbYouTube = findViewById(R.id.rbGoogle);

        btnBack = findViewById(R.id.btnBack);
        btnGo = findViewById(R.id.btnGo);
        etMessage = findViewById(R.id.etMessage);

        rbCall.setOnClickListener(this);
        rbWebPage.setOnClickListener(this);
        rbYouTube.setOnClickListener(this);

        btnBack.setOnClickListener(this);
        btnGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbMessage:
                chosenOption = 1;
                break;
            case R.id.rbMail:
                chosenOption = 2;
                break;
            case R.id.rbGoogle:
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

        String message = etMessage.getText().toString();

        if(message.length()==0) {
            createToast(this, "Message cannot be empty");
            return;
        }

        switch (chosenOption) {
            case -1:
                createToast(this, "Chose an option and then click submit");
                return;
            case 1:
                intents.get(1).putExtra("sms_body", message);
                break;
            case 2:
                intents.get(2).putExtra(Intent.EXTRA_TEXT, message);
                break;
            case 3:
                intents.get(3).putExtra(SearchManager.QUERY, message);
                break;
        }

        startActivity(intents.get(chosenOption));
    }

    private void goBack() {
        startActivity(intents.get(0));
    }
}
