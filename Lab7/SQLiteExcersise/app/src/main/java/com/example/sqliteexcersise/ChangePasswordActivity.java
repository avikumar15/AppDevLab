package com.example.sqliteexcersise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.sqliteexcersise.Utils.createToast;

public class ChangePasswordActivity extends AppCompatActivity {

    String username, passOld;
    Integer id;

    EditText etNew, etOld;
    TextView tv;

    Button btn;

    DbHelper dbHelper;
    SQLiteDatabase dbWrite;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        username = getIntent().getStringExtra(Utils.USER_NAME);
        passOld = getIntent().getStringExtra(Utils.USER_PASS);
        id = getIntent().getIntExtra(Utils.USER_ID, 0);

        bindViews();

        tv.setText("Hello! Change your password, "+username);

        dbHelper = new DbHelper(this);
        dbWrite = dbHelper.getWritableDatabase();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePass();
            }
        });

    }

    private void changePass() {
        if(etNew.getText().toString().equals("") || etOld.getText().toString().equals("")) {
            createToast(this, "Enter value");
            return;
        }
        if(!passOld.equals(etOld.getText().toString())) {
            createToast(this, "Cant change password. Incorrect old pass");
            return;
        }

        dbHelper.update(dbWrite, username, etNew.getText().toString());
        createToast(this, "Password Changed, Going back.");
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void bindViews() {
        etNew = findViewById(R.id.etNewPass);
        etOld = findViewById(R.id.etOldPass);
        btn = findViewById(R.id.btnChangePass);
        tv = findViewById(R.id.tvelcomeChange);
    }


}