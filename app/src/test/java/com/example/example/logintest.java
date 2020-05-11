package com.example.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class logintest {
    @Test
    public void NotEmptyString() {
        Checkfunction c=new Checkfunction();
        assertEquals(0, c.notEmpty(""));
    }
}
