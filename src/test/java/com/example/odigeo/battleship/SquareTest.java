package com.example.odigeo.battleship;

import com.example.odigeo.battleship.domain.Square;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.example.odigeo.battleship.domain.Status.*;

public class SquareTest {

    @Test
    public void shouldInitateSquareWithWATERstatus(){
        Square square = new Square();
        assertSame(WATER, square.getStatus());
    }
}
