package com.muzzy404.waiternotepad.fragments;


import android.content.Intent;
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

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_tables);

        return rootView;
    }


    public class Order implements Comparable<Order> {
        private int number;
        private int table;

        Order(int number, int table) {
            this.number = number;
            this.table = table;
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
            return Integer.valueOf(table).compareTo(Integer.valueOf(o.table));
        }
    }


    private Order[] createOrders() {
        Order[] orders = {
                new Order(1, 3),
                new Order(2, 3),
                new Order(30, 1),
                new Order(40, 3),
                new Order(50, 7),
                new Order(51, 7),
                new Order(52, 3),
                new Order(53, 3),
                new Order(54, 7),
                new Order(55, 1),
        };

        Arrays.sort(orders);

        return orders;
    }

}
