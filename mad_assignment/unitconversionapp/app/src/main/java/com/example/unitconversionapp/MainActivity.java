package com.example.unitconversionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    FragmentContainerView fcv;
    TextView tvWelcome1, tvWelcome2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.app_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment = new MainFragment();

        Bundle bundle = new Bundle();

        switch (item.getItemId()) {
            case R.id.lengthItem:
                bundle.putString("Type", "Length");
                break;

            case R.id.areaItem:
                bundle.putString("Type", "Area");
                break;

            case R.id.weightItem:
                bundle.putString("Type", "Weight");
                break;

            case R.id.currencyItem:
                bundle.putString("Type", "Currency");
                break;

            case R.id.timeItem:
                bundle.putString("Type", "Time");
                break;

            default:
                finish();

        }

        tvWelcome1.setVisibility(View.GONE);
        tvWelcome2.setVisibility(View.GONE);

        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fcv, fragment)
                .commit();

        return true;
    }

    private void bindViews() {
        fcv = findViewById(R.id.fcv);
        tvWelcome1 = findViewById(R.id.tvelcome);
        tvWelcome2 = findViewById(R.id.tvelcome2);
    }

}