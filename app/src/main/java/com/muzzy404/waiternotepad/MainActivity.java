package com.muzzy404.waiternotepad;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.muzzy404.waiternotepad.helpers.OrdersCardsAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements
        OrdersCardsAdapter.MainActivityCallback {

    private Toolbar toolbar;
    private BottomNavigationView navigation;
    private FloatingActionButton fabAddOrder;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private OrdersCardsAdapter adapter;

    private Order[] orders;

    private String getCurrentDateTitle() {
        return new SimpleDateFormat("EEEE d MMM yyyy").format(new Date());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // test set
        orders = createTestOrdersSet();

        navigation = (BottomNavigationView) findViewById(R.id.navigation);

        fabAddOrder = (FloatingActionButton) findViewById(R.id.btn_add_new_order);
        fabAddOrder.setOnClickListener(new AddNewOrder());

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getCurrentDateTitle());

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_orders);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new OrdersCardsAdapter(orders, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.toolbar_settings:
                Toast.makeText(getApplicationContext(), getString(R.string.title_settings),
                        Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }

    @Override
    public void onCardClick(int order, int table) {
        Log.d("callback listener", "Order = " + order + ", table = " + table);

        switch (navigation.getSelectedItemId()) {
            case R.id.nav_edit:
                Toast.makeText(getApplicationContext(),
                        "Edit activity, order = " + order + ", table = " + table,
                        Toast.LENGTH_SHORT).show();
                return;
            case R.id.nav_serve:
                Toast.makeText(getApplicationContext(),
                        "Serve activity, order = " + order + ", table = " + table,
                        Toast.LENGTH_SHORT).show();
                return;
            case R.id.nav_close:
                Toast.makeText(getApplicationContext(),
                        "Close activity, order = " + order + ", table = " + table,
                        Toast.LENGTH_SHORT).show();

                // TODO: remove this later
                orders = new Order[] { new Order(1, 3, "fat ugly woman") };

                // TODO: load again all orders from database

                break;
        }

        adapter.refreshOrdersSet(orders);
    }

    private Order[] createTestOrdersSet() {
        Log.d("creation", "test data set of orders creation");

        return new Order[] {
                new Order(1, 3, "fat ugly woman"),
                new Order(2, 3, "little girl with red balloon"),
                new Order(30, 1, "man in black suit"),
                new Order(40, 8, "pretty girl in blue dress"),
                new Order(50, 7, "beautiful old woman"),
                new Order(51, 7, "red head"),
                new Order(52, 3, "my best friend"),
                new Order(53, 3, "stupid russians"),
                new Order(54, 7, "smart little boy"),
                new Order(55, 1, "poor old man"),
                new Order(56, 8, "my best friend"),
                new Order(57, 9, "stupid russians"),
                new Order(100, 4, "smart little boy"),
                new Order(103, 4, "poor old man"),
        };
    }

    private class AddNewOrder implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(getApplicationContext(), "Add new", Toast.LENGTH_SHORT).show();
        }
    }
}
