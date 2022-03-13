package com.artapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.artapp.R;
import com.artapp.activities.BookingActivity;
import com.artapp.models.vehicle;

import java.util.ArrayList;


public class VehicleInfoRecyclerAdapter extends RecyclerView.Adapter<VehicleInfoRecyclerAdapter.ViewHolder> {
    private ArrayList<vehicle> vehicleinfoArrayList;
    private Context context;

    public VehicleInfoRecyclerAdapter(ArrayList<vehicle> vehicleinfoArrayList, Context context) {
        this.vehicleinfoArrayList = vehicleinfoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.vehicle_img, parent, false);
        VehicleInfoRecyclerAdapter.ViewHolder viewHolder = new VehicleInfoRecyclerAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        vehicle vhinfo = vehicleinfoArrayList.get(position);
        holder.txtvhno.setText(vhinfo.vhno);
        holder.txtFrom.setText(vhinfo.fromlocation);
        holder.txtTo.setText(vhinfo.tolocation);
        holder.containerInfo.setOnClickListener(new View.OnClickListener() {
//            String vhno = vhinfo.getVhno();
//            String fromlocation = vhinfo.getFromlocation();
//            String tolocation = vhinfo.getTolocation();

            @Override
            public void onClick(View v) {
                Intent personalDetail = new Intent(context, BookingActivity.class);
                context.startActivity(personalDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicleinfoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView txtvhno;
        public TextView txtFrom, txtTo;
        public ImageView vhimg;
        public LinearLayout containerInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.txtvhno = (TextView) itemView.findViewById(R.id.txtvhno);
            this.txtFrom = (TextView) itemView.findViewById(R.id.txtFrom);
            this.txtTo = itemView.findViewById(R.id.txtTo);
            this.vhimg = itemView.findViewById(R.id.vhimg);
            containerInfo = (LinearLayout) itemView.findViewById(R.id.containerInfo);
        }
    }
}




