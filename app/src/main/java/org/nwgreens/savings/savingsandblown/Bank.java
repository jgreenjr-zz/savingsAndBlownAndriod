package org.nwgreens.savings.savingsandblown;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by root on 11/16/15.
 */
public class Bank {
    private Balance Total;

    private Transaction[] Transactions;

    public ArrayList<Transaction> getTransactions() {
        return new ArrayList<>(Arrays.asList(Transactions));
    }

    public ArrayList<Transaction> getTransactions(int filterType) {
        switch(filterType){
            case R.id.currentOnly:
                return getTransactions(new Date());
            case R.id.NextSeven:
                Date d = new Date();
                Calendar c = Calendar.getInstance();
                c.setTime(d);
                c.add(Calendar.DATE, 7);
                d = c.getTime();
                return getTransactions(d);
            default:
                return getTransactions();
        }
    }

    public ArrayList<Transaction> getTransactions(Date maxDate) {
        ArrayList<Transaction> returnValue = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");


        for(int i = 0; i < Transactions.length; i++){
            try {
                if (!sdf.parse(Transactions[i].getDate()).after(maxDate)){
                    returnValue.add(Transactions[i]);
                }
            }
            catch(ParseException pe){

            }
        }

        return returnValue;
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
