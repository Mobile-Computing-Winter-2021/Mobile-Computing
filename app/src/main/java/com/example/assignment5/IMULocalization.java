package com.example.assignment5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IMULocalization extends AppCompatActivity implements SensorEventListener {

    Switch data;

    Button collect,locate;
    TextView result;

    List<com.example.assignment5.IMUData> imuDataList=new ArrayList<>();
    private SensorManager sensorManager;

    Sensor accelerometer, gyroscope,magnetometer,stepcounter,stepdetector;
    Float xacc,yacc,zacc,xgyr,ygyr,zgyr,xmag,ymag,zmag;
    float step_count=0;

    float speed= 0;
    String Direction;
    int RoomNo;

    Boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_m_u_localization);

        data=findViewById(R.id.switch1);
        collect=findViewById(R.id.cdata);
        locate=findViewById(R.id.TestRoom);
        result=findViewById(R.id.showtest);



        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        stepcounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        stepdetector=sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);


        //  smgps = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (accelerometer != null) {
                    sensorManager.registerListener(IMULocalization.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "accelerometer does not exist");
                }

                if (gyroscope != null) {
                    sensorManager.registerListener(IMULocalization.this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "gyroscope does not exist");
                }

                if (magnetometer != null) {
                    sensorManager.registerListener(IMULocalization.this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "magnetometer does not exist");
                }

                if (stepcounter != null) {
                    running=true;
                    sensorManager.registerListener(IMULocalization.this, stepcounter, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "stepcounter does not exist");
                }

                if (stepdetector != null) {
                    sensorManager.registerListener(IMULocalization.this, stepdetector, SensorManager.SENSOR_DELAY_NORMAL);
                } else {
                    Log.d("RUN", "stepdetector does not exist");
                }



            }
        });

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMUData imuData = new IMUData();
                RoomDb dbimu = RoomDb.getInstance(getApplicationContext());
                ScanDao daoimu = dbimu.scanDao();

                //dbimu.clearAllTables();

                imuData.setX_acc(xacc);
                imuData.setY_acc(yacc);
                imuData.setZ_acc(zacc);

                imuData.setX_gyr(xgyr);
                imuData.setY_gyr(ygyr);
                imuData.setZ_gyr(zgyr);

                imuData.setX_mag(xmag);
                imuData.setY_mag(ymag);
                imuData.setZ_mag(zmag);

                imuData.setSpeed(speed);
                imuData.setDirection("NA");

                imuData.setStepcount(step_count);
                if(step_count<10.0){
                    imuData.setRoom_no(1);
                }
              else if(step_count>=10.0 && step_count< 30.0){
                    imuData.setRoom_no(2);
                }
                else if(step_count>=30.0 && step_count< 50.0){
                    imuData.setRoom_no(2);
                }

                else {
                    imuData.setRoom_no(5);
                }

                daoimu.insertimu(imuData);

            }
        });

        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<Integer> limu1 = new ArrayList<>();
                ArrayList<Integer> limu2 = new ArrayList<>();
                RoomDb dbimu2 = RoomDb.getInstance(getApplicationContext());
                ScanDao daoimu2 = dbimu2.scanDao();
                List<IMUData> list = daoimu2.getAllimuValue();

                for(int i=0;i<list.size();i++){
                   int diff= (int) Math.abs(list.get(i).getStepcount()-step_count);
                   limu2.add(diff);
                   limu1.add(list.get(i).getRoom_no());
                }
                int index= limu2.indexOf(Collections.min(limu2));
                Log.d("RUN", String.valueOf(limu2.indexOf(Collections.min(limu2))));
                Log.d("Run", String.valueOf(limu1.get(index)));
                result.setText("Room Number is:"+ limu1.get(index));
            }
        });

    }
    @Override
    protected void onPause() {
        super.onPause();
        running=false;
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {

//        IMUData imuData = new IMUData();
//        RoomDb dbimu = RoomDb.getInstance(getApplicationContext());
//        ScanDao dao1 = dbimu.scanDao();

        if (data.isChecked()) {

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                xacc = event.values[0];
                yacc = event.values[1];
                zacc = event.values[2];
            }

            if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {

                xgyr = event.values[0];
               ygyr = event.values[1];
               zgyr = event.values[2];
            }

            if(event.sensor.getType()== Sensor.TYPE_MAGNETIC_FIELD){

                 xmag = event.values[0];
                ymag = event.values[1];
               zmag = event.values[2];
            }
            int detect=0;
            if(event.sensor.getType()==Sensor.TYPE_STEP_COUNTER){
                step_count=  event.values[0];
            }

        }
        else{
           Log.d("Run","Stop Collection");
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}