package com.example.odigeo.battleship.controller;

import com.example.odigeo.battleship.domain.Field;
import com.example.odigeo.battleship.boat.Direction;
import com.example.odigeo.battleship.boat.Ship;
import com.example.odigeo.battleship.exception.InvalidDirectionException;
import com.example.odigeo.battleship.exception.InvalidPositionException;

import static com.example.odigeo.battleship.domain.Status.OCCUPIED;

public class PlayerController implements Controller {
    private final int BOARD_SIZE = 10;

    private void validateCoordinates(int line, int column) throws InvalidPositionException {
        if ((line < 0 || line >= BOARD_SIZE) || (column < 0 || column >= BOARD_SIZE))
            throw new InvalidPositionException();
    }

    private void validateShipAddition(Ship ship, int line, int column, int directionX, int directionY) throws InvalidPositionException {
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

    @Override
    public Field addShip(Field field, Ship ship, int line, int column, int directionX, int directionY) throws InvalidDirectionException, InvalidPositionException {

        validateCoordinates(line, column);
        validateDirection(directionX, directionY);
        validateShipAddition(ship, line, column, directionX, directionY);

        Direction direction = deriveDirection(directionX, directionY);

        for (int i = 0; i < ship.getSIZE(); i++) {

            field.getBoard()[line][column].setStatus(OCCUPIED);

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

        return field;
    }

    @Override
    public boolean tryAHit(Field field, int line, int column) throws InvalidPositionException {
        validateCoordinates(line, column);
        return field.hit(line, column);
    }
}
