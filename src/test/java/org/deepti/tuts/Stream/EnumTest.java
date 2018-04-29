package org.deepti.tuts.Stream;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum SORT_TYPE{

    RELEVANCE("relevance"),
    EXPIRATION("expiration");

    final String name;

    private SORT_TYPE(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }

}

public class EnumTest {
    @Test
    public void testEnum() {
        Assert.assertEquals("expiration" ,SORT_TYPE.EXPIRATION.getValue());

    }
}
