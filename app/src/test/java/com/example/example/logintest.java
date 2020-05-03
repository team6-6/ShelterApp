package com.example.example;

import org.junit.Test;

import org.junit.Test;

import static org.junit.Assert.*;

public class logintest {
    @Test
    public void NotEmptyString() {
        CheckFunction c=new CheckFunction();
        assertEquals(1, c.notEmpty(""));
    }
}
