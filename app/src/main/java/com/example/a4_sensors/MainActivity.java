package com.example.a4_sensors;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static android.location.LocationManager.NETWORK_PROVIDER;

@SuppressLint("UseSwitchCompatOrMaterialCode")
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView xacc, yacc, zacc, xlinear, ylinear, zlinear, temp, lig, lat, lon, prox;
    Button accavg, lightavg, detectmotion;

    TextView xaccavg, yaccavg, zaccavg, lightaverage,gpsclick;


    // ToggleButton acc;
    SensorEventListener sensorEventListener;
    Switch acc, linear, tempswitch, lightswitch, gps, proxim;

    private SensorManager sensorManager;

    Sensor accelerometer, linear_acc, temperature, Light, GPS, Proximity;

    List<com.example.a4_sensors.Light> lightdata = new ArrayList<>();
    List<Accelerometer> accelerometers = new ArrayList<>();
    List<LinearAccelerator> linearAccelerators = new ArrayList<>();
    List<com.example.a4_sensors.Proximity> proximities = new ArrayList<>();
    List<Temperature> temperatures = new ArrayList<>();
    List<GPSLocation> locations = new ArrayList<>();

    LinearLayoutManager linearLayoutManager;

    RoomDb database;

    private LocationListener locationListener;


    private LocationManager smgps;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        detectmotion = findViewById(R.id.detect_motion);
        lightavg = findViewById(R.id.lightavg);

        xacc = findViewById(R.id.xacc);
        yacc = findViewById(R.id.yacc);
        zacc = findViewById(R.id.zacc);

        xlinear = findViewById(R.id.xlinear);
        ylinear = findViewById(R.id.ylinear);
        zlinear = findViewById(R.id.zlinear);

        temp = findViewById(R.id.temp);

        lig = findViewById(R.id.light);

        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);

        prox = findViewById(R.id.proximity);

        acc = findViewById(R.id.switch1);
        linear = findViewById(R.id.switchlinear);
        tempswitch = findViewById(R.id.switchtemp);
        lightswitch = findViewById(R.id.switchlight);
        gps = findViewById(R.id.switchgps);
        proxim = findViewById(R.id.switchprox);

        accavg = findViewById(R.id.accavg);
        lightavg = findViewById(R.id.lightavg);

        xaccavg = findViewById(R.id.xaccavg);
        yaccavg = findViewById(R.id.yaccavg);
        zaccavg = findViewById(R.id.zaccavg);
        lightaverage = findViewById(R.id.lavgval);
        gpsclick=findViewById(R.id.textView12);


        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        linear_acc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        // temperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        temperature = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        Light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);


        //  smgps = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        detectmotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetectMotionStatus.class);
                startActivity(intent);
            }
        });

        gpsclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(MainActivity.this, gpsvalue.class);
                startActivity(intent3);
            }
        });
        acc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (accelerometer != null) {
                    sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "accelerometer does not exist");
                }
            }
        });


        linear.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (linear_acc != null) {
                    sensorManager.registerListener(MainActivity.this, linear_acc, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "linear acceleration not supported");
                }


            }
        });


        lightswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (Light != null) {
                        sensorManager.registerListener(MainActivity.this, Light, SensorManager.SENSOR_DELAY_NORMAL);

                    } else {
                        Log.d("RUN", "light not supported");
                    }
                }
            }
        });

        tempswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    if (temperature != null) {
                        sensorManager.registerListener(MainActivity.this, temperature, SensorManager.SENSOR_DELAY_NORMAL);


                    } else {
                        Log.d("RUN", "temperature not supported");
                    }


                }
            }
        });
        proxim.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (Proximity != null) {
                    sensorManager.registerListener(MainActivity.this, Proximity, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "Proximity not supported");
                }
            }
        });

        smgps = (LocationManager) getSystemService(LOCATION_SERVICE);
        gps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (smgps.isProviderEnabled(NETWORK_PROVIDER) ) {
                    if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //        int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                        //return;
                    }
                    Log.d("GPS","hii");

                     smgps.requestLocationUpdates(NETWORK_PROVIDER, 0, 0, new LocationListener() {

                         @Override

                         public void onLocationChanged(@NonNull Location location) {
                             if(gps.isChecked()){
                                 lat.setText(""+location.getLatitude());
                                 lon.setText(""+location.getLongitude());
                             }
                             Log.d("GPS","HII");
                             Log.d("GPS",""+location.getLongitude());

                             GPSLocation location1 = new GPSLocation();
                             // light.setLight_value("" + lig.getText());
                             location1.setLatitude(""+lat.getText());
                             location1.setLongitude(""+lon.getText());

                             RoomDb db9 = RoomDb.getInstance(getApplicationContext());
                             SensorDao dao9 = db9.sensorDao();
                             dao9.insertLocation(location1);
                         }
                     });
                  /*   if(smgps.isProviderEnabled(GPS_PROVIDER)){
                         smgps.requestLocationUpdates(GPS_PROVIDER, 0, 0, new LocationListener() {

                             @Override

                             public void onLocationChanged(@NonNull Location location) {
                                 if(gps.isChecked()){
                                     lat.setText(""+location.getLatitude());
                                     lon.setText(""+location.getLongitude());
                                 }
                                 Log.d("RUN","HII");
                                 Log.d("RUN",""+location.getLongitude());

                                 GPSLocation location1 = new GPSLocation();
                                 // light.setLight_value("" + lig.getText());
                                 location1.setLatitude(""+lat.getText());
                                 location1.setLongitude(""+lon.getText());

                                 RoomDb db9 = RoomDb.getInstance(getApplicationContext());
                                 SensorDao dao9 = db9.sensorDao();
                                 dao9.insertLocation(location1);


                             }
                         });
                     }*/


                    // ;

                }
            }
        });

        accavg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter_acc = 0;
                float xsum = 0;
                float ysum = 0;
                float zsum = 0;


                RoomDb db6 = RoomDb.getInstance(getApplicationContext());
                SensorDao dao6 = db6.sensorDao();
                List<Accelerometer> list = dao6.getAllAccValue();
                //1 min=60 ms
                long time = System.currentTimeMillis() / 60000;
                for (int i = 0; i < list.size() && (time-list.get(i).getTime() <= 60); i++) {
                    //comparing less than 60mins i.e. 1hr

                        xsum += list.get(i).getX_acc();
                        ysum += list.get(i).getY_acc();
                        zsum += list.get(i).getZ_acc();
                        counter_acc += 1;

                    Log.d("RUN", xsum + "  " + ysum + " " + zsum);
                }
                Log.d("RUN", xsum + "  " + ysum + " " + zsum);

                xaccavg.setText("" + xsum / counter_acc);
                yaccavg.setText("" + ysum / counter_acc);
                zaccavg.setText("" + zsum / counter_acc);

            }
        });
        lightavg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int counter_light = 0;
                float lightsum = 0;


                RoomDb db7 = RoomDb.getInstance(getApplicationContext());
                SensorDao dao6 = db7.sensorDao();
                List<com.example.a4_sensors.Light> list = dao6.getAllLightValue();
                long time = System.currentTimeMillis() / 60000;
                for (int i = 0; i < list.size() && (time-list.get(i).getTime() <= 60); i++) {

                        lightsum += list.get(i).getLight_value();

                        counter_light += 1;

                }

                lightaverage.setText("" + lightsum / counter_light);


            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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

                    smgps.requestLocationUpdates(String.valueOf(MainActivity.this), 0, 0, locationListener);
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (acc.isChecked()) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {


                Accelerometer accelerometer1 = new Accelerometer();
                RoomDb db1 = RoomDb.getInstance(getApplicationContext());
                SensorDao dao1 = db1.sensorDao();
                xacc.setText(""+ event.values[0]);
                accelerometer1.setX_acc(event.values[0]);

                yacc.setText( ""+event.values[1]);
                accelerometer1.setY_acc(event.values[1]);

                zacc.setText(""+ event.values[2]);
                accelerometer1.setZ_acc(event.values[2]);
                accelerometer1.setTime(event.timestamp);

                dao1.insertacc(accelerometer1);
            }
        }
        else{
            xacc.setText("not running");
            yacc.setText("not running");
            zacc.setText("not running");
        }

        if (linear.isChecked()) {
            if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {


                LinearAccelerator linearAccelerator = new LinearAccelerator();
                RoomDb db2 = RoomDb.getInstance(getApplicationContext());
                SensorDao dao2 = db2.sensorDao();

                xlinear.setText("X: " + event.values[0]);
                linearAccelerator.setX_linear("" + xlinear.getText());

                ylinear.setText("Y: " + event.values[1]);
                linearAccelerator.setY_linear("" + ylinear.getText());

                zlinear.setText("Z: " + event.values[2]);
                linearAccelerator.setZ_linear("" + zlinear.getText());

                dao2.insertLinearAcc(linearAccelerator);
            }
        }
        else{
            xlinear.setText("not running");
            ylinear.setText("not running");
            zlinear.setText("not running");
        }

        if (lightswitch.isChecked()) {
            if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
                lig.setText("" + event.values[0]);

                com.example.a4_sensors.Light light = new Light();
               // light.setLight_value("" + lig.getText());
                light.setLight_value(event.values[0]);
                light.setTime(event.timestamp);
                RoomDb db = RoomDb.getInstance(getApplicationContext());
                SensorDao dao = db.sensorDao();
                dao.insertLight(light);

            }
        }
        else{
            lig.setText("not running");
        }

        if (tempswitch.isChecked()) {
            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                temp.setText(" " + event.values[0]+ " \n"
                        + event.values[1]+ " \n"
                        + event.values[2]);

                Temperature temperature = new Temperature();
                // light.setLight_value("" + lig.getText());
                temperature.setTempValue(""+ temp.getText());

                RoomDb db10 = RoomDb.getInstance(getApplicationContext());
                SensorDao dao10 = db10.sensorDao();
                dao10.insertTemperature(temperature);
            }
        }
        else{
            temp.setText("not running");
        }
        if (proxim.isChecked()) {
            if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {

                com.example.a4_sensors.Proximity proximity = new Proximity();
                RoomDb db3 = RoomDb.getInstance(getApplicationContext());
                SensorDao dao3 = db3.sensorDao();

                if (event.values[0] == 0) {
                    prox.setText("near");
                    proximity.setProximity_Value("" + prox.getText());
                } else {
                    prox.setText("AWAY");
                    proximity.setProximity_Value("" + prox.getText());
                }

                dao3.insertProximity(proximity);


            }
        }
        else{
            prox.setText("not running");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {



    }



   /* @Override
    public void onLocationChanged(Location location) {
        if(gps.isChecked()){

            lat.setText(""+location.getLatitude());
            lon.setText(""+location.getLongitude());
        }
    }*/
}







