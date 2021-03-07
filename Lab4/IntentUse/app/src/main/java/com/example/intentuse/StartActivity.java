package com.example.intentuse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.intentuse.MainActivity;
import com.example.intentuse.R;

import static com.example.intentuse.Utils.NAME_KEY;
import static com.example.intentuse.Utils.ROLL_KEY;
import static com.example.intentuse.Utils.createToast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etRoll;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        etName = findViewById(R.id.et1);
        etRoll = findViewById(R.id.et2);

        btnNext = findViewById(R.id.btn);

        btnNext.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String name = etName.getText().toString();
        String roll = etRoll.getText().toString();

        if(name.length()==0) {
            createToast(this,"Name cannot be empty");
            return;
        }

        if(roll.length() != 9) {
            createToast(this, "Roll number should contain 9 digits");
            return;
        }

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(NAME_KEY, name);
        intent.putExtra(ROLL_KEY, roll);
        startActivity(intent);

    }

}
