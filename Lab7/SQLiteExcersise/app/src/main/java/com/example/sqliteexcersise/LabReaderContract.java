package com.example.sqliteexcersise;

import android.provider.BaseColumns;

public final class LabReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LabReaderContract() {}

    /* Inner class that defines the table contents */
    public static class LabEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_USERNAME = "UserName";
        public static final String COLUMN_NAME_NAME = "Name";
        public static final String COLUMN_NAME_PASS = "Password";
        public static final String COLUMN_NAME_EMAIL = "Email";
        public static final String COLUMN_NAME_PHONE = "Phone";
    }
}
