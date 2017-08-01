package org.chiefacc.core;

import java.math.BigDecimal;

public class PersonPairPay implements Comparable<PersonPairPay> {

    private final String payFromName;
    private final String payToName;
    private final BigDecimal sum;

    public PersonPairPay(String payFromName, String payToName, BigDecimal sum) {
        this.payFromName = payFromName;
        this.payToName = payToName;
        this.sum = sum;
    }

    public PersonPairPay(String payFromName, String payToName, String sum) {
        this(payFromName, payToName, new BigDecimal(sum));
    }

    public PersonPairPay(Person payFrom, Person payTo, String sum) {
        this(payFrom.getName(), payTo.getName(), new BigDecimal(sum));
    }

    public String getPayFromName() {
        return payFromName;
    }

    public String getPayToName() {
        return payToName;
    }

    private BigDecimal getSum() {
        return sum;
    }

    private String getBothNames() {
        return payFromName + payToName;
    }

    @Override
    public int compareTo(PersonPairPay pp) {
        if (this.sum.equals(pp.getSum())) {
            return getBothNames().compareTo(pp.getBothNames());
        } else {
            return pp.getSum().compareTo(this.sum);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonPairPay)) return false;

        PersonPairPay that = (PersonPairPay) o;

        if (payFromName != null ? !payFromName.equals(that.payFromName) : that.payFromName != null) return false;
        if (payToName != null ? !payToName.equals(that.payToName) : that.payToName != null) return false;
        return sum != null ? sum.equals(that.sum) : that.sum == null;
    }

    @Override
    public int hashCode() {
        int result = payFromName != null ? payFromName.hashCode() : 0;
        result = 31 * result + (payToName != null ? payToName.hashCode() : 0);
        result = 31 * result + (sum != null ? sum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return payFromName + " -> " + payToName + " : " + sum;
    }
}
