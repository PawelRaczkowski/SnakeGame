package com.Board;

import com.Snake.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
    private int x,y; // rozmiary
    private List<Cell> cells;
    public boolean ifCollision;
    public boolean ifEaten;

    Cell snack=null; // specjalna komórka do referencji na obiekt, który będzie jabłkiem

    public Board(int x, int y) {
        this.x = x;
        this.y = y;
        cells=new ArrayList<Cell>();
        createCells(x,y);
        ifCollision=false; // czy nastąpiła kolizja
        ifEaten=false; // czy jabłko zjedzone przez węża
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void drawBoard(Snake snake) {

        snake.drawSnake(this); // modyfikuje komórki ( Cells ) i dopiero rysuję

        if(!ifEaten) {
            snack = putApple();
            ifEaten=true;
        }
        drawSpace();
        if(snake.checkCollision(this))
            ifCollision=true;
        else if(snake.getFood(snack))
        {
            snake.improve();
            ifEaten=false;
        }
    }

    private Cell putApple() {
        Random random=new Random();
        int xApple=random.nextInt(this.getX()-2)+1;
        int yApple=random.nextInt(this.getY()-2)+1;
        getCell(xApple,yApple).setBodyCell(BodyCell.SNACK);
        return getCell(xApple,yApple);
    }

    private void drawSpace() { // rysuje plansze
        for(int i=0; i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                getCell(j,i).drawCell();
            }
            System.out.println();
        }
    }

    private void createCells(int x, int y) {
        for(int i=0; i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                if(i==0 || i==x-1)
                    cells.add(new Cell(BodyCell.BOUNDARY,j,i));
                else
                {
                    if(j==0 || j==y-1)
                        cells.add(new Cell(BodyCell.BOUNDARY,j,i));
                    else
                        cells.add(new Cell(BodyCell.EMPTY,j,i));
                }
            }
        }
    }
    public Cell getCell(int x, int y) // zwróc komórkę po jej wspólrzędnych
    {
        for(Cell cell: cells)
        {
            if(cell.getX()==x && cell.getY()==y)
                return cell;
        }
        return null;
    }
}
