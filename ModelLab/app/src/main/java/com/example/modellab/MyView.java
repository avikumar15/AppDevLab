package com.example.modellab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyView extends View {

    private int viewWidthFinal;
    private int viewHeightFinal;
    private int diceViewEdge;
    private double x = Math.sin(Math.PI);
    private double t = Math.PI;
    private double RATE_OF_OSCILLATION = 1.1;
    private int currentDice;

    // Flag variable to instantiate bitmap list only once
    private boolean isBitmapSet = false;
    // Flag to check when button is pressed
    private boolean isReady = false;
    // Flag to check if the view bitmap is changed
    private boolean isInTransition = true;

    private int frequency;
    private int value;

    private AppInterface appInterface;

    private ArrayList<Bitmap> bitmaps = new ArrayList();

    static String TAG = "DiceView";

    public MyView(Context context) {
        super(context);
        Log.i(TAG, "Constructor 1 is called");
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "Constructor 2 is called");
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "Constructor 3 is called");
        init();
    }

    public void setRequirements(int frequency, int value) {
        this.frequency = frequency;
        this.value = value;
    }

    private void init() {
        viewHeightFinal = 1000;
        viewWidthFinal = 1000;
        diceViewEdge = 500;
        currentDice = 0;
    }

    public void setInterface(AppInterface mDiceInterface) {
        this.appInterface = mDiceInterface;
    }

    public void startShaking() {
        isReady = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.i(TAG, "onMeasure() is called");

        int widthXml = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightXml = MeasureSpec.getSize(heightMeasureSpec);

        Log.i(TAG, "widthXml = " + widthXml + " and heightXml = " + heightXml);

        int viewWidthDesirable = 1000;
        viewWidthFinal = Math.min(widthXml, viewWidthDesirable);
        int viewHeightDesirable = 1000;
        viewHeightFinal = Math.min(heightXml, viewHeightDesirable);

        // since this view is square, assigning width and height to the min of original width and height
        viewHeightFinal = Math.min(viewHeightFinal, viewWidthFinal);
        viewWidthFinal = viewHeightFinal;

        // edge is half the width length, since some space is left for oscillations
        diceViewEdge = viewWidthFinal / 2;

        Log.i(TAG, "viewWidthFinal = " + viewWidthFinal + " and viewHeightFinal = " + viewHeightFinal);

        setMeasuredDimension(viewWidthFinal, viewHeightFinal);

        if (!isBitmapSet)
            setBitmap();
    }

    private void setBitmap() {

    }

}
