package org.chiefacc;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;
import org.chiefacc.core.Person;
import org.chiefacc.core.PersonPairPay;
import org.chiefacc.core.SumDistributor;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class SumDistributorTest {

    private static final Logger LOG = LoggerFactory.getLogger(SumDistributorTest.class);

    @Test
    public void testPersonsTreeNotEquals() {
        Person p1 = new Person("p1", 6);
        Person p2 = new Person("p2", 0);
        Person p3 = new Person("p3", 0);
        Person p4 = new Person("p4", 0);
        Person p5 = new Person("p5", 0);
        Person p6 = new Person("p6", 0);

        Collection<PersonPairPay> actual = new TreeSet<>();
        actual.add(new PersonPairPay(p2, p1, 1));
        actual.add(new PersonPairPay(p3, p1, 1));
        actual.add(new PersonPairPay(p4, p1, 1));
        actual.add(new PersonPairPay(p6, p1, 1));
        actual.add(new PersonPairPay(p5, p1, 1));

        NavigableSet<PersonPairPay> unexpected = new TreeSet<>();
        unexpected.add(new PersonPairPay(p2, p1, 1));
        unexpected.add(new PersonPairPay(p3, p1, 1));
        unexpected.add(new PersonPairPay(p4, p1, 1));
        unexpected.add(new PersonPairPay(p6, p1, 1));
        unexpected.add(new PersonPairPay(p5, p1, 2));

        assertNotEquals(unexpected, actual);
    }

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

        Collection<PersonPairPay> distributed = sd.distribute(personsTest);
        for (PersonPairPay pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPairPay> expected = new TreeSet<>();
        expected.add(new PersonPairPay(p2, p1, 1));
        expected.add(new PersonPairPay(p3, p1, 1));
        expected.add(new PersonPairPay(p4, p1, 1));
        expected.add(new PersonPairPay(p6, p1, 1));
        expected.add(new PersonPairPay(p5, p1, 2));

        System.out.println(expected.equals(distributed));

        assertEquals(expected, distributed);

        assertEquals(BigDecimal.valueOf(1), sd.averageFromPersons(personsTest));
    }

    @Test
    public void firstAllSumTest2() {
        Person p1 = new Person("p1", 600.6);
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

        Collection<PersonPairPay> distributed = sd.distribute(personsTest);
        for (PersonPairPay pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPairPay> expected = new TreeSet<>();
        expected.add(new PersonPairPay(p2, p1, 100.1));
        expected.add(new PersonPairPay(p3, p1, 100.1));
        expected.add(new PersonPairPay(p4, p1, 100.1));
        expected.add(new PersonPairPay(p6, p1, 100.1));
        expected.add(new PersonPairPay(p5, p1, 100.1));

        assertEquals(expected, distributed);

        assertEquals(BigDecimal.valueOf(100.1), sd.averageFromPersons(personsTest));
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

        Collection<PersonPairPay> distributed = sd.distribute(personsTest);
        for (PersonPairPay pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPairPay> expected = new TreeSet<>();
        expected.add(new PersonPairPay(p3, p1, 2));
        expected.add(new PersonPairPay(p4, p1, 2));
        expected.add(new PersonPairPay(p5, p2, 2));
        expected.add(new PersonPairPay(p6, p2, 2));

        assertEquals(expected, distributed);

        assertEquals(BigDecimal.valueOf(2), sd.averageFromPersons(personsTest));
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

        Collection<PersonPairPay> distributed = sd.distribute(personsTest);
        for (PersonPairPay pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPairPay> expected = new TreeSet<>();
        expected.add(new PersonPairPay(p1, p6, 250));
        expected.add(new PersonPairPay(p2, p5, 150));
        expected.add(new PersonPairPay(p3, p4, 50));

        assertEquals(expected, distributed);

        assertEquals(BigDecimal.valueOf(350.00), sd.averageFromPersons(personsTest));
    }

    @Test
    public void allSumTest2() {
        Person p1 = new Person("p1", 100);
        Person p2 = new Person("p2", 200);
        Person p3 = new Person("p3", 300);
        Person p4 = new Person("p4", 400);
        Person p5 = new Person("p5", 500.3);
        Person p6 = new Person("p6", 0);

        SumDistributor sd = new SumDistributor();

        Set<Person> personsTest = new HashSet<>();
        personsTest.add(p1);
        personsTest.add(p2);
        personsTest.add(p3);
        personsTest.add(p4);
        personsTest.add(p5);
        personsTest.add(p6);

        Collection<PersonPairPay> distributed = sd.distribute(personsTest);
        for (PersonPairPay pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPairPay> expected = new TreeSet<>();
        expected.add(new PersonPairPay(p6, p5, 250.05));
        expected.add(new PersonPairPay(p1, p4, 149.85));
        expected.add(new PersonPairPay(p2, p3, 49.95));
        expected.add(new PersonPairPay(p1, p5, 0.20));
        expected.add(new PersonPairPay(p2, p4, 0.10));

        assertEquals(expected, distributed);


        assertEquals(BigDecimal.valueOf(250.05), sd.averageFromPersons(personsTest));
    }

    @Test
    public void allSumTest3() {
        Person p1 = new Person("p1", 200);
        Person p2 = new Person("p2", 1000);
        Person p3 = new Person("p3", 850);
        Person p4 = new Person("p4", 750);
        Person p5 = new Person("p5", 500.3);
        Person p6 = new Person("p6", 0);

        SumDistributor sd = new SumDistributor();

        Set<Person> personsTest = new HashSet<>();
        personsTest.add(p1);
        personsTest.add(p2);
        personsTest.add(p3);
        personsTest.add(p4);
        personsTest.add(p5);
        personsTest.add(p6);

        Collection<PersonPairPay> distributed = sd.distribute(personsTest);
        for (PersonPairPay pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPairPay> expected = new TreeSet<>();
        expected.add(new PersonPairPay(p6, p2, 449.95));
        expected.add(new PersonPairPay(p1, p3, 299.95));
        expected.add(new PersonPairPay(p6, p4, 100.1));
        expected.add(new PersonPairPay(p1, p4, 50.1));
        expected.add(new PersonPairPay(p5, p4, 49.75));

        assertEquals(expected, distributed);


        assertEquals(BigDecimal.valueOf(550.05), sd.averageFromPersons(personsTest));
    }

    @Test
    public void allSumTest4() {
        Person p1 = new Person("p1", 200);
        Person p2 = new Person("p2", 1000);
        Person p3 = new Person("p3", 850);
        Person p4 = new Person("p4", 750);
        Person p5 = new Person("p5", 500.3333);
        Person p6 = new Person("p6", 0);

        SumDistributor sd = new SumDistributor();

        Set<Person> personsTest = new HashSet<>();
        personsTest.add(p1);
        personsTest.add(p2);
        personsTest.add(p3);
        personsTest.add(p4);
        personsTest.add(p5);
        personsTest.add(p6);

        Collection<PersonPairPay> distributed = sd.distribute(personsTest);
        for (PersonPairPay pp : distributed) {
            LOG.info(pp.toString());
        }

        NavigableSet<PersonPairPay> expected = new TreeSet<>();
        expected.add(new PersonPairPay(p6, p2, 449.94));
        expected.add(new PersonPairPay(p1, p3, 299.94));
        expected.add(new PersonPairPay(p6, p4, 100.12));
        expected.add(new PersonPairPay(p1, p4, 50.12));
        expected.add(new PersonPairPay(p5, p4, 49.70));

        assertEquals(expected, distributed);


        assertEquals(BigDecimal.valueOf(550.06), sd.averageFromPersons(personsTest));
    }

}
