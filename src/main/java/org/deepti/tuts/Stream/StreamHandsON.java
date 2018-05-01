package org.deepti.tuts.Stream;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamHandsON {


    public static Map<Boolean,List<Person>> partitionAdults(List<Person> collection) {
        return collection.parallelStream()
                .collect(Collectors.groupingByConcurrent(person ->person.getAge() >= 18));
    }

    public static Map<String,List<Person>> groupByNationality(List<Person> collection) {
        return collection.stream()
                .collect(Collectors.groupingByConcurrent(Person::getNationality));
    }

    public static String namesToString(List<Person> collection) {
        return collection.stream()
                .map(Person::getName)
                .collect(joining(", ", "Names: ", "."));
    }

    protected static class Person {

        private String nationality;
        private String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Person(String name, int age, String nationality) {
            this(name, age);
            this.nationality = nationality;
        }

        public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public int getAge() {
           return age;
       }

       public void setAge(int age) {
           this.age = age;
       }

        public String getNationality() {
            return nationality;
        }

        public void setNationality(String nationality) {
            this.nationality = nationality;
        }


        public static Comparator<Person> compareAge = (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge());
   }


    public static List<String> getElementSizeIsLessThan4(List<String> collection) {
            return collection.stream()
                    .filter(s -> s.length() <4)
                    .collect(toList());

    }


    //flat map can be used if you want to apply an operation to all the elements of 2 dimensional arrays
    public static List<String> flattenMap(List<List<String>> collection) {
        return collection.stream()
                .flatMap( x -> x.stream())
                .collect(toList());
    }

    public static Person getOldestPerson(List<Person> collection) {

       return collection.stream()
               .max(Comparator.comparing(Person::getAge))
               .get();

    }

    public static Integer calculate(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);  //Stream.of("java", "c", "c#", "python").reduce("Languages:", (x,y)->x+" | "+y);
    }

    public static List<String> getKidNames(List<Person> collection) {
        return collection.stream()
                .filter(kid -> kid.getAge() < 18)
                .map(Person::getName)
                .collect(toList());
    }

    public static IntSummaryStatistics getStats(List<Person> collection) {
        return collection.stream()
                .mapToInt(Person::getAge)  // Map to Int it used when we are sure that the return is going to be integer value
                .summaryStatistics();
    }

}
