package com.artapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.artapp.R;

public class HomeActivity extends AppCompatActivity {
    CardView txtvhbookandtrack;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        builder = new AlertDialog.Builder(this);
        txtvhbookandtrack = findViewById(R.id.txtvhbookandtrack);
        txtvhbookandtrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vh=new Intent(HomeActivity.this,VehicleActivity.class);
                                startActivity(vh);
//                builder.setMessage("What do you want to do?")
//                        .setCancelable(false)
//                        .setPositiveButton("Tracking", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                Intent track=new Intent(MainActivity.this,TrackMapActivity.class);
//                                startActivity(track);
//                            }
//                        })
//                        .setNeutralButton("Booking", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                Intent bookintent = new Intent(MainActivity.this, VehicleActivity.class);
//                                startActivity(bookintent);
//                            }
//                        });
//                AlertDialog alert = builder.create();
//                alert.setTitle("Booking or Tracking");
//                alert.show();
            }
        });
    }
}