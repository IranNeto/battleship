package com.example.odigeo.battleship;

import com.example.odigeo.battleship.boat.Cruiser;
import com.example.odigeo.battleship.boat.Ship;
import com.example.odigeo.battleship.exception.InvalidDirectionException;
import com.example.odigeo.battleship.exception.InvalidPositionException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static com.example.odigeo.battleship.Status.OCCUPIED;
import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void shouldInitiateBoardWhenFieldIsCreated() {
        Field field = new Field();
        assertNotNull(field.getBoard());
        assertEquals(10, field.getBoard().length);
        assertEquals(10, field.getBoard()[0].length);
    }

    @Test
    public void shouldAddShipInField() throws Exception {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        int directionX = 1;
        int directionY = 0;
        field.addShip(cruiser, line, column, directionX, directionY);
        assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        assertEquals(OCCUPIED, field.getBoard()[line][column + 1].getStatus());
        assertEquals(OCCUPIED, field.getBoard()[line][column + 2].getStatus());
    }

    @Test
    public void shouldInvalidateShipAdditionWhenDirXANDDirYAreZero() {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 0;
        Assertions.assertThrows(Exception.class, () -> field.addShip(cruiser, line, column, directionX, directionY));
    }

    @Test
    public void shouldInvalidateShipAdditionWhenDirXIsPositiveOrNegativeAndDirYIsTheOpposite() {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        int directionX = 1;
        int directionY = -1;
        Assertions.assertThrows(InvalidDirectionException.class, () -> field.addShip(cruiser, line, column, directionX, directionY));
        Assertions.assertThrows(InvalidDirectionException.class, () -> field.addShip(cruiser, line, column, directionY, directionX));
    }

    @Test
    public void shouldInvalidateShipAdditionWhenDirXOrDirYIsLessThanMinusOneAndBiggerThanOne() {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        Assertions.assertThrows(InvalidDirectionException.class, () -> field.addShip(cruiser, line, column, -2, 0));
        Assertions.assertThrows(InvalidDirectionException.class, () -> field.addShip(cruiser, line, column, 2, 0));
        Assertions.assertThrows(InvalidDirectionException.class, () -> field.addShip(cruiser, line, column, 0, -2));
        Assertions.assertThrows(InvalidDirectionException.class, () -> field.addShip(cruiser, line, column, 0, 2));
    }

    @Test
    public void shouldAddShipOrientedToTheRight() throws Exception {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        int directionX = 1;
        int directionY = 0;
        field.addShip(cruiser, line, column, directionX, directionY);
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column + 1].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column + 2].getStatus());
    }

    @Test
    public void shouldAddShipOrientedUp() throws Exception {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 1;
        field.addShip(cruiser, line, column, directionX, directionY);
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line + 1][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line + 2][column].getStatus());
    }

    @Test
    public void shouldAddShipOrientedDown() throws Exception {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 9;
        int column = 9;
        int directionX = 0;
        int directionY = -1;
        field.addShip(cruiser, line, column, directionX, directionY);
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line - 1][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line - 2][column].getStatus());
    }

    @Test
    public void shouldAddShipOrientedToTheLeft() throws Exception {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 9;
        int column = 9;
        int directionX = -1;
        int directionY = 0;
        field.addShip(cruiser, line, column, directionX, directionY);
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column - 1].getStatus());
        Assertions.assertEquals(OCCUPIED, field.getBoard()[line][column - 2].getStatus());
    }

    @Test
    public void shouldInvalidateShipAdditionDueToInitialPositionOutOfField() {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 10;
        int column = 10;
        int directionX = -1;
        int directionY = 0;
        Assertions.assertThrows(InvalidPositionException.class, () -> field.addShip(cruiser, line, column, directionX, directionY));
    }

    @Test
    public void shouldInvalidateShipAdditionDueToInvalidPosition() {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 9;
        int column = 9;
        int directionX = 0;
        int directionY = 1;
        Assertions.assertThrows(InvalidPositionException.class, () -> field.addShip(cruiser, line, column, directionX, directionY));
    }

    @Test
    public void shouldTryAHitAndMiss() throws InvalidDirectionException, InvalidPositionException {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 1;
        int x = 9;
        int y = 9;
        field.addShip(cruiser, line, column, directionX, directionY);
        assertFalse(field.hit(x, y));
    }

    @Test
    public void shouldTryAHitAndHit() throws InvalidDirectionException, InvalidPositionException {
        Field field = new Field();
        Ship cruiser = new Cruiser();
        int line = 0;
        int column = 0;
        int directionX = 0;
        int directionY = 1;
        int x = 2;
        int y = 0;
        field.addShip(cruiser, line, column, directionX, directionY);
        assertTrue(field.hit(x, y));
    }
}
