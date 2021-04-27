package com.example.modellab;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

// Question:
//  Design a Reminder application using Android studio with the following 
// functionalities.

// Get user details such as name, age, gender, 
// I agree on the checkbox and a submit button upon opening the app.
// When the user gives all details and submit, redirect to a page that 
// takes inputs - event name, time, and radio buttons with app names 
// (Google play music, Google Play Movies, and clock). 
// Upon submitting the inputs, display the Reminder set successfully.

// Your application has to open sometime (say 5 seconds) 
// before the time given by the user. After the app gets opened, 
// it should be redirected to the application which user selected 
// from the radio buttons.

// Use toasts atleast twice wherever necessary.

