package org.chiefacc;

public class PersonPair implements Comparable<PersonPair> {

    private final Person payFrom;
    private final Person payTo;
    private final double sum;

    PersonPair(Person payFrom, Person payTo, double sum) {
        this.payFrom = payFrom;
        this.payTo = payTo;
        this.sum = sum;
    }

    public Person getPayFrom() {
        return payFrom;
    }

    public Person getPayTo() {
        return payTo;
    }

    private double getSum() {
        return sum;
    }

    @Override
    public int compareTo(PersonPair pp) {
        if (this.sum > pp.getSum()) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String toString() {
        return payFrom.getName() + " -> " + payTo.getName() + " : " + sum;
    }
}
