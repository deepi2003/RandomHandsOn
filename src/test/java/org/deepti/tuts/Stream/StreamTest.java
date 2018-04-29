package org.deepti.tuts.Stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {


    @Test
    public void testStream() {
        List<String> temp = Arrays.asList("CL", "BC", "X5", "4X");

        List<String> result =  temp.parallelStream()
                .filter( s ->s.equalsIgnoreCase("CL"))
                .map(s -> s.toLowerCase())
                .collect(Collectors.toList());

        Assert.assertEquals(result.get(0), "cl");

    }

    @Test
    public void testFilterTags() {
        List<FilterTags> validFilterTags = new ArrayList<>();
        validFilterTags.add(new FilterTags("CL", 4));
        validFilterTags.add(new FilterTags("X5", 5));
        validFilterTags.add(new FilterTags("PROMO", 2));
        validFilterTags.add(new FilterTags("BCC", 3));
        validFilterTags.add(new FilterTags("4X", 1));

        List<String> prefered = Arrays.asList("CL", "BCC");
        Collections.reverse(prefered);

        //for every preferred tag change order to 0 and set that in front
        prefered.stream().forEachOrdered(p->getPreferredOrderFilterList(p, validFilterTags));

        Assert.assertEquals(0, validFilterTags.get(0).getDisplayOrder());
        Assert.assertEquals("CL", validFilterTags.get(0).getName());

        Assert.assertEquals(0, validFilterTags.get(1).getDisplayOrder());
        Assert.assertEquals("BCC", validFilterTags.get(1).getName());

        Assert.assertEquals( 5, validFilterTags.size() );
    }


    @Test
    public void testFilterTagsWithPreferredTagNotPresent() {
        List<FilterTags> validFilterTags = new ArrayList<>();
        validFilterTags.add(new FilterTags("X5", 5));
        validFilterTags.add(new FilterTags("PROMO", 2));
        validFilterTags.add(new FilterTags("BCC", 3));
        validFilterTags.add(new FilterTags("4X", 1));

        List<String> prefered = Arrays.asList("CL", "BCC");
        Collections.reverse(prefered);

        //for every preferred tag change order to 0 and set that in front
        prefered.stream().forEachOrdered(p->getPreferredOrderFilterList(p, validFilterTags));


        Assert.assertEquals(0, validFilterTags.get(0).getDisplayOrder());
        Assert.assertEquals("BCC", validFilterTags.get(0).getName());

        Assert.assertEquals( 4, validFilterTags.size() );
    }

    private FilterTags getPreferredOrderFilterList(String p, List<FilterTags> validFilterTags) {
        return validFilterTags.stream()
                .filter( tag -> tag.getName().equalsIgnoreCase(p))
                .findFirst()
                .map( ele -> setPreferredOrderAndPosition(validFilterTags, ele)).orElse(null);
    }

    private FilterTags setPreferredOrderAndPosition(List<FilterTags> validFilterTags, FilterTags tag) {
        tag.setDisplayOrder(0);
        validFilterTags.remove(tag);
        validFilterTags.add(0, tag);
        return tag;
    }
}

class FilterTags {
    public FilterTags(String name, int order) {
        this.name = name;
        displayOrder = order;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    String name;
    int displayOrder;

}
