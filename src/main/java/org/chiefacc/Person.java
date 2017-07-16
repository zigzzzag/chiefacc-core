package org.chiefacc;

public class Person implements Comparable<Person> {

    private final String name;
    private double sum;

    public Person(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    @Override
    public int compareTo(Person p) {
        if (p.getSum() > this.sum) {
            return 1;
        } else {
            return -1;
        }
    }
}
