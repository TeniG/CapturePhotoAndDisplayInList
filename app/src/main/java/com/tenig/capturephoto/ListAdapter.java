package com.tenig.capturephoto;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    Context context;
    List<Locations> locationsList;

    public ListAdapter(Context context,List<Locations> locationsList) {
        this.context=context;
        this.locationsList=locationsList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list, parent, false);
        ListViewHolder viewHolder = new ListViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {


        holder.tv_latlong.setText("Latitude :"+String.valueOf(locationsList.get(position).getLatitude())+",  Longitude :"+String.valueOf(locationsList.get(position).getLongitude()));

        //convert byte to bitmap take from contact class
        byte[] outImage=locationsList.get(position).getImage();
        ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        holder.iv_location.setImageBitmap(theImage);
    }

    @Override
    public int getItemCount() {
        return locationsList.size();
    }

    public void updateDataList(List<Locations> dataList) {
        Log.d("MainActivity", "pppppp testttt updateList " + dataList.size());
        this.locationsList = dataList;
        notifyDataSetChanged();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_location;
        TextView tv_latlong;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_location=itemView.findViewById(R.id.iv_location);
            tv_latlong=itemView.findViewById(R.id.tv_latlong);
        }
    }


}