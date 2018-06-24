package com.example.memo.lugardasraizes.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.memo.lugardasraizes.Fragment.GraficoFragment;
import com.example.memo.lugardasraizes.Fragment.Inicio;


public class TabsAdapter extends FragmentPagerAdapter {
    private final String TAG = "AdapterDosFragment";

    public TabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Log.i(TAG, "Novo Inicio");
                return new Inicio();
            case 1:
                Log.i(TAG, "Novo Gragico");
                return new GraficoFragment();
            default:
                return null;

        }

    }
}
