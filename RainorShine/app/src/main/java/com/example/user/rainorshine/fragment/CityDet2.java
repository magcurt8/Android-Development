package com.example.user.rainorshine.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.rainorshine.R;
import com.example.user.rainorshine.data.Details;
import com.example.user.rainorshine.network.WeatherAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016. 04. 30..
 */
public class CityDet2 extends Fragment {
    TextView city;
    TextView weather;
    TextView temperature;
    TextView wind;
    TextView sunrise;
    TextView sunset;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_det_2, container, false);
        city=(TextView)view.findViewById(R.id.city);
        weather=(TextView)view.findViewById(R.id.weather);
        temperature=(TextView)view.findViewById(R.id.temp);
        wind=(TextView)view.findViewById(R.id.wind);
        sunrise=(TextView)view.findViewById(R.id.sunrise);
        sunset=(TextView)view.findViewById(R.id.sunset);

        Retrofit retrofit=new Retrofit.Builder().
                        baseUrl("http://api.openweathermap.org/data/2.5/").
                        addConverterFactory(GsonConverterFactory.create()).
                        build();

        final WeatherAPI weatherAPI= retrofit.create(WeatherAPI.class);

        //not sure if the getName parameters are correct
        Call<Details> weatherQuery= weatherAPI.getName("Budapest","metrics","4e63614312494535b18dd3e4ef47860b");
        weatherQuery.enqueue(new Callback<Details>() {
            @Override
            public void onResponse(Call<Details> call, Response<Details> response) {
                city.setText(response.body().getName());
                temperature.setText(response.body().getMain().getTemp().toString());
                weather.setText(response.body().getWeather().get(0).getDescription().toString());
                wind.setText(response.body().getWind().getSpeed().toString());
                sunrise.setText((""+response.body().getSys().getSunrise()));
                sunset.setText(""+response.body().getSys().getSunset());
            }

            @Override
            public void onFailure(Call<Details> call, Throwable t) {
                city.setText("ERROR: " + t.getMessage());

            }
        });
        return view;
    }
    public static CityDet2 newInstance() {
        CityDet2 cd2 = new CityDet2();
        return cd2;
    }

}
