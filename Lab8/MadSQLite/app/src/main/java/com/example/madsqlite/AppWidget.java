package com.example.madsqlite;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RemoteViews;

import com.example.madsqlite.db.DbHelper;
import com.example.madsqlite.db.ReaderContract;

public class AppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
        views.setTextViewText(R.id.appwidget_text, "widgetText");

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        DbHelper dbHelper;
        SQLiteDatabase dbRead;

        dbHelper = new DbHelper(context);
        dbRead = dbHelper.getReadableDatabase();

        Cursor cursor = dbRead.rawQuery("SELECT * FROM "+ ReaderContract.TableEntry.TABLE_NAME, null);

        CharSequence widgetText = "";
        String str = "";

        while(cursor.moveToNext()) {

            String pname, pid, pprice, pmrp;

            pname = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PNAME));
            pid = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PID));
            pprice = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_PRICE));
            pmrp = cursor.getString(
                    cursor.getColumnIndexOrThrow(ReaderContract.TableEntry.COLUMN_NAME_MRP));

            str += "-" + pname + " , " + pprice +"\n";
        }
        cursor.close();
        widgetText = str;

        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.app_widget);
            views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);
            views.setTextViewText(R.id.appwidget_text, widgetText);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    @Override
    public void onEnabled(Context context) {

    }

    @Override
    public void onDisabled(Context context) {

    }
}

