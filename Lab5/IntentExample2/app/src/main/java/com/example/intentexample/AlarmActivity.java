package com.example.intentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.intentexample.Utils.createToast;

public class AlarmActivity extends AppCompatActivity implements View.OnClickListener {

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

    @SuppressLint("SetTextI18n")
    private void setActivity() {
        tv1.setText("Alarm Message");
        tv2.setText("Time(HH:MM in 24h)");
        tv3.setVisibility(View.GONE);
        et3.setVisibility(View.GONE);
        btnNext.setText("Open Alarm Clock");
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

    @Override
    public void onClick(View view) {

        String msg = et1.getText().toString();
        String time = et2.getText().toString();

        String[] parts = time.split(":");
        int hour = 0;
        int min = 0;

        if(msg.equals("") || time.equals("")) {
            createToast(this, "Empty edit texts not allowed!");
        } else if(parts.length!=2) {
            createToast(this, "Time format not proper!");
        } else {

            try {
                hour = Integer.parseInt(parts[0]);
                min = Integer.parseInt(parts[1]);
            } catch (NumberFormatException e) {
                createToast(this, "Improper number format!");
                return;
            }
            
            if(hour>=24 || min>=60 || min<0 || hour<0) {
                createToast(this, "Time format not proper!");
            } else {
                Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);

                alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, msg);
                alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, min);

                startActivity(alarmIntent);
            }
        }
    }
}