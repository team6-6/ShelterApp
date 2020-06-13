package com.example.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DeleteSirenTest {
    @Test
    public void EmptyString() {
        Checkfunction c=new Checkfunction();
        assertEquals(1, c.notEmpty(""));
    }

    @Test
    public void NotEmptyString() {
        Checkfunction c=new Checkfunction();
        assertEquals(0, c.notEmpty("deletesiren"));
    }
}
