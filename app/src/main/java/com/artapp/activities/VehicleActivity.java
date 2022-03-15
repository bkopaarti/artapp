package com.artapp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.PermissionRequest;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.artapp.R;
import com.artapp.adapter.VehicleInfoRecyclerAdapter;
import com.artapp.adapter.vehichleAdapter;
import com.artapp.models.vehicle;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class VehicleActivity extends AppCompatActivity {
    Spinner spinner;
    private ArrayList<vehicle> vehicleinfoArrayList;
    //    private DBHelper dbHelper;
    private VehicleInfoRecyclerAdapter vehicleInfoRecyclerAdapter;
    private RecyclerView recyclerView;
    SupportMapFragment smf;
    FusedLocationProviderClient client;
    LinearLayout institutionalBus;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);
        vehicleinfoArrayList = new ArrayList<>();
//        //dbHelper = new DBHelper(getContext());
        vehicleinfoArrayList = initvehicles();
        builder = new AlertDialog.Builder(this);
        vehicleInfoRecyclerAdapter = new VehicleInfoRecyclerAdapter(vehicleinfoArrayList, this);
        recyclerView = findViewById(R.id.vh);
        institutionalBus = findViewById(R.id.institutionalBUs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(vehicleInfoRecyclerAdapter);
        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient(this);
        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getmylocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }

                }).check();

        institutionalBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater li = LayoutInflater.from(VehicleActivity.this);
                View promptsView = li.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        VehicleActivity.this);
                alertDialogBuilder.setView(promptsView);
                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.editTextDialogUserInput);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        String roll_no=userInput.getText().toString();
                                        if (roll_no.equals("2053008")){
                                            Toast.makeText(VehicleActivity.this, "Your are registered", Toast.LENGTH_SHORT).show();
                                        }
                                        else {
                                            Toast.makeText(VehicleActivity.this, "Sorry you are not registered", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

            }
        });
    }

public void getmylocation(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return;
        }

        Task<Location> task=client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>(){
@Override
public void onSuccess(final Location location){
        smf.getMapAsync(new OnMapReadyCallback(){
@Override
public void onMapReady(GoogleMap googleMap){
        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
        LatLng latLng1=new LatLng(location.getLatitude()+0.0012,location.getLongitude()+0.0012);
        LatLng latLng4=new LatLng(location.getLatitude()-0.0018,location.getLongitude()-0.0015);
        LatLng latLng2=new LatLng(location.getLatitude()-0.0008,location.getLongitude()+0.0006);
        LatLng latLng3=new LatLng(location.getLatitude()+0.0020,location.getLongitude()-0.0012);
        Log.d("lng","lt"+location.getLatitude()+" lang"+location.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("ART Pvt");
        MarkerOptions markerOptions1=new MarkerOptions().position(latLng1).title("ART Pub");
        MarkerOptions markerOptions2=new MarkerOptions().position(latLng2).title("ART Pvt");
        MarkerOptions markerOptions3=new MarkerOptions().position(latLng3).title("ART Pub");
        MarkerOptions markerOptions4=new MarkerOptions().position(latLng4).title("ART Pub");
        googleMap.addMarker(markerOptions);
        googleMap.addMarker(markerOptions1);
        googleMap.addMarker(markerOptions2);
        googleMap.addMarker(markerOptions3);
        googleMap.addMarker(markerOptions4);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));
        }
        });
        }
        });
        }

private ArrayList<vehicle> initvehicles(){
        ArrayList<vehicle> list=new ArrayList<>();

        list.add(new vehicle("MH 10 CP 3428","Sangli","Kolhapur"));
        list.add(new vehicle("MH 10 CP 3428","Sangli","Kolhapur"));
        list.add(new vehicle("MH 10 CP 3428","Sangli","Kolhapur"));
        return list;
        }
        }
//public void addListenerOnSpinnerItemSelection() {
//        spinner.setOnItemSelectedListener(new vehichleAdapter());
//        }
