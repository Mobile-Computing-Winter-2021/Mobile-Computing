package com.example.a4_sensors;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetectMotionStatus extends AppCompatActivity  {

    private detect_accelerometer accelerometer;
    

    private TextView motion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect_motion_status);

        motion=findViewById(R.id.textViewmotion);

        accelerometer=new detect_accelerometer(this);


        accelerometer.setListener(new detect_accelerometer.Listener() {
            @Override
            public void onTranslation(float x, float y, float z) {


                if(x>1.0f){
                    motion.setText("Stationary");
                }
                else if(x<-1.0f){
                    motion.setText("Motion");
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        accelerometer.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        accelerometer.unregister();
    }
}