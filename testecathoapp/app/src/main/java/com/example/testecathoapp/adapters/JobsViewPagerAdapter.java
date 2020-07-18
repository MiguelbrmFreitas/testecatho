package com.example.testecathoapp.adapters;

import android.view.ViewGroup;

import com.example.testecathoapp.fragments.JobSuggestionFragment;
import com.example.testecathoapp.models.JobSuggestion;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class JobsViewPagerAdapter extends FragmentPagerAdapter
{
    private JobSuggestion[] mJobsSuggestion;

    public JobsViewPagerAdapter(FragmentManager fragmentManager, JobSuggestion[] jobsSuggestion) {
        super(fragmentManager);
        mJobsSuggestion = jobsSuggestion;
    }

    @Override
    public int getCount() {
        if (mJobsSuggestion != null) {
            return mJobsSuggestion.length;
        } else {
            return 0;
        }
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return JobSuggestionFragment.newInstance(mJobsSuggestion[position], position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Evitando que os fragments sejam destruídos e o estado seja perdido
        //super.destroyItem(ViewGroup container, int position, Object object);
    }
}
