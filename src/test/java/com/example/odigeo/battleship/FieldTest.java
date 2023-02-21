package com.example.odigeo.battleship;

import com.example.odigeo.battleship.domain.Field;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {

    @Test
    public void shouldInitiateBoardWhenFieldIsCreated() {
        Field field = new Field();
        assertNotNull(field.getBoard());
        assertEquals(10, field.getBoard().length);
        assertEquals(10, field.getBoard()[0].length);
    }
}
