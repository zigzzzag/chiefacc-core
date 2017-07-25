package org.chiefacc.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class SumDistributor {

    private static final BigDecimal EPSILON = new BigDecimal(0.001);

    public SumDistributor() {
    }

    public Collection<PersonPair> distribute(Set<Person> persons) {
        return distribute(persons, averageFromPersons(persons));
    }

    public Collection<PersonPair> distribute(Set<Person> persons, BigDecimal averageFromPersons) {
        final PriorityQueue<Person> personsMaxToMin = new PriorityQueue<>(persons.size(), Person.maxToMinComparator());

        // create copy persons
        for (Person p : persons) {
            personsMaxToMin.add(new Person(p.getName(), p.getSum()));
        }

        return distribute(personsMaxToMin, new TreeSet<PersonPair>(), averageFromPersons);
    }

    private NavigableSet<PersonPair> distribute(PriorityQueue<Person> personsMaxToMin, NavigableSet<PersonPair> distrRes,
                                                final BigDecimal averageSum) {

        if (personsMaxToMin == null || personsMaxToMin.isEmpty()
                || sumEqual(personsMaxToMin.peek().getSum(), averageSum)) {
            return new TreeSet<>();
        }

        BigDecimal sumToDistr = personsMaxToMin.peek().getSum().subtract(averageSum);

        // распределяем разницу первого по всем начиная с последнего
        final Person firstPerson = personsMaxToMin.poll();


        final PriorityQueue<Person> personsMinToMax = new PriorityQueue<>(personsMaxToMin.size(), Person.minToMaxComparator());
        personsMinToMax.addAll(personsMaxToMin);
        while (!personsMinToMax.isEmpty()) {
            Person p = personsMinToMax.poll();
            if (sumToDistr.compareTo(averageSum.subtract(p.getSum())) <= 0) {
                p.setSum(p.getSum().add(sumToDistr));
                distrRes.add(new PersonPair(p, firstPerson, sumToDistr));
                break;
            } else {
                BigDecimal diff = averageSum.subtract(p.getSum());
                if (diff.abs().compareTo(EPSILON) > 0) {
                    p.setSum(p.getSum().add(diff));
                    distrRes.add(new PersonPair(p, firstPerson, diff));
                    sumToDistr = sumToDistr.subtract(diff);
                }
            }
        }

        distribute(personsMaxToMin, distrRes, averageSum);

        return distrRes;
    }

    private boolean sumEqual(BigDecimal sum1, BigDecimal sum2) {
        return sum1.subtract(sum2).abs().compareTo(EPSILON) <= 0;
    }

    public BigDecimal averageFromPersons(Collection<Person> persons) {
        BigDecimal allSum = new BigDecimal(0);
        for (Person p : persons) {
            allSum = allSum.add(p.getSum());
        }

        return new BigDecimal(allSum.doubleValue() / persons.size()).setScale(2, RoundingMode.HALF_EVEN);
    }
}
