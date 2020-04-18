package com.Game;

import com.Board.Board;
import com.Snake.Snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Game  {
    private Board board;
    private Snake snake;

    public Game(Board board, Snake snake)
    {
        this.board=board;
        this.snake=snake;
    }



    public void run() throws InterruptedException {
        while(true)
        {
            board.drawBoard(snake);
            Thread.sleep(500);
            snake.makeMove(board);
            if(board.ifCollision)
                break;
            clearScreen();
        }
    }


    public void clearScreen()
    {
        try
        {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
