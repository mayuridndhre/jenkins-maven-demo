package com.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    public void testAddition() {
        int result = 2 + 3;
        assertEquals(5, result, "2 + 3 should be 5");
    }

    @Test
    public void testSubtraction() {
        int result = 10 - 5;
        assertEquals(5, result, "10 - 5 should be 5");
    }
}
//new comment added new