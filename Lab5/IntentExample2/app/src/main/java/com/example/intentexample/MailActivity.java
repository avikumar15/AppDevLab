package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.intentexample.Utils.createToast;

public class MailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et1, et2, et3;
    Button btnNext;
    TextView tv1, tv2, tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        bindViews();
        setActivity();
        btnNext.setOnClickListener(this);
    }

    private void bindViews() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        tv1 = findViewById(R.id.tvOverEt1);
        tv2 = findViewById(R.id.tvOverEt2);
        tv3 = findViewById(R.id.tvOverEt3);
        btnNext = findViewById(R.id.btn);
    }

    @SuppressLint("SetTextI18n")
    private void setActivity() {
        tv1.setText("Sender's Address");
        tv2.setText("Subject");
        tv3.setText("Body");
        btnNext.setText("Open Mail");
    }

    @Override
    public void onClick(View view) {
        String email = et1.getText().toString();
        String sub = et2.getText().toString();
        String body = et3. getText().toString();

        if(email.equals("") || sub.equals("") || body.equals("")) {
            createToast(this, "Empty edit texts not allowed!");
            return;
        }

        Intent mailIntent = new Intent(Intent.ACTION_SENDTO);

        mailIntent.setData(Uri.parse("mailto:"+email));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, sub);
        mailIntent.putExtra(Intent.EXTRA_TEXT, body);

        startActivity(mailIntent);
    }
}
