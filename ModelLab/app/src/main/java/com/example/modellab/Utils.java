package com.example.modellab;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static String NAME = "NAME";
    public static String EMAIL = "EMAIL";
    public static String MOBILE = "MOBILE";
    public static String HIGH_SCORE = "HIGH_SCORE";

    public static String UNAME = "UNAME";
    public static String PASS = "PASS";

    public static String INTENT_EXTRA1 = "INTENT_EXTRA1";
    public static String INTENT_EXTRA2 = "INTENT_EXTRA2";
    public static String INTENT_EXTRA3 = "INTENT_EXTRA3";
    public static String INTENT_EXTRA4 = "INTENT_EXTRA4";
    public static String INTENT_EXTRA5 = "INTENT_EXTRA5";

    public static String SP_KEY = "SP_KEY";

    public static void createToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
