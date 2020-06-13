package com.example.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChangeTest {

    @Test
    public void SamePasswordTest() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.SamePassword("Aa123456","Aa123456"));
    }

    @Test
    public void NotSamePasswordTest() {
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.SamePassword("Ba123456","Aa123456"));
    }


}
