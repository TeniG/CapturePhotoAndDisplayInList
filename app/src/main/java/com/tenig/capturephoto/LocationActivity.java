package com.tenig.capturephoto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    ListAdapter listAdapter;
    List<Locations> locationsList ;
    DataBaseHandler db;
    RecyclerView rv_data;
LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        rv_data = findViewById(R.id.rv_data);

        db = new DataBaseHandler(this);
        locationsList = db.getAllLocations();

        listAdapter = new ListAdapter(this, new ArrayList<Locations>(0));
        linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rv_data.setLayoutManager(linearLayoutManager);
        rv_data.setAdapter(listAdapter);

        listAdapter.updateDataList(locationsList);
        listAdapter.notifyDataSetChanged();
    }
}
