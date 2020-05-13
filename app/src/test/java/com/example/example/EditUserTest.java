package com.example.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EditUserTest {

    @Test
    public void BadPermission() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.CheckPermission("kk"));
    }

    @Test
    public void GoodPermission() {
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.CheckPermission("A"));
    }

    @Test
    public void EmptyPermission() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.CheckPermission(""));
    }

    @Test
    public void NumPermission() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.CheckPermission("12"));
    }
}
