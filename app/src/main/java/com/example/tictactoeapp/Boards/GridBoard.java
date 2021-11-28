package com.example.tictactoeapp.Boards;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.Button;
import android.widget.TextView;

public interface GridBoard {
    Paint colorPaintX = new Paint();
    Paint paintO = new Paint();

    //--------------------For loop to Create the board---------------------------------\\
    default void drawGameBoard(Canvas canvas, int gridType, int cellSize, Paint drawing){
        for (int col=1; col<gridType; col++){
            canvas.drawLine(cellSize*col, 0, cellSize*col,canvas.getWidth(), drawing);
        }
        for (int row=1; row<gridType; row++){
            canvas.drawLine(0, cellSize*row, canvas.getWidth(),cellSize*row, drawing);
        }
    }// drawGameBoard

    //-----------------------X and O pieces-------------------------\\
    default void DrawX(Canvas canvas, int row, int col, int cellSize){
        colorPaintX.setColor(Color.RED);
        colorPaintX.setStyle(Paint.Style.STROKE);
        colorPaintX.setAntiAlias(true);
        colorPaintX.setStrokeWidth(16);

        // creates X
        canvas.drawLine((float) ((col+1)*cellSize - cellSize*0.2),
                (float) (row*cellSize +cellSize*0.2),
                (float) (col*cellSize +cellSize*0.2),
                (float) ((row+1)*cellSize -cellSize*0.2),
                colorPaintX);

        canvas.drawLine((float) (col*cellSize + cellSize*0.2),
                (float) (row*cellSize + cellSize*0.2),
                (float) ((col+1)*cellSize - cellSize*0.2),
                (float) ((row+1)*cellSize - cellSize*0.2),
                colorPaintX);
    }// DrawX

    default void DrawO(Canvas canvas, int row , int col, int cellSize){
        paintO.setColor(Color.BLUE);
        paintO.setStyle(Paint.Style.STROKE);
        paintO.setAntiAlias(true);
        paintO.setStrokeWidth(16);

        canvas.drawOval((float) (col*cellSize + cellSize*0.2),
                (float) (row*cellSize + cellSize*0.2),
                (float) ((col*cellSize+ cellSize) -cellSize*0.2),
                (float) ((row*cellSize +cellSize) -cellSize*0.2),
                paintO);
    }//DrawO

    void gameTime(Button playAgain, Button home, TextView playDisplay, String[] name);
    void resetGame();
}
