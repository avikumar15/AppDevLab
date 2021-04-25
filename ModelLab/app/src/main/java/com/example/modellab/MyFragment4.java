package com.example.modellab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyFragment4 extends Fragment implements View.OnClickListener {

    AppInterface appInterface = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        setClickListeners();

        if(getArguments()!=null) {

        } else {
            Utils.createToast(getContext(), "Something fishy");
        }

        return view;
    }

    private void setClickListeners() {

    }

    @Override
    public void onClick(View view) {

    }

    public void setInterface(AppInterface resultInterface) {
        appInterface = resultInterface;
    }

}
