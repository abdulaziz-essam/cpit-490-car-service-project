package com.example.project_interface;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    GoogleApiClient client;
    LocationRequest request;
    private LocationRequest locationRequest;
    public static final int Request_check_setting = 1001;
    Button goBT;
    Button skip;
    EditText searchET;
    Button findBT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        onStart();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchET =findViewById(R.id.search);
        goBT = findViewById(R.id.go);
        skip = findViewById(R.id.skip);
        findBT= findViewById(R.id.findBT);
        goBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, home.class);
                startActivity(intent);

            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapsActivity.this, home.class);
                startActivity(intent);

            }
        });

        findBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String company = searchET.getText().toString().toUpperCase();
                if       (company.equals("KIA")){
                    findkia();
                }else if (company.equals("MAZDA")){
                    findmazda();
                }else if (company.equals("FERRARI"))
                    findferrari();
                else
                    Toast.makeText(MapsActivity.this,"Write Car Manufacturer" ,Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void findkia()
    {
        LatLng kia = new LatLng(21.622891, 39.201753);
        mMap.addMarker(new MarkerOptions().position(kia).title("Kia Service Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kia));
        Toast.makeText(MapsActivity.this,"The Nearest Service Center" ,Toast.LENGTH_SHORT).show();

    }
    public void findmazda()
    {
        LatLng mazda = new LatLng(21.609139625304195, 39.15603588357934);
        mMap.addMarker(new MarkerOptions().position(mazda).title("Mazda Service Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mazda));
        Toast.makeText(MapsActivity.this,"The Nearest Service Center" ,Toast.LENGTH_SHORT).show();

    } public void findferrari()
    {
        LatLng ferrari = new LatLng(21.575027565779507, 39.16785629260287);
        mMap.addMarker(new MarkerOptions().position(ferrari).title("Ferrari Service Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ferrari));
        Toast.makeText(MapsActivity.this,"The Nearest Service Center" ,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        client.connect();

        // Add a marker in Sydney and move the camera
        //  LatLng jeddah = new LatLng(21.485811, 39.192505);
        //  mMap.addMarker(new MarkerOptions().position(jeddah).title("Marker in Jeddah"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(jeddah));
    }

    @Override
    public void onLocationChanged(Location location) {

        if (location == null){
            Toast.makeText(getApplicationContext(),"Location is not found" ,Toast.LENGTH_SHORT).show();
        }
        else {
            LatLng latLngCurrent= new LatLng(location.getLatitude(),location.getLongitude());

            CameraUpdate update= CameraUpdateFactory.newLatLngZoom(latLngCurrent,15);
            // mMap.animateCamera(update);

            MarkerOptions options =new MarkerOptions();
            options.position(latLngCurrent);
            options.title("Current Location");
            mMap.addMarker(options);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        request = new LocationRequest().create();
        request.setInterval(1000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(client, request, this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
        locationRequest.setFastestInterval(500);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                .checkLocationSettings(builder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                try {
                    LocationSettingsResponse response =task.getResult(ApiException.class);
                    Toast.makeText(MapsActivity.this, "GPS IS ON", Toast.LENGTH_SHORT).show();
                } catch (ApiException e) {

                    switch (e.getStatusCode()){
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                            try {
                                ResolvableApiException resolvableApiException = (ResolvableApiException)e;
                                resolvableApiException.startResolutionForResult(MapsActivity.this ,Request_check_setting);
                            } catch (IntentSender.SendIntentException ex) {

                            }
                            break;

                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            break;
                    }
                }
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Request_check_setting){
            switch (requestCode){
                case Activity.RESULT_OK:
                    Toast.makeText(this, "GPS IS TURNED ON", Toast.LENGTH_SHORT).show();
                    break;
                case  Activity.RESULT_CANCELED:

                    Toast.makeText(this, "GPS IS REQUIRED TO BE ON", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}