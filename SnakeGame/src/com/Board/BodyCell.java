package com.Board;

public enum BodyCell {
    BOUNDARY('#'), BODYSNAKE('o'),EMPTY(' '),SNACK('*')
    ;
    private char c;
    BodyCell(char c) {
        this.c=c;
    }

    public char getC() {
        return c;
    }


}
