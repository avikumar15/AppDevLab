package com.example.sqliteexcersise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.sqliteexcersise.Utils.USER_ID;
import static com.example.sqliteexcersise.Utils.USER_NAME;
import static com.example.sqliteexcersise.Utils.USER_PASS;
import static com.example.sqliteexcersise.Utils.createToast;

public class LoginActivity extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase dbRead;

    EditText etUserName, etPassword;
    Button btnLogin, btnRegister;

    Intent intent;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initDb();
        bindViews();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void register() {
        intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

    private void login() {

        if(etPassword.getText().toString().equals("") || etUserName.getText().toString().equals("")) {
            createToast(this, "Fields cannot be empty");
            return;
        }

        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        boolean flag = false;

        Cursor cursor = dbRead.rawQuery("SELECT * FROM "+ LabReaderContract.LabEntry.TABLE_NAME, null);

        while(cursor.moveToNext()) {

            String uname;
            String pass;

            uname = cursor.getString(
                    cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_USERNAME));

            pass = cursor.getString(
                    cursor.getColumnIndexOrThrow(LabReaderContract.LabEntry.COLUMN_NAME_PASS));

            if(uname.equals(userName)) {
                if(pass.equals(password)) {
                    createToast(this, "Credentials match. Logging you in!");
                    flag = true;
                    break;
                } else {
                    createToast(this, "Wrong password");
                    break;
                }
            }
        }
        cursor.close();

        if(flag) {
            intent  = new Intent(this, MainActivity.class);

            intent.putExtra(USER_NAME, userName);
            intent.putExtra(USER_PASS, password);

            startActivity(intent);
            finish();
        } else {
            createToast(this, "User not found.");
        }

    }

    private void initDb() {
        dbHelper = new DbHelper(this);
        dbRead = dbHelper.getReadableDatabase();
    }

    private void bindViews() {
        etUserName = findViewById(R.id.et1);
        etPassword = findViewById(R.id.et2);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnReg);
        tv = findViewById(R.id.tvelcome);
    }


}
