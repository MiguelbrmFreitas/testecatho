package com.example.testecathoapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testecathoapp.R;

import androidx.recyclerview.widget.RecyclerView;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder> {
    String[] mLocations;

    public LocationsAdapter(String[] locations) {
        mLocations = locations;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_location, parent, false);

        LocationsViewHolder vh = new LocationsViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        final LocationsViewHolder lvh = holder;

        // Seta o texto para cada localização
        lvh.mLocationTextView.setText(mLocations[position]);
    }

    /**
     * Classe para definir o view holder com o layout de cada item do adapter
     */
    static class LocationsViewHolder extends RecyclerView.ViewHolder {

        TextView mLocationTextView;

        LocationsViewHolder(View itemView) {
            super(itemView);
            mLocationTextView = itemView.findViewById(R.id.layout_location_text_view);
        }
    }

    @Override
    public int getItemCount() {
        return mLocations.length;
    }
}