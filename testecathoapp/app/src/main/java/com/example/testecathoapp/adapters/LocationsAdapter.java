package com.example.testecathoapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testecathoapp.R;

import androidx.recyclerview.widget.RecyclerView;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder> {
    String[] mLocations;
    Context mContext;

    public LocationsAdapter(String[] locations, Context context) {
        mContext = context;
        mLocations = locations;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent,
                                                  int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_location, parent, false);

        return new LocationsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        final LocationsViewHolder lvh = holder;

        lvh.itemView.setVisibility(View.VISIBLE);
        lvh.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

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