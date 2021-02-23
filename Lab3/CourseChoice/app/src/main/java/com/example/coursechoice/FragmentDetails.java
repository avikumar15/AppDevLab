package com.example.coursechoice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentDetails extends Fragment {

    EditText etRollNum;
    ArrayList<String> courses = new ArrayList<>();
    UpdateResultInterface resultInterface = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        etRollNum = view.findViewById(R.id.etRollNumber);
        Log.i("TAG", "onCreateView: FragmentDetails");

        if(getArguments() != null) {
            courses = getArguments().getStringArrayList("COURSES");

            if(resultInterface!=null && courses!=null) {
                Log.i("TAG", "FragmentDetails: Got them");
                StringBuilder res = new StringBuilder("Thank You"+etRollNum.getText().toString()+".\nThe courses registered are: ");
                for(String s : courses) {
                    res.append(s).append(", ");
                }
                resultInterface.setResultTextView(res.substring(0, res.length()-3));
            }

        }

        return view;
    }

    public String getEditTextText() {
        return etRollNum.getText().toString();
    }

    public void setInterface(UpdateResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }

}
