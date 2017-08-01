package org.chiefacc.core;

import java.math.BigDecimal;
import java.util.Comparator;

public class Person {

    private final String name;
    private BigDecimal sum;


    public Person(String name, BigDecimal sum) {
        this.name = name;
        this.sum = sum;
    }

    public Person(String name, double sum) {
        this(name, BigDecimal.valueOf(sum));
    }

    public String getName() {
        return name;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @SuppressWarnings("Duplicates")
    public static Comparator<Person> minToMaxComparator() {
        return new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                int comp = p1.sum.compareTo(p2.sum);
                if (comp == 0) {
                    return p1.name.compareTo(p2.name);
                } else {
                    return comp;
                }
            }
        };
    }

    @SuppressWarnings("Duplicates")
    public static Comparator<Person> maxToMinComparator() {
        return new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                int comp = p2.sum.compareTo(p1.sum);
                if (comp == 0) {
                    return p1.name.compareTo(p2.name);
                } else {
                    return comp;
                }
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return name != null ? name.equals(person.name) : person.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                '}';
    }
}
