package com.Snake;

import com.Board.Board;
import com.Board.BodyCell;
import com.Board.Cell;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Snake implements ChangeDirection {
    private int length;
    private Direction direction;
    private List<Cell> body;// głowa węża to pierwsza pozycja listy
    public Scanner scanner;
    public Thread listener;





    public Snake(int length)
    {
        this.length=length;
        this.direction=Direction.RIGHT;
        body=new ArrayList<Cell>();
        createSnake(length);
        scanner=new Scanner(System.in);
        listener=new Thread(this::changeDirection); // specjalny wątek na słuchanie z istreama
        listener.start();
    }

    private void createSnake(int length) { //inicjalizacja węża
        for(int i=0;i<length;i++)
        {
            body.add(new Cell(BodyCell.BODYSNAKE,length-i,1));
        }
    }

    public void drawSnake(Board board) {
        for(Cell cell: board.getCells())
        {
            for(Cell bodyCell: body)
            {
                if(cell.getX()==bodyCell.getX() && cell.getY()==bodyCell.getY()) {
                    cell.setBodyCell(BodyCell.BODYSNAKE);
                    break;
                }
            }
        }
    }

    public void changeDirection()
    {

        char input;
        while(true) {
            switch (input = scanner.nextLine().charAt(0)) {
                case 'a':
                    this.direction = Direction.LEFT;
                    break;
                case 'w':
                    this.direction = Direction.UP;
                    break;
                case 'd':
                    this.direction = Direction.RIGHT;
                    break;
                case 's':
                    this.direction = Direction.DOWN;
                    break;
                default:
                    break;
            }
        }
    }


    public void makeMove(Board board) {

        ArrayList<Cell> copiedList= new ArrayList<>(); // kopiowanie całego ciała sprzed zrobienia ruchu
        for(Cell cell: body)
        {
            copiedList.add(new Cell(cell.getBodyCell(),cell.getX(),cell.getY()));
        }
        setHead(this.direction);

        for(int i=0;i<length-1;i++)
        {
            body.get(i+1).setX(copiedList.get(i).getX());
            body.get(i+1).setY(copiedList.get(i).getY());
        }

            //ustawienie ostatniego na empty
        board.getCell(copiedList.get(length-1).getX(),copiedList.get(length-1).getY()).setBodyCell(BodyCell.EMPTY);

    }
    public void setHead(Direction direction){
        if(direction==Direction.RIGHT)
        {
            body.get(0).setX(body.get(0).getX()+1);
        }
        else if(direction==Direction.LEFT)
        {

            body.get(0).setX(body.get(0).getX()-1);
        }
        else if(direction==Direction.UP)
        {
            body.get(0).setY(body.get(0).getY()-1);
        }
        else
        {
            body.get(0).setY(body.get(0).getY()+1);
        }
    }




    public boolean checkCollision(Board board) {
        if((body.get(0).getX()<=0 || body.get(0).getX()>=board.getX()-2)
                || (body.get(0).getY()<=0 || body.get(0).getY()>=board.getY()-2))
        {
            return true;
        }
        if(checkHeadWithOtherBody())
        {
            return true;
        }
        return false;
    }

    private boolean checkHeadWithOtherBody() {
        for(int i=4; i<body.size();i++)
        {
            if(body.get(0).getX()==body.get(i).getX() && body.get(0).getY()==body.get(i).getY())
            {
                return true;
            }
        }
        return false;
    }

    public boolean getFood(Cell cell) { // sprawdzanie czy natrafiło na jedzenie
        if(body.get(0).getX()==cell.getX() && body.get(0).getY()==cell.getY())
        {
            return true;
        }
        return false;
    }

    public void improve() { // wydłuż węża

        Cell lastPartBody=body.get(length-1);
        ++length;
        body.add(new Cell(BodyCell.BODYSNAKE,lastPartBody.getX(),lastPartBody.getY()+1));
    }
}
