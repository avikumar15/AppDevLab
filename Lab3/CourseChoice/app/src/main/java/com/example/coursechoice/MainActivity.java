package com.example.coursechoice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements UpdateResultInterface {

    TextView tvWelcome, tvResult;
    FragmentContainerView fragmentContainerView;
    Fragment mFragmentSelection, mFragmentDetails;
    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> btnText = new ArrayList<>();
    Button btnNext;

    int currentFragment = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFragments();
        setFirstFragment();
    }

    private void initFragments() {
        mFragmentDetails = new FragmentDetails();
        mFragmentSelection = new FragmentSelection();

        ((FragmentDetails) mFragmentDetails).setInterface(this);
        ((FragmentSelection) mFragmentSelection).setInterface(this);

        fragments.add(mFragmentDetails);
        fragments.add(mFragmentSelection);
    }

    private void setFirstFragment() {
        transactFragment();
    }

    private void initViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        fragmentContainerView = findViewById(R.id.fragmentContainer);
        btnNext = findViewById(R.id.btnNext);
        tvResult = findViewById(R.id.tvResult);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performClick();
            }
        });

        btnText.add("NEXT");
        btnText.add("SUBMIT");

        btnNext.setText(btnText.get(currentFragment%2));
    }

    private void performClick() {
        transactFragment();
    }

    public void transactFragment() {
        int mod = currentFragment%2;

        Bundle bundle = new Bundle();

        if(mod==1) {
            String roll = ((FragmentDetails) mFragmentDetails).getEditTextText();
            if(roll.equals("")) {
                Toast.makeText(this, "Roll number can't be empty", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            bundle.putString("ROLL_NUMBER", roll);
        } else if(currentFragment!=0) {
            ArrayList<String> courseRegistered = ((FragmentSelection) mFragmentSelection)
                    .getCoursesRegistered();

            for(String s: courseRegistered) {
                Log.i("TAG", "MainActivity "+s);
            }
            if(courseRegistered.size()<3) {
                Toast.makeText(this, "Please select at least 3 courses and continue", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
            bundle.putStringArrayList("COURSES", courseRegistered);
        }

        fragments.get(mod).setArguments(bundle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, fragments.get(mod))
                .commit();

        currentFragment++;
    }

    @Override
    public void setResultTextView(String res) {
        tvResult.setText(res);
        tvResult.setBackgroundColor(Color.parseColor("#98FB98"));
        tvResult.setPadding(20,10,20,10);
        btnNext.setText(btnText.get((currentFragment+1)%2));
    }
}