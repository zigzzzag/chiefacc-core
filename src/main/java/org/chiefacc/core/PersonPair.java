package org.chiefacc.core;

import java.math.BigDecimal;

public class PersonPair implements Comparable<PersonPair> {

    private final Person payFrom;
    private final Person payTo;
    private final BigDecimal sum;

    public PersonPair(Person payFrom, Person payTo, BigDecimal sum) {
        this.payFrom = payFrom;
        this.payTo = payTo;
        this.sum = sum;
    }

    public PersonPair(Person payFrom, Person payTo, String sum) {
        this(payFrom, payTo, new BigDecimal(sum));
    }

    public Person getPayFrom() {
        return payFrom;
    }

    public Person getPayTo() {
        return payTo;
    }

    private BigDecimal getSum() {
        return sum;
    }

    private String getBothNames() {
        return payFrom.getName() + payTo.getName();
    }

    @Override
    public int compareTo(PersonPair pp) {
        if (this.sum.equals(pp.getSum())) {
            return getBothNames().compareTo(pp.getBothNames());
        } else {
            return pp.getSum().compareTo(this.sum);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonPair)) return false;

        PersonPair that = (PersonPair) o;

        if (payFrom != null ? !payFrom.equals(that.payFrom) : that.payFrom != null) return false;
        if (payTo != null ? !payTo.equals(that.payTo) : that.payTo != null) return false;
        return sum != null ? sum.equals(that.sum) : that.sum == null;
    }

    @Override
    public int hashCode() {
        int result = payFrom != null ? payFrom.hashCode() : 0;
        result = 31 * result + (payTo != null ? payTo.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return payFrom.getName() + " -> " + payTo.getName() + " : " + sum;
    }
}
