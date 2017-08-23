package com.muzzy404.waiternotepad.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.muzzy404.waiternotepad.R;

import java.util.Arrays;

public class TablesFragment extends Fragment {

    private RecyclerView recyclerView;

    public TablesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tables, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_orders);

        return rootView;
    }


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

        // for sorting of books
        @Override
        public int compareTo(@NonNull Order o) {
            return Integer.valueOf(table).compareTo(o.table);
        }
    }


    private Order[] createOrders() {
        Order[] orders = {
                new Order(1, 3, "fat ugly woman"),
                new Order(2, 3, "little girl with red balloon"),
                new Order(30, 1, "man in black suit"),
                new Order(40, 3, "pretty girl in blue dress"),
                new Order(50, 7, "beautiful old woman"),
                new Order(51, 7, "red head"),
                new Order(52, 3, "my best friend"),
                new Order(53, 3, "stupid russians"),
                new Order(54, 7, "smart little boy"),
                new Order(55, 1, "poor old man"),
        };

        Arrays.sort(orders);

        return orders;
    }

}
