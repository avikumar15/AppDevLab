package com.example.unitconversionapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    TextView tv;
    Spinner spinner;
    EditText et;
    String type="";
    Bundle bundle;
    ArrayAdapter<CharSequence> adapter;
    ArrayList<Double> ratioValue;
    RecyclerView rv;
    MyAdapter rvAdapter;
    Double ratio;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout,container, false);

        tv = v.findViewById(R.id.tvFrag);
        spinner = v.findViewById(R.id.spv);
        et = v.findViewById(R.id.etFrag);
        rv = v.findViewById(R.id.rv);

        ratioValue = new ArrayList<>();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bundle = getArguments();
        type = bundle.getString("Type");

        tv.setText(type);

        switch(type) {
            case "Length":
                adapter = ArrayAdapter.createFromResource(
                        getActivity().getBaseContext(),
                        R.array.length,
                        R.layout.spinner_layout
                );
                ratioValue = new ArrayList<Double>() {{
                    add(1000000.0);
                    add(1000.0);
                    add(1.0);
                    add(39.3701);
                    add(1.09);
                    add(0.000621371);
                }};
                break;
            case "Area":
                adapter = ArrayAdapter.createFromResource(
                        getActivity().getBaseContext(),
                        R.array.area,
                        R.layout.spinner_layout
                );
                ratioValue = new ArrayList<Double>() {{
                    add(1000000.0*1000000.0);
                    add(1000.0*1000.0);
                    add(1.0);
                    add(39.3701*39.3701);
                    add(1.09*1.09);
                    add(0.000621371*0.000621371);
                }};
                break;
            case "Currency":
                adapter = ArrayAdapter.createFromResource(
                        getActivity().getBaseContext(),
                        R.array.currency,
                        R.layout.spinner_layout
                );
                ratioValue = new ArrayList<Double>() {{
                    add(74.5);
                    add(1.0);
                    add(0.84);
                    add(109.3);
                    add(0.73);
                }};
                break;
            case "Weight":
                adapter = ArrayAdapter.createFromResource(
                        getActivity().getBaseContext(),
                        R.array.weight,
                        R.layout.spinner_layout
                );
                ratioValue = new ArrayList<Double>() {{
                    add(74.5);
                    add(1.0);
                    add(0.84);
                    add(109.3);
                    add(0.73);
                }};
                break;
            case "Time":
                adapter = ArrayAdapter.createFromResource(
                        getActivity().getBaseContext(),
                        R.array.time,
                        R.layout.spinner_layout
                );
                ratioValue = new ArrayList<Double>() {{
                    add(3600000.0);
                    add(3600.0);
                    add(60.0);
                    add(1.0);
                }};
                break;
        }

        adapter.setDropDownViewResource(R.layout.col_layout);
        spinner.setAdapter(adapter);

        rvAdapter = new MyAdapter(adapter, ratioValue);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    Double val = Double.parseDouble(et.getText().toString());
                    ratio = val / ratioValue.get(i);

                    for (int ind = 0; ind < ratioValue.size(); ind++) {
                        ratioValue.set(ind, ratioValue.get(ind) * ratio);
                    }
                    rvAdapter.notifyDataSetChanged();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
