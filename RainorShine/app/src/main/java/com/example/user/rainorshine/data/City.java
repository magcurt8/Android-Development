package com.example.user.rainorshine.data;

import java.io.Serializable;

/**
 * Created by user on 2016. 04. 26..
 */
public class City implements Serializable{
    private String city;

    public City(){}

    public City(String city){
        this.city=city;
    }
    public String getCity(){
        return city;
    }
    public void setCity(String city){
        this.city=city;
    }
}
