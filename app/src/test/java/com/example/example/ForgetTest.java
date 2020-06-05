package com.example.example;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ForgetTest {

    @Test
    public void inValidNameTest() {
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.validName("9Meir"));
    }

    @Test
    public void ValidNameTest() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.validName("Meir"));
    }

}
