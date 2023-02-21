package com.example.odigeo.battleship.controller;

import com.example.odigeo.battleship.domain.Field;
import com.example.odigeo.battleship.boat.Ship;
import com.example.odigeo.battleship.exception.InvalidDirectionException;
import com.example.odigeo.battleship.exception.InvalidPositionException;

public interface Controller {
    Field addShip(Field field, Ship ship, int line, int column, int directionX, int directionY) throws InvalidDirectionException, InvalidPositionException;
    boolean tryAHit(Field field, int line, int column) throws InvalidPositionException;
}
