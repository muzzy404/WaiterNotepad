package com.muzzy404.waiternotepad;

import android.support.annotation.NonNull;

public class Order implements Comparable<Order> {
    private int number;
    private int table;
    private String description;

    Order(int number, int table, String description) {
        this.number = number;
        this.table = table;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public int getTable() {
        return table;
    }

    public String getDescription() {
        return description;
    }

    // for sorting of books
    @Override
    public int compareTo(@NonNull Order o) {
        return Integer.valueOf(table).compareTo(o.table);
    }
}
