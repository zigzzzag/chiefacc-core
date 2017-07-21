package org.chiefacc.core;

import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class SumDistributor {

    public SumDistributor() {
    }

    public NavigableSet<PersonPair> distribute(PriorityQueue<Person> persons) {
        return distribute(persons, new TreeSet<PersonPair>(), averageFromPersons(persons));
    }

    //todo remake PriorityQueue on List
    private NavigableSet<PersonPair> distribute(PriorityQueue<Person> persons, NavigableSet<PersonPair> distrRes,
                                                final double averageSum) {

        if (persons == null || persons.isEmpty() || persons.peek().getSum() == averageSum) {
            return new TreeSet<>();
        }

        double sumToDistr = persons.peek().getSum() - averageSum;

        // распределяем разницу первого по всем начиная с последнего
        final Person firstPerson = persons.poll();


        final PriorityQueue<Person> personsReverse = new PriorityQueue<>(persons.size(), Person.minToMaxComparator());
        personsReverse.addAll(persons);
        while (!personsReverse.isEmpty()) {
            Person p = personsReverse.poll();
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

        distrRes.addAll(distribute(persons));

        return distrRes;
    }

    private double averageFromPersons(PriorityQueue<Person> persons) {
        double allSum = 0;
        for (Person p : persons) {
            allSum += p.getSum();
        }

        return allSum / persons.size();
    }
}
