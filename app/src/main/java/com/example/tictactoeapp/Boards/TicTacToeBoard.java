package com.example.tictactoeapp.Boards;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tictactoeapp.Boards.Logic.GameLogic;


public class TicTacToeBoard extends View  implements GridBoard{
    private int cellSize;
    private final GameLogic game = new GameLogic(3);
    private boolean winLine = false;

    public TicTacToeBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // Set Dimensions of Board
    @Override
    protected void onMeasure(int width, int height){
        super.onMeasure(width, height);

        //find the smallest dimension of the Device
        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        Log.i("Testing: dimension is =", String.valueOf(dimension));

        cellSize = dimension/3; // 3 boxes in each row & column
        Log.i("Testing: cellsize is = ", String.valueOf(cellSize));

        setMeasuredDimension(dimension, dimension); // To draw a Square
    }// onMeasure

    @Override
    protected void onDraw(Canvas canvas){
        drawGameBoard(canvas, 3, cellSize);
        drawMarkers(canvas, 3, cellSize, game);
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

                    if(game.winConditions3x3()){
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
    public void gameTime(Button playAgain,Button home,TextView playDisplay,String[] name,TextView score1,TextView score2){
        game.setPlayAgainBtn(playAgain);
        game.setHomeBtn(home);
        game.setPlayerTurn(playDisplay);
        if (!name[0].equals("") && !name[1].equals("")){
            game.setName(name);
        }
        game.setScore1(score1);
        game.setScore2(score2);
    }

    public void resetGame(){
        game.resetGame(3);
        winLine = false;
    }
}
