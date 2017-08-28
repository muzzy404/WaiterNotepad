package com.muzzy404.waiternotepad.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muzzy404.waiternotepad.Order;
import com.muzzy404.waiternotepad.R;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class OrdersCardsAdapter extends RecyclerView.Adapter<OrdersCardsAdapter.ViewHolder> {

    private MainActivityCallback callback;

    private Resources res;
    private LinkedList<Integer> colorsSet = new LinkedList<>();

    private Order[] orders;
    private Integer[] colors;

    private void loadOrdersSet(Order[] ordersSet) {
        orders = ordersSet;
        Arrays.sort(orders);

        // set color to each card
        colors = new Integer[orders.length];
        Iterator<Integer> it = colorsSet.iterator();
        Integer currentColor = it.next();

        for(int i = 0; i < orders.length - 1; ++i) {
            colors[i] = currentColor;
            if (orders[i + 1].getTable() != orders[i].getTable()) {
                if (!it.hasNext()){
                    it = colorsSet.iterator();
                }
                currentColor = it.next();
            }
        }
        colors[colors.length - 1] = currentColor;
    }

    public void refreshOrdersSet(Order[] ordersSet) {
        loadOrdersSet(ordersSet);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView orderTitle;
        private TextView orderTable;
        private TextView orderDescription;

        private CardView card;

        private ViewHolder(View itemView) {
            super(itemView);

            orderTitle = (TextView) itemView.findViewById(R.id.order_card_title);
            orderTable = (TextView) itemView.findViewById(R.id.order_card_table);
            orderDescription = (TextView) itemView.findViewById(R.id.order_card_description);

            card = (CardView) itemView.findViewById(R.id.card_order);
        }
    }

    public OrdersCardsAdapter(Order[] orders, Context context) {
        res = context.getResources();
        callback = (MainActivityCallback) context;

        // colorsSet for cards
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardRed));
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardGreen));
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardPink));
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardIndigo));

        loadOrdersSet(orders);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_card_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        Log.d("adapter deb", "bind");

        holder.orderTitle.setText(res.getString(R.string.title_order_number, orders[i].getNumber()));
        holder.orderTable.setText(res.getString(R.string.title_table_number, orders[i].getTable()));
        holder.orderDescription.setText(orders[i].getDescription());

        holder.orderTitle.setTextColor(colors[i]);

        final int pos = i;

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onCardClick(
                        orders[pos].getNumber(),
                        orders[pos].getTable());
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.length;
    }

    @Override
    public long getItemId(int position) {
        return orders[position].getNumber();
    }

    public interface MainActivityCallback {
        void onCardClick(final int order, final int table);
    }
}
