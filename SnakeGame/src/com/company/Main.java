package com.company;

import com.Board.Board;
import com.Game.Game;
import com.Snake.Snake;

import java.io.IOException;

public class
Main {

    public static void main(String[] args) throws IOException, InterruptedException {
	    Game game=new Game(new Board(20,20), new Snake(5));
	    game.run();
	    System.out.println("You lose");
    }
}
