package org.chiefacc;

import java.util.PriorityQueue;
import org.chiefacc.core.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class PersonOrderTest {

    @Test
    public void personOrderTestMaxToMin() {

        Person p1 = new Person("p1", 6);
        Person p2 = new Person("p2", 6);
        Person p3 = new Person("p3", 0);
        Person p4 = new Person("p4", 0);
        Person p5 = new Person("p5", 0);
        Person p6 = new Person("p6", 0);

        PriorityQueue<Person> personsMaxToMin = new PriorityQueue<>(6, Person.maxToMinComparator());
        personsMaxToMin.add(p1);
        personsMaxToMin.add(p2);
        personsMaxToMin.add(p3);
        personsMaxToMin.add(p4);
        personsMaxToMin.add(p5);
        personsMaxToMin.add(p6);

        assertEquals(p1, personsMaxToMin.poll());
        assertEquals(p2, personsMaxToMin.poll());
        assertEquals(p3, personsMaxToMin.poll());
        assertEquals(p4, personsMaxToMin.poll());
        assertEquals(p5, personsMaxToMin.poll());
        assertEquals(p6, personsMaxToMin.poll());
    }

    @Test
    public void personOrderTestMinToMax() {

        Person p1 = new Person("p1", 6);
        Person p2 = new Person("p2", 6);
        Person p3 = new Person("p3", 0);
        Person p4 = new Person("p4", 0);
        Person p5 = new Person("p5", 0);
        Person p6 = new Person("p6", 0);

        PriorityQueue<Person> personsMaxToMin = new PriorityQueue<>(6, Person.minToMaxComparator());
        personsMaxToMin.add(p1);
        personsMaxToMin.add(p2);
        personsMaxToMin.add(p3);
        personsMaxToMin.add(p4);
        personsMaxToMin.add(p5);
        personsMaxToMin.add(p6);

        assertEquals(p3, personsMaxToMin.poll());
        assertEquals(p4, personsMaxToMin.poll());
        assertEquals(p5, personsMaxToMin.poll());
        assertEquals(p6, personsMaxToMin.poll());
        assertEquals(p1, personsMaxToMin.poll());
        assertEquals(p2, personsMaxToMin.poll());
    }
}
