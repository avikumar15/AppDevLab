package com.example.menuapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.menuapp.Utils.EMAIL;
import static com.example.menuapp.Utils.MOBILE;
import static com.example.menuapp.Utils.NAME;
import static com.example.menuapp.Utils.createToast;

public class PrefActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSave, btnShow;
    EditText etName, etEmail, etMobile;
    TextView resultText;

    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);
        bind();
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
    }

    private void bind() {
        btnSave = findViewById(R.id.btnSave);
        btnShow = findViewById(R.id.btnShow);
        etName = findViewById(R.id.et1);
        etEmail = findViewById(R.id.et2);
        etMobile = findViewById(R.id.et3);
        resultText = findViewById(R.id.resultText);

        btnShow.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSave:
                save();
                break;
            case R.id.btnShow:
                show();
                break;
        }
    }

    private void show() {

        String name = sharedPref.getString(NAME, "name");
        String email = sharedPref.getString(EMAIL, "email");
        String mob = sharedPref.getString(MOBILE, "mobile");

        resultText.setVisibility(View.VISIBLE);
        resultText.setText("Name: "+name+"\nEmail: "+email+"\nMobile: "+mob);

    }

    private void save() {
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String mob = etMobile.getText().toString();

        resultText.setVisibility(View.GONE);

        if(name.equals("") || email.equals("") || mob.equals("")) {
            createToast(this, "Empty Not Acceptable");
        } else {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(NAME, name);
            editor.putString(EMAIL, email);
            editor.putString(MOBILE, mob);
            editor.apply();
        }
    }
}
