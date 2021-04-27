package com.example.modellab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.modellab.Utils.AGE;
import static com.example.modellab.Utils.GENDER;
import static com.example.modellab.Utils.NAME;
import static com.example.modellab.Utils.createToast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    EditText etName, etAge, etGender;
    Button btnSubmit;
    CheckBox cb;

    Intent intent;

    boolean isChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bindViews();
        setListener();
    }

    private void setListener() {
        btnSubmit.setOnClickListener(this);
        cb.setOnClickListener(this);
    }

    private void register() {

        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String gender = etGender.getText().toString();

        if (name.equals("")
                || age.equals("")
                || gender.equals("")) {
            createToast(this, "Fields can't be empty!");
            return;
        } else if (!isChecked) {
            createToast(this, "Click I agree and continue!");
            return;
        }

        intent = new Intent(this, MainActivity.class);

        intent.putExtra(NAME, name);
        intent.putExtra(AGE, age);
        intent.putExtra(GENDER, gender);

        startActivity(intent);
    }

    private void bindViews() {
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etGender = findViewById(R.id.etGender);
        btnSubmit = findViewById(R.id.btnSubmit);
        cb = findViewById(R.id.cb_i_agree);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSubmit:
                register();
                break;
            case R.id.cb_i_agree:
                CheckBox checkBox = (CheckBox) view;
                isChecked = checkBox.isChecked();
                break;
        }
    }
}