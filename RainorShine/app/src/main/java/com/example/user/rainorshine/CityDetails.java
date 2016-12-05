package com.example.user.rainorshine;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.user.rainorshine.fragment.FragmentAdapter;

public class CityDetails extends AppCompatActivity {
    //THIS SECTION WILL BE IN SUBLIME TEXT FOR NOW

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_details);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);

        pager.setAdapter(adapter);
    }
}
