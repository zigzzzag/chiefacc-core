package org.chiefacc.core;

import java.util.Collection;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class SumDistributor {

    public SumDistributor() {
    }

    public Collection<PersonPair> distribute(Set<Person> persons) {
        final PriorityQueue<Person> personsMaxToMin = new PriorityQueue<>(persons.size(), Person.maxToMinComparator());
        personsMaxToMin.addAll(persons);

        return distribute(personsMaxToMin, new TreeSet<PersonPair>(), averageFromPersons(persons));
    }

    private NavigableSet<PersonPair> distribute(PriorityQueue<Person> personsMaxToMin, NavigableSet<PersonPair> distrRes,
                                                final double averageSum) {

        if (personsMaxToMin == null || personsMaxToMin.isEmpty() || personsMaxToMin.peek().getSum() == averageSum) {
            return new TreeSet<>();
        }

        double sumToDistr = personsMaxToMin.peek().getSum() - averageSum;

        // распределяем разницу первого по всем начиная с последнего
        final Person firstPerson = personsMaxToMin.poll();


        final PriorityQueue<Person> personsMinToMax = new PriorityQueue<>(personsMaxToMin.size(), Person.minToMaxComparator());
        personsMinToMax.addAll(personsMaxToMin);
        while (!personsMinToMax.isEmpty()) {
            Person p = personsMinToMax.poll();
            if (sumToDistr <= averageSum - p.getSum()) {
                p.setSum(p.getSum() + sumToDistr);
                distrRes.add(new PersonPair(p, firstPerson, sumToDistr));
                break;
            } else {
                double diff = averageSum - p.getSum();
                if (diff != 0) {
                    p.setSum(p.getSum() + diff);
                    distrRes.add(new PersonPair(p, firstPerson, diff));
                    sumToDistr -= diff;
                }
            }
        }

        distribute(personsMaxToMin, distrRes, averageSum);

        return distrRes;
    }

    private double averageFromPersons(Collection<Person> persons) {
        double allSum = 0;
        for (Person p : persons) {
            allSum += p.getSum();
        }

        return allSum / persons.size();
    }
}
