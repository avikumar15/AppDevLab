package com.example.intentuse;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static String NAME_KEY = "NAME_KEY";
    public static String ROLL_KEY = "ROLL_KEY";

    public static void createToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
