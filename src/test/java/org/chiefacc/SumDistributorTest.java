package org.chiefacc;

import java.util.NavigableSet;
import java.util.PriorityQueue;
import org.chiefacc.core.Person;
import org.chiefacc.core.PersonPair;
import org.chiefacc.core.SumDistributor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SumDistributorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SumDistributorTest.class);

    @Test
    public void firstAllSumTest() {
        SumDistributor sd = new SumDistributor();

        PriorityQueue<Person> personsTest = new PriorityQueue<>(6, Person.maxToMinComparator());
        personsTest.add(new Person("Gogi", 6));
        personsTest.add(new Person("Max", 0));
        personsTest.add(new Person("Sanya B", 0));
        personsTest.add(new Person("Sanya F", 0));
        personsTest.add(new Person("Shirz", 0));
        personsTest.add(new Person("Serega", 0));

        NavigableSet<PersonPair> distributed = sd.distribute(personsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }
    }

    @Test
    public void firstTwoSumTest() {
        SumDistributor sd = new SumDistributor();

        PriorityQueue<Person> personsTest = new PriorityQueue<>(6, Person.maxToMinComparator());
        personsTest.add(new Person("Gogi", 6));
        personsTest.add(new Person("Max", 6));
        personsTest.add(new Person("Sanya B", 0));
        personsTest.add(new Person("Sanya F", 0));
        personsTest.add(new Person("Shirz", 0));
        personsTest.add(new Person("Serega", 0));

        NavigableSet<PersonPair> distributed = sd.distribute(personsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }
    }

    @Test
    public void allSumTest1() {
        SumDistributor sd = new SumDistributor();

        PriorityQueue<Person> personsTest = new PriorityQueue<>(6, Person.maxToMinComparator());
        personsTest.add(new Person("p1", 200));
        personsTest.add(new Person("p2", 123));
        personsTest.add(new Person("p3", 124));
        personsTest.add(new Person("p4", 125));

        NavigableSet<PersonPair> distributed = sd.distribute(personsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }
    }

}
