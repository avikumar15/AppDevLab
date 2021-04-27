package com.example.modellab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static com.example.modellab.Utils.createToast;

public class MainFragment extends Fragment implements View.OnClickListener {

    RadioButton rbMusic, rbMovies, rbClock;
    Button btnSubmit;

    AppInterface appInterface;
    int choice=-1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        rbMusic = view.findViewById(R.id.rbMusic);
        rbMovies = view.findViewById(R.id.rbMovies);
        rbClock = view.findViewById(R.id.rbClock);

        btnSubmit = view.findViewById(R.id.btnGo);

        rbClock.setOnClickListener(this);
        rbMovies.setOnClickListener(this);
        rbMusic.setOnClickListener(this);

        btnSubmit.setOnClickListener(this);

        return view;
    }

    public void setAppInterface(AppInterface appInterface) {
        this.appInterface = appInterface;
    }

    public void removeOnClick() {
        btnSubmit.setOnClickListener(null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rbMusic:
                choice = 1;
                break;
            case R.id.rbMovies:
                choice = 2;
                break;
            case R.id.rbClock:
                choice = 3;
                break;
            case R.id.btnGo:
                appInterface.onClickedButton(choice);
                break;

        }
    }
}
