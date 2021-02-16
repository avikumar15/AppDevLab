package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    TextView textView;

    Button button;
    RadioGroup rg;

    int choice = -1;
    double result;

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.et1);
        editText2 = findViewById(R.id.et2);

        button = findViewById(R.id.btn);

        textView = findViewById(R.id.tvAns);
        rg = findViewById(R.id.rg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearNumbers();
            }
        });

    }

    private void clearNumbers() {
        editText1.setText("");
        editText2.setText("");
        textView.setText("");
        textView.setBackgroundColor(Color.parseColor("#BEE0EC"));
        rg.clearCheck();
    }

    public void onRadioButtonClicked(View view) {

        if(!(editText1.getText().toString().equals("")) && !(editText2.getText().toString().equals(""))) {
            boolean checked = ((RadioButton) view).isChecked();

            switch (view.getId()) {
                case R.id.add:
                    if (checked) {
                        Log.i(TAG, "Adding numbers");
                        Toast.makeText(this, "Adding the numbers", Toast.LENGTH_SHORT).show();
                        choice = 1;
                        break;
                    }
                case R.id.subtract:
                    if (checked) {
                        Log.i(TAG, "Subtracting numbers");
                        Toast.makeText(this, "Subtracting the numbers", Toast.LENGTH_SHORT).show();
                        choice = 2;
                        break;
                    }
                case R.id.multiply:
                    if (checked) {
                        Log.i(TAG, "Multiplying numbers");
                        Toast.makeText(this, "Multiplying the numbers", Toast.LENGTH_SHORT).show();
                        choice = 3;
                        break;
                    }
                case R.id.divide:
                    if (checked) {
                        Log.i(TAG, "Dividing numbers");
                        Toast.makeText(this, "Dividing the numbers", Toast.LENGTH_SHORT).show();
                        choice = 4;
                        break;
                    }
            }
        }
        performOperation();
    }

    private void performOperation() {

        if(choice==-1) {
            Toast.makeText(this, "Enter numbers and select the radio button again to continue", Toast.LENGTH_SHORT).show();
        } else {
            int num1 = -1, num2 = -1;
            int flag = 1;
            try {
                num1 = Integer.parseInt(editText1.getText().toString());
                num2 = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException e) {
                flag = 0;
                textView.setBackgroundColor(Color.parseColor("#BEE0EC"));
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
            }

            if(flag==1) {
                switch (choice) {
                    case 1:
                        result = num1 + num2;
                        break;

                    case 2:
                        result = num1 - num2;
                        break;

                    case 3:
                        result = num1 * num2;
                        break;

                    case 4:
                        result = round((1.0*num1)/(1.0*num2),2);
                        break;

                }

                textView.setBackgroundColor(Color.parseColor("#90ee90"));
                textView.setText(result + " is the computed answer");

            }

        }

    }

    public static double round(double value, int places) {
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}