package org.nwgreens.savings.savingsandblown;

import java.util.Date;

/**
 * Created by root on 11/16/15.
 */
public class Transaction {
    private String payee;
    private String date;
    private String type;
    private double amount;
    private String Status;
    private String category;
    private String Notes;
    private String InsertDate;
    private String UpdateDate;
    private String id;
    private Balance balance;

    public String GetPayee(){
        return payee;
    }

    public String getDate()
    {
        return date;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public String getInsertDate() {
        return InsertDate;
    }

    public void setInsertDate(String insertDate) {
        InsertDate = insertDate;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String updateDate) {
        UpdateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}

