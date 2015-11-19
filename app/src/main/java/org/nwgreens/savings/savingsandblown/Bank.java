package org.nwgreens.savings.savingsandblown;

/**
 * Created by root on 11/16/15.
 */
public class Bank {
    private Balance Total;

    private Transaction[] Transactions;

    public Transaction[] getTransactions() {
        return Transactions;
    }

    public void setTransactions(Transaction[] transactions) {
        Transactions = transactions;
    }

    public Balance getTotal() {
        return Total;
    }

    public void setTotal(Balance total) {
        Total = total;
    }
}
