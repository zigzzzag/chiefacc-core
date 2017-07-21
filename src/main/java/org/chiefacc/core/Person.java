package org.chiefacc.core;

import java.util.Comparator;

public class Person {

    private final String name;
    private double sum;

    public Person(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    //todo encapsulate
    public static Comparator<Person> minToMaxComparator() {
        return new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                if (p1.sum > p2.sum) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }

    //todo encapsulate
    public static Comparator<Person> maxToMinComparator() {
        return new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                if (p1.sum < p2.sum) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }
}
