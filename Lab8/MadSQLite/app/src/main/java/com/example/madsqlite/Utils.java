package com.example.madsqlite;

import android.content.Context;
import android.widget.Toast;

public class Utils {

    public static String USER_NAME = "NAME";
    public static String USER_PASS = "PASS";
    public static String USER_ID = "_ID_";


    public static String SP_USER_NAME = "SP_NAME";
    public static String SP_USER_PASS = "SP_PASS";

    public static void createToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
