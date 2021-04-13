package com.example.madsqlite.db;

import android.provider.BaseColumns;

public final class ReaderContract {

    private ReaderContract() {}

    public static class TableEntry implements BaseColumns {
        public static final String TABLE_NAME = "Product";
        public static final String COLUMN_NAME_PID = "Pid";
        public static final String COLUMN_NAME_PNAME = "PName";
        public static final String COLUMN_NAME_MRP = "MRP";
        public static final String COLUMN_NAME_PRICE = "Price";
    }
}
