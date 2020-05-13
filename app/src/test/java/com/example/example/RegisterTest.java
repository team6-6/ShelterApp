package com.example.example;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void NotInRange() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.RangeValue(5,"eden"));
    }

    @Test
    public void InRange() {
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.RangeValue(5,"example"));
    }

    @Test
    public void CheckThreeCharacter() {
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.RangeValue(3,"ede"));
    }

    @Test
    public void EmptyInRange() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.RangeValue(3,""));
    }

    @Test
    public void InRangeBetweenTwoNumber() {
        Checkfunction c=new Checkfunction();
        assertNotEquals(true, c.RangeValues(5,8,"edenda"));
    }

    @Test
    public void NotInRangeBetweenTwoNumber() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.RangeValues(5,8,"eden"));
    }

    @Test
    public void EmptyInRangeBetweenTwoNumber() {
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.RangeValues(5,8,""));
    }

    @Test
    public void ContainsUpperCasePassword() {
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.CheckPassword(UpperCasePatten,"Aa123456"));
    }

    @Test
    public void ContainsLowerCasePassword() {
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.CheckPassword(lowerCasePatten,"Aa123456"));
    }

    @Test
    public void ContainsNumbersPassword() {

        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        Checkfunction c=new Checkfunction();
        assertEquals(true, c.CheckPassword(digitCasePatten,"Aa123456"));
    }

    @Test
    public void NotContainsUpperCasePassword() {
        Pattern UpperCasePatten = Pattern.compile("[A-Z ]");
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.CheckPassword(UpperCasePatten,"aa123456"));
    }

    @Test
    public void NotContainsLowerCasePassword() {
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.CheckPassword(lowerCasePatten,"AA123456"));
    }

    @Test
    public void NotContainsNumbersPassword() {
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");
        Checkfunction c=new Checkfunction();
        assertEquals(false, c.CheckPassword(digitCasePatten,"AAAAAAAA"));
    }
}
