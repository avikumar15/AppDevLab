package com.example.modellab;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static String NAME = "NAME";
    public static String AGE = "AGE";
    public static String GENDER = "GENDER";

    public static String SP_KEY = "SP_KEY";

    public static void createToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
