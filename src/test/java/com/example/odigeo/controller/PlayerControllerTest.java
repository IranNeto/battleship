package com.example.odigeo.controller;

import com.example.odigeo.battleship.domain.Field;
import com.example.odigeo.battleship.boat.Battleship;
import com.example.odigeo.battleship.boat.Cruiser;
import com.example.odigeo.battleship.boat.Ship;
import com.example.odigeo.battleship.controller.PlayerController;
import com.example.odigeo.battleship.exception.InvalidDirectionException;
import com.example.odigeo.battleship.exception.InvalidPositionException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static com.example.odigeo.battleship.domain.Status.OCCUPIED;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerControllerTest {

    @Test
    public void shouldAddAShip() throws InvalidDirectionException, InvalidPositionException {
        PlayerController playerController = new PlayerController();
        Ship battleship = new Battleship();
        Field field = new Field();
        playerController.addShip(field, battleship, 0, 0, 0, 1);
        assertEquals(OCCUPIED, field.getBoard()[0][0].getStatus());
    }

    @Test
    public void shouldAddShipInField() throws InvalidDirectionException, InvalidPositionException {
        PlayerController playerController = new PlayerController();
        Field field = new Field();
        Ship cruiser = new Cruiser();

        int line = 0;
        int column = 0;
        int directionX = 1;
        int directionY = 0;

        playerController.addShip(field, cruiser, line, column, directionX, directionY);
        Assert.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assert.assertEquals(OCCUPIED, field.getBoard()[line][column + 1].getStatus());
        Assert.assertEquals(OCCUPIED, field.getBoard()[line][column + 2].getStatus());
    }

    @Test
    public void shouldInvalidateShipAdditionWhenDirXANDDirYAreZero() {
        PlayerController playerController = new PlayerController();
        Ship cruiser = new Cruiser();
        Field field = new Field();

        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 0;
        Assertions.assertThrows(Exception.class, () -> playerController.addShip(field, cruiser, line, column, directionX, directionY));
    }

    @Test
    public void shouldInvalidateShipAdditionWhenDirXIsPositiveOrNegativeAndDirYIsTheOpposite() {
        PlayerController playerController = new PlayerController();
        Ship cruiser = new Cruiser();
        Field field = new Field();
        int line = 0;
        int column = 0;
        int directionX = 1;
        int directionY = -1;
        Assertions.assertThrows(InvalidDirectionException.class, () -> playerController.addShip(field, cruiser, line, column, directionX, directionY));
        Assertions.assertThrows(InvalidDirectionException.class, () -> playerController.addShip(field, cruiser, line, column, directionY, directionX));
    }

    @Test
    public void shouldInvalidateShipAdditionWhenDirXOrDirYIsLessThanMinusOneAndBiggerThanOne() {
        PlayerController playerController = new PlayerController();
        Ship cruiser = new Cruiser();
        Field field = new Field();
        int line = 0;
        int column = 0;

        Assertions.assertThrows(InvalidDirectionException.class, () -> playerController.addShip(field, cruiser, line, column, -2, 0));
        Assertions.assertThrows(InvalidDirectionException.class, () -> playerController.addShip(field, cruiser, line, column, 2, 0));
        Assertions.assertThrows(InvalidDirectionException.class, () -> playerController.addShip(field, cruiser, line, column, 0, -2));
        Assertions.assertThrows(InvalidDirectionException.class, () -> playerController.addShip(field, cruiser, line, column, 0, 2));
    }

    @Test
    public void shouldAddShipOrientedToTheRight() throws Exception {
        PlayerController playerController = new PlayerController();
        Field field = new Field();
        Ship cruiser = new Cruiser();

        int line = 0;
        int column = 0;
        int directionX = 1;
        int directionY = 0;
        playerController.addShip(field, cruiser, line, column, directionX, directionY);

        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column + 1].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column + 2].getStatus());
    }

    @Test
    public void shouldAddShipOrientedUp() throws Exception {
        PlayerController playerController = new PlayerController();
        Field field = new Field();
        Ship cruiser = new Cruiser();

        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 1;
        playerController.addShip(field, cruiser, line, column, directionX, directionY);
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line + 1][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line + 2][column].getStatus());
    }

    @Test
    public void shouldAddShipOrientedDown() throws Exception {
        PlayerController playerController = new PlayerController();
        Field field = new Field();
        Ship cruiser = new Cruiser();

        int line = 9;
        int column = 9;
        int directionX = 0;
        int directionY = -1;
        playerController.addShip(field, cruiser, line, column, directionX, directionY);
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line - 1][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line - 2][column].getStatus());
    }

    @Test
    public void shouldAddShipOrientedToTheLeft() throws Exception {
        PlayerController playerController = new PlayerController();
        Field field = new Field();
        Ship cruiser = new Cruiser();

        int line = 9;
        int column = 9;
        int directionX = -1;
        int directionY = 0;
        playerController.addShip(field, cruiser, line, column, directionX, directionY);
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column - 1].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column - 2].getStatus());
    }

    @Test
    public void shouldInvalidateShipAdditionDueToInitialPositionOutOfField() {
        PlayerController playerController = new PlayerController();
        Ship cruiser = new Cruiser();
        Field field = new Field();

        int line = 10;
        int column = 10;
        int directionX = -1;
        int directionY = 0;
        Assertions.assertThrows(InvalidPositionException.class, () -> playerController.addShip(field, cruiser, line, column, directionX, directionY));
    }

    @Test
    public void shouldInvalidateShipAdditionDueToInvalidPosition() {
        PlayerController playerController = new PlayerController();
        Ship cruiser = new Cruiser();
        Field field = new Field();
        int line = 9;
        int column = 9;
        int directionX = 0;
        int directionY = 1;
        Assertions.assertThrows(InvalidPositionException.class, () -> playerController.addShip(field, cruiser, line, column, directionX, directionY));
    }

    @Test
    public void shouldTryAHitAndMiss() throws InvalidDirectionException, InvalidPositionException {
        PlayerController playerController = new PlayerController();
        Ship cruiser = new Cruiser();
        Field field = new Field();
        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 1;
        int x = 9;
        int y = 9;
        playerController.addShip(field, cruiser, line, column, directionX, directionY);
        assertFalse(playerController.tryAHit(field, x, y));
    }

    @Test
    public void shouldTryAHitAndHit() throws InvalidDirectionException, InvalidPositionException {
        PlayerController playerController = new PlayerController();
        Ship cruiser = new Cruiser();
        Field field = new Field();
        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 1;
        int x = 2;
        int y = 0;
        playerController.addShip(field, cruiser, line, column, directionX, directionY);
        assertTrue(playerController.tryAHit(field, x, y));
    }
}
