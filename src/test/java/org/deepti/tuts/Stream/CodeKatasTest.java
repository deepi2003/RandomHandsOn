package org.deepti.tuts.Stream;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.deepti.tuts.Stream.StreamHandsON.*;

public class CodeKatasTest {
    @Test
    public void transformShouldFilterCollection() {
        List<String> collection = asList("My", "name", "is", "John", "Doe");
        List<String> expected = asList("My", "is", "Doe");
        assertThat(getElementSizeIsLessThan4(collection)).hasSameElementsAs(expected);
    }

    @Test
    public void transformShouldFlattenCollection() {
        List<List<String>> collection = asList(asList("Viktor", "Farcic"), asList("John", "Doe", "Third"));
        List<String> expected = asList("Viktor", "Farcic", "John", "Doe", "Third");
        assertThat(flattenMap(collection)).hasSameElementsAs(expected);
    }


    @Test
    public void getOldestPersonShouldReturnOldestPerson() {
        StreamHandsON.Person sara = new StreamHandsON.Person("Sara", 4);
        StreamHandsON.Person viktor = new StreamHandsON.Person("Viktor", 40);
        StreamHandsON.Person eva = new StreamHandsON.Person("Eva", 42);
        List<StreamHandsON.Person> collection = asList(sara, eva, viktor);
        assertThat(StreamHandsON.getOldestPerson(collection)).isEqualToComparingFieldByField(eva);
    }


    @Test
    public void transformShouldConvertCollectionElementsToUpperCase() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        assertThat(StreamHandsON.calculate(numbers)).isEqualTo(1 + 2 + 3 + 4 + 5);
    }


    @Test
    public void getKidNameShouldReturnNamesOfAllKidsWhereAgeUnder18() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        Person anna = new Person("Anna", 5);
        List<Person> collection = asList(sara, eva, viktor, anna);
        assertThat(StreamHandsON.getKidNames(collection))
                .contains("Sara", "Anna")
                .doesNotContain("Viktor", "Eva");
    }

    @Test
    public void partitionAdultsShouldSeparateKidsFromAdults() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);
        Map<Boolean, List<Person>> result = StreamHandsON.partitionAdults(collection);
        assertThat(result.get(true)).hasSameElementsAs(asList(viktor, eva));
        assertThat(result.get(false)).hasSameElementsAs(asList(sara));
    }


    @Test
    public void partitionAdultsBasedOnNationality() {
        Person sara = new Person("Sara", 4, "Norwegian");
        Person viktor = new Person("Viktor", 40, "Serbian");
        Person eva = new Person("Eva", 42, "Norwegian");
        List<Person> collection = asList(sara, eva, viktor);
        Map<String, List<Person>> result = StreamHandsON.groupByNationality(collection);
        assertThat(result.get("Norwegian")).hasSameElementsAs(asList(sara, eva));
        assertThat(result.get("Serbian")).hasSameElementsAs(asList(viktor));
    }


    @Test
    public void toStringShouldReturnPeopleNamesSeparatedByComma() {
        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, viktor, eva);
        assertThat(StreamHandsON.namesToString(collection))
                .isEqualTo("Names: Sara, Viktor, Eva.");
    }

    class PeopleStatsSpec {

        Person sara = new Person("Sara", 4);
        Person viktor = new Person("Viktor", 40);
        Person eva = new Person("Eva", 42);
        List<Person> collection = asList(sara, eva, viktor);

        @Test
        public void getStatsShouldReturnAverageAge() {
            assertThat(StreamHandsON.getStats(collection).getAverage())
                    .isEqualTo((double)(4 + 40 + 42) / 3);
        }

        @Test
        public void getStatsShouldReturnNumberOfPeople() {
            assertThat(getStats(collection).getCount())
                    .isEqualTo(3);
        }

        @Test
        public void getStatsShouldReturnMaximumAge() {
            assertThat(getStats(collection).getMax())
                    .isEqualTo(42);
        }

        @Test
        public void getStatsShouldReturnMinimumAge() {
            assertThat(getStats(collection).getMin())
                    .isEqualTo(4);
        }

        @Test
        public void getStatsShouldReturnSumOfAllAges() {
            assertThat(getStats(collection).getSum())
                    .isEqualTo(40 + 42 + 4);
        }

    }


}
