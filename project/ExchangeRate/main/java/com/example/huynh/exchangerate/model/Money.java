package com.example.huynh.exchangerate.model;

/**
 * Created by huynh on 11/25/2017.
 */

public class Money {
    public long au9999;
    public long au18k;
    public long usd;
    public long euro;
    public long yen;

    @Override
    public String toString() {
        return "Money{" +
                "au9999=" + au9999 +
                ", au18k=" + au18k +
                ", usd=" + usd +
                ", euro=" + euro +
                ", yen=" + yen +
                '}';
    }

    public Money(long au9999, long au18k, long usd, long euro, long yen) {
        this.au9999 = au9999;
        this.au18k = au18k;
        this.usd = usd;
        this.euro = euro;
        this.yen = yen;
    }

    public Money() {

    }
}
