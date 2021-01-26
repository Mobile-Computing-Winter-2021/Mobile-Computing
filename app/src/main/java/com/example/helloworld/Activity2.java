package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    TextView t;
    Button check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Activity2.class.getSimpleName(),"Activity2 onCreate()");

        setContentView(R.layout.activity_2);

        t=findViewById(R.id.textView);


        Intent i=getIntent();
        String result=i.getStringExtra("result");
        int value=i.getIntExtra("value",0);
        t.setText(result);
        Button back=findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2= new Intent();
                if(value==5){
                    i2.putExtra("val","you are safe!");
                }
                else{
                    i2.putExtra("val","you are not safe!");
                }
                setResult(RESULT_OK,i2);
                finish();
            }
        });


        check=findViewById(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(value==5){
                    Toast.makeText(Activity2.this,"you are safe",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Activity2.this,"you are not safe",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "State of Activity2 Changed from onCreate() to onStart()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getSimpleName(),"State of Activity2 Changed from onCreate() to onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "State of Activity2 Changed from onStart() to onResume()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getSimpleName(),"State of Activity2 Changed from onStart() to onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "State of Activity2 Changed from onResume() to onPause()", Toast.LENGTH_SHORT).show();
        Log.d(Activity2.class.getSimpleName(),"State of Activity2 Changed from onResume() to onPause()");
    }
    @Override
    protected void onStop() {
        Toast.makeText(this, "State of Activity2 Changed from onPause() to onStop()", Toast.LENGTH_SHORT).show();
        super.onStop();
        Log.d(Activity2.class.getSimpleName(),"State of Activity2 Changed from onPause() to onStop()");
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this,"State of Activity2 Changed from onStop() to onDestroy()", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        Log.d(Activity2.class.getSimpleName(),"State of Activity2 Changed from onStop() to onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "State of Activity2 Changed from v to onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(Activity2.class.getSimpleName(),"State of Activity2 Changed from v to onRestart()");

    }
}
