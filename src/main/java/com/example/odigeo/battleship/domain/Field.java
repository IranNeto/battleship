package com.example.odigeo.battleship.domain;

import static com.example.odigeo.battleship.domain.Status.ALREADY_HIT;
import static com.example.odigeo.battleship.domain.Status.OCCUPIED;

public class Field {

    public final int BOARD_SIZE = 10;

    private final Square[][] board = new Square[BOARD_SIZE][BOARD_SIZE];

    public Field() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = new Square();
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    public boolean hit(int x, int y) {
        Square square = board[x][y];
        if (square.getStatus() == OCCUPIED) {
            square.setStatus(ALREADY_HIT);
            return true;
        }

        return false;
    }
}
