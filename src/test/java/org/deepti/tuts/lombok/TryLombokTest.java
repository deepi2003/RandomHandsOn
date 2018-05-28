package org.deepti.tuts.lombok;

import org.junit.Assert;
import org.junit.Test;



public class TryLombokTest {

    @Test
    public void testFirstNameGetter() {
        TryLombok test = new TryLombok();
        test.setFirstName("test");
        Assert.assertEquals("test", test.getFirstName());
    }

    @Test
    public void testFullName() {
        UseLombok useLombok = new UseLombok();
        Assert.assertEquals("Test Passed", useLombok.getUserName());
    }

}