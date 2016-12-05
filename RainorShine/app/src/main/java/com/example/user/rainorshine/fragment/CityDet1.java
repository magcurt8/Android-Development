package com.example.user.rainorshine.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.user.rainorshine.R;
import com.example.user.rainorshine.data.Details;
import com.example.user.rainorshine.network.WeatherAPI;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by user on 2016. 04. 30..
 */

public class CityDet1 extends Fragment {
    ImageView weatherImage;
    TextView city;
    TextView weather;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.city_det_1, container, false);
        //use event bus to retrieve current city name and the information about the current weather information
        city=(TextView)view.findViewById(R.id.tvCityName);
        weather=(TextView)view.findViewById(R.id.tvWeatherGeneral);
        weatherImage=(ImageView)view.findViewById(R.id.ivWeather);

        Retrofit retrofit=new Retrofit.Builder().
                //pretty sure this is right, but it may also have a problem
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
                        Glide.with(getContext())
                                .load("http://openweathermap.org/img/w/" +
                                        response.body().getWeather().get(0).getIcon() + ".png")
                                .override(400, 350)
                                .into(weatherImage);
                    }

                    @Override
                    public void onFailure(Call<Details> call, Throwable t) {
                        city.setText("ERROR: " + t.getMessage());

                    }
                });
                    return view;
                }
        public static CityDet1 newInstance() {
            CityDet1 cd1 = new CityDet1();
            return cd1;
    }
}
