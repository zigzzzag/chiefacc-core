package org.chiefacc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.TreeSet;

class SumDistributor {

    SumDistributor() {
    }

    NavigableSet<PersonPair> distribute(PriorityQueue<Person> persons) {
        return distribute(persons, new TreeSet<>(), averageFromPersons(persons));
    }

    private NavigableSet<PersonPair> distribute(PriorityQueue<Person> persons, NavigableSet<PersonPair> distrRes,
                                                final double averageSum) {

        if (persons == null || persons.isEmpty() || persons.peek().getSum() == averageSum) {
            return Collections.emptyNavigableSet();
        }

        double sumToDistr = persons.peek().getSum() - averageSum;

        // распределяем разницу первого по всем начиная с последнего
        final Person firstPerson = persons.poll();

        final List<Person> personsList = new ArrayList<>(persons);
        for (int i = persons.size() - 1; i >= 0; i--) {
            Person p = personsList.get(i);
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
