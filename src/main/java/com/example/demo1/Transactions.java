package com.example.demo1;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Transactions {
    private SimpleStringProperty transaction_ID;
    private SimpleStringProperty transaction_type;
    private SimpleStringProperty amount;
    private SimpleStringProperty timestamp;

    public static ObservableList<Transactions> transaction_list = FXCollections.observableArrayList();

    public Transactions(String transaction_ID, String transaction_type, String amount, String timestamp)
    {
        this.transaction_ID = new SimpleStringProperty(transaction_ID);
        this.transaction_type = new SimpleStringProperty(transaction_type);
        this.amount = new SimpleStringProperty(amount);
        this.timestamp = new SimpleStringProperty(timestamp);
    }

    public static int add_t(Transactions t){
        try
        {
            transaction_list.add(t);
            return 1;
        }
        catch(Exception e){
            System.out.println(e);
            return -1;
        }
    }

    public String getTransaction_ID() {
        return transaction_ID.get();
    }

    public SimpleStringProperty transaction_IDProperty() {
        return transaction_ID;
    }

    public void setTransaction_ID(String transaction_ID) {
        this.transaction_ID.set(transaction_ID);
    }

    public String getTransaction_type() {
        return transaction_type.get();
    }

    public SimpleStringProperty transaction_typeProperty() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type.set(transaction_type);
    }

    public String getAmount() {
        return amount.get();
    }

    public SimpleStringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public String getTimestamp() {
        return timestamp.get();
    }

    public SimpleStringProperty timestampProperty() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp.set(timestamp);
    }
}
