package org.chiefacc;

public class PersonPair implements Comparable<PersonPair> {

    private final Person payFrom;
    private final Person payTo;
    private final double sum;

    public PersonPair(Person payFrom, Person payTo, double sum) {
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

    public double getSum() {
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
        return "Pay from " + payFrom.getName() + " to " + payTo.getName() + " sum " + sum;
    }
}
