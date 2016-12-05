package com.example.user.rainorshine.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.rainorshine.CityDetails;
import com.example.user.rainorshine.MainActivity;
import com.example.user.rainorshine.data.City;
import com.example.user.rainorshine.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by user on 2016. 04. 26..
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> implements CityTouchHelperAdapter {
    private Context context;
    private List<City> cities = new ArrayList<City>();

    public WeatherAdapter(Context context) {
        this.context = context;
        for (int j = 0; j < 1; j++) {
            cities.add(new City("City: " + j));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_row,parent,false);

        return new ViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        holder.tvCity.setText(cities.get(position).getCity());
        holder.ivDetails.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("IT'S WORKING WOOT");
                        ((MainActivity)context).showWeatherIntent();
                    }
                });

    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void addCity(City city) {
        cities.add(0, city);
        notifyItemInserted(0);
    }
    @Override
    public void onItemDismiss(int position){
        cities.remove(position);
        notifyItemRemoved(position);
    }
    public void deleteAll(){
        cities.clear();
    }
    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(cities, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(cities, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvCity;
        private ImageView ivDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            ivDetails=(ImageView)itemView.findViewById(R.id.ivDetails);
        }
    }
}
