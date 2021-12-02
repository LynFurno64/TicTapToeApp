package com.example.tictactoeapp.Boards.Logic;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLogic {
    private int [][] gameBoard;
    private Button playAgainBtn;
    private Button homeBtn;
    private TextView playerTurn;
    private String[] name = {"Player 1","Player 2"};

    private TextView p1score;
    private TextView p2score;
    int player1_score = 0;
    int player2_score = 0;

    private int player = 1;

    public GameLogic(int gridSize) {
        gameBoard = new int[gridSize][gridSize];
        for (int r=0; r< gridSize; r++){
            for (int c=0; c< gridSize; c++){
                gameBoard[r][c] = 0;
            }
        }
    }// GameLogic

    public boolean updateGameBoard(int row,int col){
        if (gameBoard[row-1][col-1]==0){
            gameBoard[row-1][col-1]= player;

            if (player==1){
                playerTurn.setText(("Currently"+" "+name[1]+"'s Turn"));
            }
            else {
                playerTurn.setText(("Currently"+" "+name[0]+"'s Turn"));
            }

            return true;
        }
        else {
            return false;
        }
    }// updateGameBoard

    @SuppressLint("SetTextI18n")
    public void resetGame(int gridSize){
        for (int r=0; r< gridSize; r++){
            for (int c=0; c< gridSize; c++){
                gameBoard[r][c] = 0;
            }
        }
        player = 1;
        playAgainBtn.setVisibility(View.GONE);
        homeBtn.setVisibility(View.GONE);
        playerTurn.setText(("Current Player"+" "+name[0]+"'s Turn"));
    }// resetGame

    @SuppressLint("SetTextI18n")
    // keep track of players score
    public void scoreBoard(String called){
        //check if player 1
        if (called.equals(name[0])){
            player1_score++;
            p1score.setText(name[0] + "Score : "+ player1_score);
        }
        // //check if player 2
        if (called.equals(name[1])){
            player2_score++;
            p2score.setText(name[1] + "Score : "+ player2_score);
        }
    }

    public void setP1score(TextView p1score) {
        this.p1score = p1score;
    }

    public void setP2score(TextView p2score) {
        this.p2score = p2score;
    }

    public void setHomeBtn(Button homeBtn) {
        this.homeBtn = homeBtn;
    }
    public void setPlayerTurn(TextView playerTurn){
        this.playerTurn = playerTurn;
    }
    public void setPlayAgainBtn(Button playAgainBtn) {
        this.playAgainBtn = playAgainBtn;
    }
    public void setName(String[] name) {
        this.name = name;
    }

    public int[][] getGameBoard() { return gameBoard; }

    public void setPlayer(int player) { this.player = player; }

    public int getPlayer() { return player; }


    //------------------------------WIN CONDITIONS---------------------------------\\
    @SuppressLint("SetTextI18n")
    public boolean winConditions3x3(){
        boolean win = false;
        //win logic for 4x4
        //horizontal
        for (int r =0; r<3; r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][2] &&
                    gameBoard[r][0] != 0) {
                win = true;
            }
        }
        //vertical
        for (int c =0; c<3; c++){
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[2][c] &&
                    gameBoard[0][c] != 0) {
                win = true;
            }
        }
        //diagonal
        if (gameBoard[0][0] == gameBoard[1][1]&& gameBoard[0][0] == gameBoard[2][2]&&
                gameBoard[0][0] != 0){
            win = true;
        }
        if (gameBoard[2][0] == gameBoard[1][1]&& gameBoard[2][0] == gameBoard[0][2]&&
                gameBoard[2][0] != 0){
            win = true;
        }
        int boardFilled = 0;
        for (int r=0; r<3; r++){
            for (int c=0; c<3; c++){
                if (gameBoard[r][c] != 0){
                    boardFilled +=1;
                }
            }
        }
        if (win){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((name[player-1] + " WON !!!!!!!"));
            scoreBoard(name[player-1]);
            return true;
        }
        else if (boardFilled == 9 ){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText("Someone Please win!!!!!");
            return true;
        }
        else {
            return false;
        }
    }// winConditions3x3

    @SuppressLint("SetTextI18n")
    public boolean winConditions4x4(){
        boolean win = false;

        //win logic for 4x4
        //horizontal
        for (int r =0; r<4; r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][3] && gameBoard[r][0] != 0)
            {
                win = true;
            }
        }
        //vertical
        for (int c =0; c<4; c++){
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[3][c] && gameBoard[0][c] != 0)
            {
                win = true;
            }
        }
        //diagonal
        if (gameBoard[0][0] == gameBoard[1][1]&& gameBoard[0][0] == gameBoard[3][3]&& gameBoard[0][0] != 0)
        {
            win = true;
        }
        if (gameBoard[3][0] == gameBoard[1][1]&& gameBoard[3][0] == gameBoard[0][3]&& gameBoard[3][0] != 0)
        {
            win = true;
        }
        int boardFilled = 0;
        for (int r=0; r<4; r++){
            for (int c=0; c<4; c++){
                if (gameBoard[r][c] != 0){
                    boardFilled +=1;
                }
            }
        }

        if (win){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((name[player-1] + " WON !!!!!!!"));
            scoreBoard(name[player-1]);
            return true;
        }
        else if (boardFilled == 20 ){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText("Someone Please win!!!!!");
            return true;
        }
        else {
            return false;
        }
    }// winConditions4x4

    @SuppressLint("SetTextI18n")
    public boolean winConditions5x5(){
        boolean win = false;

        //win logic for 5x5
        //horizontal
        for (int r =0; r<5; r++){
            if (gameBoard[r][0] == gameBoard[r][1] && gameBoard[r][0] == gameBoard[r][4] && gameBoard[r][0] != 0)
            {
                win = true;
            }
        }
        //vertical
        for (int c =0; c<5; c++){
            if (gameBoard[0][c] == gameBoard[1][c] && gameBoard[0][c] == gameBoard[4][c] && gameBoard[0][c] != 0)
            {
                win = true;
            }
        }
        //diagonal
        if (gameBoard[0][0] == gameBoard[1][1]&& gameBoard[0][0] == gameBoard[4][4]&& gameBoard[0][0] != 0)
        {
            win = true;
        }
        if (gameBoard[4][0] == gameBoard[1][1]&& gameBoard[4][0] == gameBoard[0][4]&& gameBoard[4][0] != 0)
        {
            win = true;
        }
        //board
        int boardFilled = 0;
        for (int r=0; r<5; r++){
            for (int c=0; c<5; c++){
                if (gameBoard[r][c] != 0){
                    boardFilled +=1;
                }
            }
        }

        if (win){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText((name[player-1] + " WON !!!!!!!"));
            scoreBoard(name[player-1]);
            return true;
        }
        else if (boardFilled == 25  ){
            playAgainBtn.setVisibility(View.VISIBLE);
            homeBtn.setVisibility(View.VISIBLE);
            playerTurn.setText("Someone Please win!!!!!");
            return true;
        }
        else {
            return false;
        }
    }// winConditions5x5
}