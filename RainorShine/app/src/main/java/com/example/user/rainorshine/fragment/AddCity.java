package com.example.user.rainorshine.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.rainorshine.R;
import com.example.user.rainorshine.MainActivity;
import com.example.user.rainorshine.adapter.WeatherAdapter;

/**
 * Created by user on 2016. 04. 27..
 */
public class AddCity extends DialogFragment {
    String city;


    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        View layout=inflater.inflate(R.layout.add_city,null);
        final EditText newCity=(EditText)layout.findViewById(R.id.etCity);


        builder.setView(layout)

                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        city=newCity.getText().toString();
                        ((MainActivity) getActivity()).createNewCity(city);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        AddCity.this.getDialog().cancel();
                    }
                })
                .setTitle("Add City");

                return builder.create();
    }
    public static AddCity newInstance() {
        AddCity f = new AddCity();
        return f;
    }

}
