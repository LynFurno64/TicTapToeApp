package com.example.tictactoeapp;

import android.graphics.Color;
import android.graphics.Paint;

public class Methods {
    public Paint paintX = new Paint();
    public Paint paintO = new Paint();


    public Paint getPaintX() {
        paintX.setColor(Color.RED);
        return paintX;
    }

    public Paint getPaintO() {
        paintX.setColor(Color.BLUE);
        return paintX;
    }
}
