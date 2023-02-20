package com.example.odigeo.battleship;

import org.junit.Test;
import static org.junit.Assert.*;
import static com.example.odigeo.battleship.Status.*;

public class SquareTest {

    @Test
    public void shouldInitateSquareWithWATERstatus(){
        Square square = new Square();
        assertSame(WATER, square.getStatus());
    }
}
