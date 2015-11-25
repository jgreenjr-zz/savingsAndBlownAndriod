package org.nwgreens.savings.savingsandblown;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by root on 11/16/15.
 */
public class Bank {
    private Balance Total;

    private Transaction[] Transactions;

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(Arrays.asList(Transactions));
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
