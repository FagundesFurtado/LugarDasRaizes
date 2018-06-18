package com.example.memo.lugardasraizes.Main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.BottomNavigationView;
import android.widget.Toast;


import com.example.memo.lugardasraizes.Adapter.MyTabsListener;
import com.example.memo.lugardasraizes.Adapter.TabsAdapter;
import com.example.memo.lugardasraizes.Dados.Grafico;
import com.example.memo.lugardasraizes.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private Toolbar toolbar;
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager()));
        final ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        actionBar.addTab(actionBar.newTab().
                setText("Função").
                setTabListener(new MyTabsListener(viewPager, 0)));
        actionBar.addTab(actionBar.newTab().
                setText("Gráfico").
                setTabListener(new MyTabsListener(viewPager, 1)));



        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float
                    positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }


    public void graficoFragment(){


        viewPager.setCurrentItem(1);


    }



}
