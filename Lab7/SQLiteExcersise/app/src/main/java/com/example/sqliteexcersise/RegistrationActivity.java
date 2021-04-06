package com.example.sqliteexcersise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.sqliteexcersise.Utils.createToast;

public class RegistrationActivity extends AppCompatActivity {

    EditText etName, etUsername, etPass, etConfirm, etEmail, etPhone;
    Button btn;

    DbHelper dbHelper;
    SQLiteDatabase dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DbHelper(this);
        bindViews();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {

        if(etName.getText().toString().equals("") || etUsername.getText().toString().equals("") || etPass.getText().toString().equals("") ||
                etConfirm.getText().toString().equals("") || etEmail.getText().toString().equals("") || etPhone.getText().toString().equals("")) {
            createToast(this, "Fields can't be empty");
            return;
        } else if(!etPass.getText().toString().equals(etConfirm.getText().toString())) {
            createToast(this, "Passwords don't match");
            return;
        }

        dbWrite = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(LabReaderContract.LabEntry.COLUMN_NAME_USERNAME, etUsername.getText().toString());
        values.put(LabReaderContract.LabEntry.COLUMN_NAME_NAME, etName.getText().toString());
        values.put(LabReaderContract.LabEntry.COLUMN_NAME_PASS, etPass.getText().toString());
        values.put(LabReaderContract.LabEntry.COLUMN_NAME_EMAIL, etEmail.getText().toString());
        values.put(LabReaderContract.LabEntry.COLUMN_NAME_PHONE, etPhone.getText().toString());

        long newRowId = dbWrite.insert(LabReaderContract.LabEntry.TABLE_NAME, null, values);

        if(newRowId != -1) {
            createToast(this, "Inserted Value. User is registered!!!");
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            createToast(this, "Duplicate Username Found. Aborting!!!");
        }
    }

    private void bindViews() {
        etName = findViewById(R.id.etRegName);
        etUsername = findViewById(R.id.etRegUsername);
        etPass = findViewById(R.id.etRegPass);
        etConfirm = findViewById(R.id.etRegConfirm);
        etEmail = findViewById(R.id.etRegEmail);
        etPhone = findViewById(R.id.etRegPhone);
        btn = findViewById(R.id.btnRegistered);
    }

}
