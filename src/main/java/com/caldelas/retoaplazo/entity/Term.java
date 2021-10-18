package com.caldelas.retoaplazo.entity;

public class Term {
    private final double amount;
    private final int terms;
    private final double rate;

    public Term(double amount, int terms, double rate) {
        this.amount = amount;
        this.terms = terms;
        this.rate = rate;
    }

    public double getAmount() {
        return amount;
    }

    public int getTerms() {
        return terms;
    }

    public double getRate() {
        return rate;
    }
}
