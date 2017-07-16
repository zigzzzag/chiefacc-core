package org.chiefacc;

import java.util.Collections;
import java.util.NavigableSet;
import java.util.TreeSet;

public class SumDistributor {

    private NavigableSet<PersonPair> distrRes = new TreeSet<>();
    private double averageSum = -1;

    public SumDistributor() {
    }


    //todo remake
    public NavigableSet<PersonPair> distribute(NavigableSet<Person> persons) {

        if (persons == null || persons.isEmpty() || persons.first().getSum() == averageSum) {
            distrRes = new TreeSet<>();
            averageSum = -1;
            return Collections.emptyNavigableSet();
        }

        if (averageSum == -1) {
            averageSum = averageFromPersons(persons);
        }

        double sumToDistr = persons.first().getSum() - averageSum;
        persons.first().setSum(averageSum);

        // распределяем разницу первого по всем начиная с последнего
        final NavigableSet<Person> personsWithoutFirst = persons.subSet(persons.first(), false, persons.last(), true);
        for (Person p : personsWithoutFirst.descendingSet()) {
            if (sumToDistr <= averageSum - p.getSum()) {
                p.setSum(p.getSum() + sumToDistr);
                distrRes.add(new PersonPair(p, persons.first(), sumToDistr));
                break;
            } else {
                double diff = averageSum - p.getSum();
                p.setSum(p.getSum() + diff);
                distrRes.add(new PersonPair(p, persons.first(), diff));
                sumToDistr -= diff;
            }
        }

        distrRes.addAll(distribute(personsWithoutFirst));

        return distrRes;
    }

    private double averageFromPersons(NavigableSet<Person> persons) {
        double allSum = 0;
        for (Person p : persons) {
            allSum += p.getSum();
        }

        return allSum / persons.size();
    }
}
