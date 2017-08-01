package org.chiefacc.core;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class SumDistributor {

    private static final BigDecimal EPSILON = BigDecimal.valueOf(0.001);

    public SumDistributor() {
    }

    public Collection<PersonPairPay> distribute(Set<Person> persons) {
        return distribute(persons, averageFromPersons(persons));
    }

    private Collection<PersonPairPay> distribute(Set<Person> persons, BigDecimal averageFromPersons) {
        final PriorityQueue<Person> personsMaxToMin = new PriorityQueue<>(persons.size(), Person.maxToMinComparator());

        // create copy persons
        for (Person p : persons) {
            personsMaxToMin.add(new Person(p.getName(), p.getSum().setScale(2, RoundingMode.HALF_EVEN)));
        }

        return distribute(personsMaxToMin, new TreeSet<PersonPairPay>(), averageFromPersons);
    }

    private NavigableSet<PersonPairPay> distribute(PriorityQueue<Person> personsMaxToMin, NavigableSet<PersonPairPay> distRes,
                                                   final BigDecimal averageSum) {

        if (personsMaxToMin == null || personsMaxToMin.size() <= 1
                || sumEqual(personsMaxToMin.peek().getSum(), averageSum)) {
            return new TreeSet<>();
        }

        final Person firstPerson = personsMaxToMin.poll();
        BigDecimal sumToDist = firstPerson.getSum().subtract(averageSum);

        final PriorityQueue<Person> personsMinToMax = new PriorityQueue<>(personsMaxToMin.size(), Person.minToMaxComparator());
        personsMinToMax.addAll(personsMaxToMin);
        while (!personsMinToMax.isEmpty()) {
            Person p = personsMinToMax.poll();
            if (sumToDist.compareTo(averageSum.subtract(p.getSum())) <= 0) {
                p.setSum(p.getSum().add(sumToDist));
                distRes.add(new PersonPairPay(p.getName(), firstPerson.getName(), sumToDist));

                // for reorder
                if (personsMaxToMin.remove(p)) {
                    personsMaxToMin.add(p);
                }

                break;
            } else {
                final BigDecimal diff = averageSum.subtract(p.getSum());
                if (diff.abs().compareTo(EPSILON) > 0) {
                    p.setSum(p.getSum().add(diff));

                    // for reorder
                    if (personsMaxToMin.remove(p)) {
                        personsMaxToMin.add(p);
                    }

                    distRes.add(new PersonPairPay(p.getName(), firstPerson.getName(), diff));
                    sumToDist = sumToDist.subtract(diff);
                }
            }
        }

        distribute(personsMaxToMin, distRes, averageSum);

        return distRes;
    }

    private boolean sumEqual(BigDecimal sum1, BigDecimal sum2) {
        return sum1.subtract(sum2).abs().compareTo(EPSILON) <= 0;
    }

    public BigDecimal averageFromPersons(Collection<Person> persons) {
        BigDecimal allSum = BigDecimal.ZERO;
        for (Person p : persons) {
            allSum = allSum.add(p.getSum());
        }

        return BigDecimal.valueOf(allSum.doubleValue()).divide(
                BigDecimal.valueOf(persons.size()), 2, RoundingMode.HALF_EVEN);
    }
}
