package com.example.coursechoice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;

public class FragmentSelection extends Fragment implements View.OnClickListener {

    ArrayList<CheckBox> rbs = new ArrayList<>();
    HashSet<String> courseRegistered = new HashSet<>();
    UpdateResultInterface resultInterface = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selection, container, false);
        populateRbs(view);
        setClickListeners();

        assert getArguments() != null;
        Log.i("TAG", "FragmentSelection: bundle value: "+getArguments().getString("ROLL_NUMBER"));

        if(resultInterface!=null) {
            resultInterface.setResultTextView("Hello Roll Number: " + getArguments().getString("ROLL_NUMBER"));
        }

        return view;
    }

    private void setClickListeners() {
        for (int i = 0; i < rbs.size(); i++)
            rbs.get(i).setOnClickListener(this);
    }

    private void populateRbs(View view) {
        rbs.add((CheckBox) view.findViewById(R.id.rbAlgorithm));
        rbs.add((CheckBox) view.findViewById(R.id.rbDSA));
        rbs.add((CheckBox) view.findViewById(R.id.rbDBMS));
        rbs.add((CheckBox) view.findViewById(R.id.rbCA));
        rbs.add((CheckBox) view.findViewById(R.id.rbFormalLanguage));
        rbs.add((CheckBox) view.findViewById(R.id.rbAutomata));
        rbs.add((CheckBox) view.findViewById(R.id.rbML));
        rbs.add((CheckBox) view.findViewById(R.id.rbAI));
    }

    @Override
    public void onClick(View view) {

        CheckBox checkBox = (CheckBox) view;
        Log.i("FragmentSelection", "Clicked");

        if(checkBox.isChecked()) {
            courseRegistered.add(checkBox.getText().toString());
            Toast.makeText(getContext(), "Added "+checkBox.getText().toString(), Toast.LENGTH_SHORT)
                    .show();
        } else {
            courseRegistered.remove(checkBox.getText().toString());
            Toast.makeText(getContext(), "Removed "+checkBox.getText().toString(), Toast.LENGTH_SHORT)
                    .show();
        }

        for (String s : courseRegistered) {
            Log.i("TAG",s);
        }
    }

    public ArrayList<String> getCoursesRegistered() {
        return new ArrayList<>(courseRegistered);
    }

    public void setInterface(UpdateResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }

}
