package com.example.testecathoapp.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class JobsViewPagerAdapter extends PagerAdapter
{

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Será mudado depois quando o método for implementado
        return new Object();
    }
}
