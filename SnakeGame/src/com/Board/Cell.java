package com.Board;

public class Cell {


    private BodyCell bodyCell; // enum
    private int x,y; // współrzędne komórki

    public Cell(BodyCell bodyCell, int x, int y) {
        this.bodyCell = bodyCell;
        this.x = x;
        this.y = y;
    }
    public void drawCell()
    {
        System.out.print(bodyCell.getC());
    }




    //GETTERS AND SETTERS
    public BodyCell getBodyCell() {
        return bodyCell;
    }

    public void setBodyCell(BodyCell bodyCell) {
        this.bodyCell = bodyCell;
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
}
