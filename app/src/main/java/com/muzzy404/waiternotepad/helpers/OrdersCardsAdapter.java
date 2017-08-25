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

import com.muzzy404.waiternotepad.R;
import com.muzzy404.waiternotepad.fragments.TablesFragment;

import java.util.Iterator;
import java.util.LinkedList;

public class OrdersCardsAdapter extends RecyclerView.Adapter<OrdersCardsAdapter.ViewHolder> {

    private Resources res;

    private TablesFragment.Order[] orders;
    private Integer[] colors;

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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("listener", "position = " + getAdapterPosition());
                }
            });

        }
    }

    public OrdersCardsAdapter(TablesFragment.Order[] orders) {
        this.orders = orders;
        colors = new Integer[orders.length];
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinkedList<Integer> colorsSet = new LinkedList<>();
        Context context = parent.getContext();

        res = parent.getResources();

        // colorsSet for cards
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardBlue));
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardPurple));
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardPink));
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardGrey));
        colorsSet.add(ContextCompat.getColor(context, R.color.colorCardGreen));

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

        View view = LayoutInflater.from(context)
                .inflate(R.layout.order_card_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.orderTitle.setText(res.getString(R.string.title_order_number, orders[i].getNumber()));
        holder.orderTable.setText(res.getString(R.string.title_table_number, orders[i].getTable()));
        holder.orderDescription.setText(orders[i].getDescription());
        holder.card.setCardBackgroundColor(colors[i]);
    }

    @Override
    public int getItemCount() {
        return orders.length;
    }

}
