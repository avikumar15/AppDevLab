package com.example.tictactoesimple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    EditText et1, et2;
    Button go;

    static String Name1 = "Name1";
    static String Name2 = "Name2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);

        go = findViewById(R.id.btn);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNextActivity();
            }
        });

    }

    private void startNextActivity() {

        if((et1.getText().toString().equals("") || et2.getText().toString().equals(""))) {
            Toast.makeText(this, "Enter two names and hit go", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Name1, et1.getText().toString());
            intent.putExtra(Name2, et2.getText().toString());
            startActivity(intent);
            finish();
        }
    }


}