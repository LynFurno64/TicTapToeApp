package com.example.tictactoeapp.Boards;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.tictactoeapp.Boards.Logic.GameLogic;

public interface GridBoard {
    Paint colorPaintX = new Paint();
    Paint paintO = new Paint();
    Paint mDrawPaint = new Paint();


    //--------------------For loop to Create the board---------------------------------\\
    default void drawGameBoard(Canvas canvas, int gridSize, int cellSize){
        mDrawPaint.setColor(Color.BLACK);
        mDrawPaint.setStyle(Paint.Style.STROKE);
        mDrawPaint.setAntiAlias(true);
        mDrawPaint.setStrokeWidth(16);

        for (int col=1; col< gridSize; col++){
            canvas.drawLine(cellSize*col, 0, cellSize*col,canvas.getWidth(), mDrawPaint);
        }
        for (int row=1; row< gridSize; row++){
            canvas.drawLine(0, cellSize*row, canvas.getWidth(),cellSize*row, mDrawPaint);
        }
    }// drawGameBoard

    //----------------------------Draw X or O on the Board--------------------------------\\
    default void drawMarkers(Canvas canvas, int gridSize, int cellSize, GameLogic game){
        for (int r=0; r < gridSize; r++){
            for (int c=0; c < gridSize; c++){
                if (game.getGameBoard()[r][c] != 0){
                    if (game.getGameBoard()[r][c] == 1){
                        DrawX(canvas,r,c, cellSize);
                    }
                    else {
                        DrawO(canvas,r,c, cellSize);
                    }
                }
            }
        }
    }// drawMarkers

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
}
