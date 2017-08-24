package com.muzzy404.waiternotepad;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.muzzy404.waiternotepad.fragments.TablesFragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private static final int EDIT_PAGE = 1;
    private static final int SERVE_PAGE = 2;
    private static final int CLOSE_PAGE = 3;

    private int selectedPage;

    private Toolbar toolbar;

    private TablesFragment tablesFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_edit:
                    selectedPage = EDIT_PAGE;
                    return true;
                case R.id.nav_serve:
                    selectedPage = SERVE_PAGE;
                    return true;
                case R.id.nav_close:
                    selectedPage = CLOSE_PAGE;
                    return true;
            }
            return false;
        }

    };

    private String getCurrentDateTitle() {
        return new SimpleDateFormat("EEEE d MMM yyyy").format(new Date());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getCurrentDateTitle());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavItemSelectedListener);

        tablesFragment = new TablesFragment();

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.activity_main_container, tablesFragment);
        transaction.commit();

        selectedPage = EDIT_PAGE;
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
                Toast.makeText(getApplicationContext(), "Settings", Toast.LENGTH_SHORT).show();
                return true;
        }
        return false;
    }
}
