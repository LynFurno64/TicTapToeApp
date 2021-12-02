package com.example.tictactoeapp.Boards;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoeapp.Boards.Logic.GameLogic;


public class TicTacToeBoard4x4 extends View implements GridBoard{
    private int cellSize;
    private final GameLogic game = new GameLogic(4);
    private boolean winLine = false;

    public TicTacToeBoard4x4(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // Set Dimensions of Board
    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);

        //find the smallest dimension of the Device
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        Log.i("Testing: dimension is =", String.valueOf(dimension));

        cellSize = dimension/4; // 4 boxes in each row & column
        Log.i("Testing: cellsize is = ", String.valueOf(cellSize));

        setMeasuredDimension(dimension, dimension); // To draw a Square
    }// onMeasure

    @Override
    protected void onDraw(Canvas canvas){
        drawGameBoard(canvas, 4, cellSize);
        drawMarkers(canvas, 4, cellSize, game);
    }// onDraw

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        float x = event.getX();
        float y = event.getY();

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN){
            int row = (int) Math.ceil(y/cellSize);
            int col = (int) Math.ceil(x/cellSize);

            if (!winLine){
                if (game.updateGameBoard(row,col)){
                    invalidate();

                    if(game.winConditions4x4()){
                        winLine = true;
                        invalidate();
                    }

                    //updating the players turn
                    if (game.getPlayer()% 2 ==0){
                        game.setPlayer(game.getPlayer()-1);
                    }
                    else {
                        game.setPlayer(game.getPlayer()+1);
                    }
                }
            }
            invalidate();
            return true;
        }
        return false;
    }

    //setup the game
    public void gameTime(Button playAgain,Button home,TextView playDisplay,String[] name,TextView player1Score,TextView player2Score){
        game.setPlayAgainBtn(playAgain);
        game.setHomeBtn(home);
        game.setPlayerTurn(playDisplay);
        if (!name[0].equals("") && !name[1].equals("")){
            game.setName(name);
        }
        game.setP1score(player1Score);
        game.setP2score(player2Score);
    }

    public void resetGame(){
        game.resetGame(4);
        winLine = false;
    }// resetGame

}
