package org.chiefacc.core;

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

    private double getSum() {
        return sum;
    }

    private String getBothNames() {
        return payFrom.getName() + payTo.getName();
    }

    @Override
    public int compareTo(PersonPair pp) {
        if (this.sum < pp.getSum()) {
            return 1;
        } else if (this.sum > pp.getSum()) {
            return -1;
        } else {
            return getBothNames().compareTo(pp.getBothNames());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonPair)) return false;

        PersonPair that = (PersonPair) o;

        if (Double.compare(that.sum, sum) != 0) return false;
        if (payFrom != null ? !payFrom.equals(that.payFrom) : that.payFrom != null) return false;
        return payTo != null ? payTo.equals(that.payTo) : that.payTo == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = payFrom != null ? payFrom.hashCode() : 0;
        result = 31 * result + (payTo != null ? payTo.hashCode() : 0);
        temp = Double.doubleToLongBits(sum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return payFrom.getName() + " -> " + payTo.getName() + " : " + sum;
    }
}
