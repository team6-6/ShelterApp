package com.example.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AddShelterTest {


    @Test
    public void NotDouble() {
        Checkfunction c=new Checkfunction();
        assertEquals("exception", c.ParseToDouble("test"));
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
    @Test
    public void EmptyString() {
        Checkfunction c=new Checkfunction();
        assertEquals(1, c.notEmpty(""));
    }

    @Test
    public void NotEmptyString() {
        Checkfunction c=new Checkfunction();
        assertEquals(0, c.notEmpty("sap"));
    }
}
