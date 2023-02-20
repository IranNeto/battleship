package com.example.odigeo.battleship;

import com.example.odigeo.battleship.boat.Direction;
import com.example.odigeo.battleship.boat.Ship;
import com.example.odigeo.battleship.exception.InvalidDirectionException;
import com.example.odigeo.battleship.exception.InvalidPositionException;

import static com.example.odigeo.battleship.Status.ALREADY_HIT;
import static com.example.odigeo.battleship.Status.OCCUPIED;

public class Field {

    public final int BOARD_SIZE = 10;

    private final Square[][] board = new Square[BOARD_SIZE][BOARD_SIZE];

    Field() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = new Square();
            }
        }
    }

    public Square[][] getBoard() {
        return board;
    }

    private void validateShipAddition(Ship ship, int line, int column, int directionX, int directionY) throws InvalidPositionException {
        if ((line < 0 || line >= BOARD_SIZE) || (column < 0 || column >= BOARD_SIZE))
            throw new InvalidPositionException();

        if ((line + ship.getSIZE() * directionY < 0 || line + ship.getSIZE() * directionY >= BOARD_SIZE)
                || (column + ship.getSIZE() * directionX < 0 || column + ship.getSIZE() * directionX >= BOARD_SIZE))
            throw new InvalidPositionException();
    }

    private void validateDirection(int directionX, int directionY) throws InvalidDirectionException {
        if ((directionX == directionY) || (directionX == -directionY))
            throw new InvalidDirectionException();
        if ((directionX < -1 || directionX > 1) || (directionY < -1 || directionY > 1))
            throw new InvalidDirectionException();

    }

    private Direction deriveDirection(int directionX, int directionY) {
        if (directionX == 1 && directionY == 0) return Direction.RIGHT;
        else if (directionX == 0 && directionY == -1) return Direction.DOWN;
        else if (directionX == -1 && directionY == 0) return Direction.LEFT;
        else return Direction.UP;
    }

    public void addShip(Ship ship, int line, int column, int directionX, int directionY) throws InvalidDirectionException, InvalidPositionException {
        validateDirection(directionX, directionY);
        validateShipAddition(ship, line, column, directionX, directionY);
        Direction direction = deriveDirection(directionX, directionY);

        for (int i = 0; i < ship.getSIZE(); i++) {

            board[line][column].setStatus(OCCUPIED);

            switch (direction) {
                case RIGHT:
                    column += 1;
                    break;
                case DOWN:
                    line -= 1;
                    break;
                case LEFT:
                    column -= 1;
                    break;
                case UP:
                    line += 1;
                    break;
                default:
                    break;
            }
        }
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
