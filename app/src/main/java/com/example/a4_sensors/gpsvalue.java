package com.example.a4_sensors;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.location.LocationManager.NETWORK_PROVIDER;

public class gpsvalue extends AppCompatActivity implements LocationListener {

    TextView latnew, longnew;
    Switch gpsnew;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsvalue);

        latnew = findViewById(R.id.latnew);
        longnew = findViewById(R.id.lonnew);
        gpsnew = findViewById(R.id.switchgpnew);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        gpsnew.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(gpsvalue.this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    return;
                }
                locationManager.requestLocationUpdates(NETWORK_PROVIDER, 0, 0, gpsvalue.this);
            }
        });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        if(gpsnew.isChecked()){
            Log.d("Run","HII");
            latnew.setText(location.getLatitude()+"");
            longnew.setText(location.getLongitude()+"");
        }
        else{
            latnew.setText("notRunning");
            longnew.setText("Not running");
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}