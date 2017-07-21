package org.chiefacc;

import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import org.chiefacc.core.Person;
import org.chiefacc.core.PersonPair;
import org.chiefacc.core.SumDistributor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class SumDistributorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SumDistributorTest.class);

    @Test
    public void firstAllSumTest() {
        Person p1 = new Person("p1", 6);
        Person p2 = new Person("p2", 0);
        Person p3 = new Person("p3", 0);
        Person p4 = new Person("p4", 0);
        Person p5 = new Person("p5", 0);
        Person p6 = new Person("p6", 0);


        SumDistributor sd = new SumDistributor();

        Set<Person> personsTest = new HashSet<>();
        personsTest.add(p1);
        personsTest.add(p2);
        personsTest.add(p3);
        personsTest.add(p4);
        personsTest.add(p5);
        personsTest.add(p6);

        Collection<PersonPair> distributed = sd.distribute(personsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPair> expected = new TreeSet<>();
        expected.add(new PersonPair(p2, p1, 1));
        expected.add(new PersonPair(p3, p1, 1));
        expected.add(new PersonPair(p4, p1, 1));
        expected.add(new PersonPair(p6, p1, 1));
        expected.add(new PersonPair(p5, p1, 1));


        assertEquals(expected, distributed);
    }

    @Test
    public void firstTwoSumTest() {
        Person p1 = new Person("p1", 6);
        Person p2 = new Person("p2", 6);
        Person p3 = new Person("p3", 0);
        Person p4 = new Person("p4", 0);
        Person p5 = new Person("p5", 0);
        Person p6 = new Person("p6", 0);

        SumDistributor sd = new SumDistributor();

        Set<Person> personsTest = new HashSet<>();
        personsTest.add(p1);
        personsTest.add(p2);
        personsTest.add(p3);
        personsTest.add(p4);
        personsTest.add(p5);
        personsTest.add(p6);

        Collection<PersonPair> distributed = sd.distribute(personsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPair> expected = new TreeSet<>();
        expected.add(new PersonPair(p3, p1, 2));
        expected.add(new PersonPair(p4, p1, 2));
        expected.add(new PersonPair(p5, p2, 2));
        expected.add(new PersonPair(p6, p2, 2));

        assertEquals(expected, distributed);
    }

    @Test
    public void allSumTest1() {
        Person p1 = new Person("p1", 100);
        Person p2 = new Person("p2", 200);
        Person p3 = new Person("p3", 300);
        Person p4 = new Person("p4", 400);
        Person p5 = new Person("p5", 500);
        Person p6 = new Person("p6", 600);

        SumDistributor sd = new SumDistributor();

        Set<Person> personsTest = new HashSet<>();
        personsTest.add(p1);
        personsTest.add(p2);
        personsTest.add(p3);
        personsTest.add(p4);
        personsTest.add(p5);
        personsTest.add(p6);

        Collection<PersonPair> distributed = sd.distribute(personsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPair> expected = new TreeSet<>();
        expected.add(new PersonPair(p1, p6, 250));
        expected.add(new PersonPair(p2, p5, 150));
        expected.add(new PersonPair(p3, p4, 50));

        assertEquals(expected, distributed);
    }

}
