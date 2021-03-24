package com.example.menuapp;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static String NAME = "NAME";
    public static String EMAIL = "EMAIL";
    public static String MOBILE = "MOBILE";

    public static void createToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
