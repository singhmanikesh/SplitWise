// src/main/java/com/manikesh/splitwise/splitwise/model/BalanceSheet.java
package com.manikesh.splitwise.splitwise.model;

import java.util.Map;

public class BalanceSheet {

    private double totalPaid;
    private double totalExpense;
    private Map<User, Double> balance;
}