package com.example.modellab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.modellab.db.DbHelper;
import com.example.modellab.db.ReaderContract;

import static com.example.modellab.Utils.PASS;
import static com.example.modellab.Utils.UNAME;
import static com.example.modellab.Utils.createToast;

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
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void login() {

        if(etPassword.getText().toString().equals("") || 
                etUserName.getText().toString().equals("")) {
            createToast(this, "Fields cannot be empty");
            return;
        }

        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        boolean flag = false;

        Cursor cursor = dbRead.rawQuery("SELECT * FROM "+ ReaderContract.TableEntry.TABLE_NAME, null);

        while(cursor.moveToNext()) {

            String uname;
            String pass;


            // TODO
            uname = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_MRP));

            pass = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_MRP));

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

            intent.putExtra(UNAME, userName);
            intent.putExtra(PASS, password);

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