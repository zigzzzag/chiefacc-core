package org.chiefacc;

import java.util.NavigableSet;
import java.util.PriorityQueue;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SumDistributorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SumDistributorTest.class);

    @Test
    public void firstAllSumTest() {
        SumDistributor sd = new SumDistributor();

        PriorityQueue<Person> pesronsTest = new PriorityQueue<>();
        pesronsTest.add(new Person("Gogi", 6));
        pesronsTest.add(new Person("Max", 0));
        pesronsTest.add(new Person("Sanya B", 0));
        pesronsTest.add(new Person("Sanya F", 0));
        pesronsTest.add(new Person("Shirz", 0));
        pesronsTest.add(new Person("Serega", 0));

        NavigableSet<PersonPair> distributed = sd.distribute(pesronsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }
    }

    @Test
    public void firstTwoSumTest() {
        SumDistributor sd = new SumDistributor();

        PriorityQueue<Person> pesronsTest = new PriorityQueue<>();
        pesronsTest.add(new Person("Gogi", 6));
        pesronsTest.add(new Person("Max", 6));
        pesronsTest.add(new Person("Sanya B", 0));
        pesronsTest.add(new Person("Sanya F", 0));
        pesronsTest.add(new Person("Shirz", 0));
        pesronsTest.add(new Person("Serega", 0));

        NavigableSet<PersonPair> distributed = sd.distribute(pesronsTest);
        for (PersonPair pp : distributed) {
            LOG.info(pp.toString());
        }
    }

}
