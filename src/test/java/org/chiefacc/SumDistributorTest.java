package org.chiefacc;

import org.junit.Test;

import java.util.NavigableSet;
import java.util.TreeSet;

public class SumDistributorTest {

    //todo remake
    @Test
    public void firstAllSumTest() {
        SumDistributor sd = new SumDistributor();

        TreeSet<Person> pesronsTest = new TreeSet<>();
        pesronsTest.add(new Person("Gogi", 6));
        pesronsTest.add(new Person("Max", 0));
        pesronsTest.add(new Person("Sanya B", 0));
        pesronsTest.add(new Person("Sanya F", 0));
        pesronsTest.add(new Person("Shirz", 0));
        pesronsTest.add(new Person("Serega", 0));

        NavigableSet<PersonPair> distributed = sd.distribute(pesronsTest);
        System.out.println(distributed);
    }
}
