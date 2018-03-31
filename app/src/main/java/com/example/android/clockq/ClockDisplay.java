package com.example.android.clockq;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by User on 30-03-2018.
 */

public class ClockDisplay extends View {


    int height, width = 0;
    int padding = 10;
    Paint paint;
    int fontSize = 0;
    int numeralSpacing = 0;
    int radius = 0;
    boolean isInit;
    int numbers[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    Rect rect = new Rect();
    Random rnd = new Random();
    int secondAngle;
    int minuteAngle;
    int hourAngle;
    int Hour;
    int Minute;
    int Second;
    boolean hourCheck;
    boolean minuteCheck;
    boolean secondCheck;


    public ClockDisplay(Context context) {
        super(context);
    }

    public ClockDisplay(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockDisplay(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initClock() {
        height = getHeight();
        width = getWidth();
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13, getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / 2;
        paint = new Paint();
        isInit = true;
        secondAngle = rnd.nextInt(360);
        minuteAngle = rnd.nextInt(360);
        // hourAngle = rnd.nextInt(360);
        hourAngle = 25;


    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInit) {
            initClock();
        }
        canvas.drawColor(Color.BLACK);
        drawCircle(canvas);
        //drawNumerals(canvas);
        drawCenter(canvas);
        drawHoursHands(canvas);
        drawMinutesHands(canvas);
        drawSecondsHands(canvas);
        drawHoursLines(canvas);
        drawMinutesLines(canvas);


    }

    private void drawHoursHands(Canvas canvas) {
        paint.setColor(getResources().getColor(android.R.color.holo_red_dark));

        int hourRadius = radius - 125;
        canvas.drawLine(width / 2, height / 2, (float) (width / 2 + Math.cos(Math.toRadians(hourAngle)) * hourRadius), (float) (width / 2 + Math.sin(Math.toRadians(hourAngle)) * hourRadius), paint);

    }

    private void drawMinutesHands(Canvas canvas) {
        paint.setColor(getResources().getColor(android.R.color.holo_green_dark));

        int minuteRadius = radius - 60;
        canvas.drawLine(width / 2, height / 2, (float) (width / 2 + Math.cos(Math.toRadians(minuteAngle)) * minuteRadius), (float) (width / 2 + Math.sin(Math.toRadians(minuteAngle)) * minuteRadius), paint);

    }

    private void drawSecondsHands(Canvas canvas) {
        paint.setColor(getResources().getColor(android.R.color.holo_blue_dark));

        int secondRadius = radius - 30;
        canvas.drawLine(width / 2, height / 2, (float) (width / 2 + Math.cos(Math.toRadians(secondAngle)) * secondRadius), (float) (width / 2 + Math.sin(Math.toRadians(secondAngle)) * secondRadius), paint);

    }

    private void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, 12, paint);
    }

    /* private void drawNumerals(Canvas canvas) {
             paint.setTextSize(fontSize);
             for(int number : numbers){
                 String temp = String.valueOf(numbers);
                 paint.getTextBounds(temp,0,temp.length(),rect);
                 double angle = Math.PI/6*(number-3);
                 int x=(int)(width/2+Math.cos(angle)*radius-rect.width()/2);
                 int y = (int)(height/2+Math.sin(angle)*radius+rect.height()/2);
                 canvas.drawText(temp,x,y,paint);

             }


     }*/
    private void drawHoursLines(Canvas canvas) {
        paint.setColor(getResources().getColor(android.R.color.holo_orange_dark));
        int theta = 0;
        double r;
        int x1, y1, x2, y2;
        while (theta <= 360) {
            r = Math.toRadians(theta);
            x1 = (int) ((radius) + radius * Math.cos(r));
            y1 = (int) ((radius) + radius * Math.sin(r));
            x2 = (int) ((radius) + (radius - 50) * Math.cos(r));
            y2 = (int) ((radius) + (radius - 50) * Math.sin(r));
            canvas.drawLine(x1, y1, x2, y2, paint);
            theta = theta + 30;
        }
    }

    private void drawMinutesLines(Canvas canvas) {
        paint.setColor(getResources().getColor(android.R.color.holo_purple));
        int theta = 0;
        double r;
        int a1, b1, a2, b2;
        while (theta <= 360) {
            r = Math.toRadians(theta);
            a1 = (int) ((radius) + radius * Math.cos(r));
            b1 = (int) ((radius) + radius * Math.sin(r));
            a2 = (int) ((radius) + (radius - 25) * Math.cos(r));
            b2 = (int) ((radius) + (radius - 25) * Math.sin(r));
            canvas.drawLine(a1, b1, a2, b2, paint);
            theta = theta + 6;
        }
    }


    private void drawCircle(Canvas canvas) {

        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2, height / 2, radius, paint);
    }

    public void getInputsEditText() {
        TextView Summary = (TextView) findViewById(R.id.DisplaySummery);
        EditText hours = (EditText) findViewById(R.id.Hours);
        String hoursET = hours.getText().toString();

        try {
            Hour = Integer.parseInt(String.valueOf(hoursET));
        } catch (NullPointerException e) {
            Summary.setText("Enter Hours");
        }
        EditText minutes = (EditText) findViewById(R.id.Minutes);
        String minuteET = minutes.getText().toString();

        try {
            Minute = Integer.parseInt(String.valueOf(minuteET));
        } catch (NullPointerException e) {
            Summary.setText("Enter Minutes");
        }
        EditText seconds = (EditText) findViewById(R.id.Seconds);
        String secondET = seconds.getText().toString();

        try {
            Second = Integer.parseInt(String.valueOf(secondET));
        } catch (NullPointerException e) {
            Summary.setText("Enter Seconds");
        }
    }


    public void checkValues(View view) {
        TextView Summary = (TextView) findViewById(R.id.DisplaySummery);
        if (Hour == 3 && hourAngle > 0 && hourAngle <= 30) {
            hourCheck = true;
            Summary.setText("correct");
        } else if (Hour == 4 && hourAngle > 30 && hourAngle <= 60) {
            hourCheck = true;
            Summary.setText("correct");
        } else {
          Summary.setText("Entered");
        }
    }




}


