package com.example.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddEmergencyTest {

    @Test
    public void NotDouble() {
        Checkfunction c=new Checkfunction();
        assertEquals("exception", c.ParseToDouble("edenda2"));
    }

    @Test
    public void DoubleNumeric() {
        Checkfunction c=new Checkfunction();
        assertEquals("true", c.ParseToDouble("90"));
    }

    @Test
    public void NullNumeric() {
        Checkfunction c=new Checkfunction();
        assertEquals("null", c.ParseToDouble(null));
    }

    @Test
    public void EmptyNumeric() {
        Checkfunction c=new Checkfunction();
        assertEquals("exception", c.ParseToDouble(""));
    }

}
