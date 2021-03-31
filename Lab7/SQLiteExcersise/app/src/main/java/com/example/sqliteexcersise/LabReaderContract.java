package com.example.sqliteexcersise;

import android.provider.BaseColumns;

public final class LabReaderContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private LabReaderContract() {}

    /* Inner class that defines the table contents */
    public static class LabEntry implements BaseColumns {
        public static final String TABLE_NAME = "Table1";
        public static final String COLUMN_NAME_TITLE = "Column_Title";
        public static final String COLUMN_NAME_SUBTITLE = "Column_Subtitle";
    }
}
