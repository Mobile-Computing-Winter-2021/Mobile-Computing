package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView t1;
    EditText e1;
    CheckBox c1,c2,c3,c4,c5;
    Button submit,clear;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(MainActivity.class.getSimpleName(),"Activity1 onCreate()");

        setContentView(R.layout.activity_main);

        e1=findViewById(R.id.name);
        t1=findViewById(R.id.re);
        c1=findViewById(R.id.checkBox1);
        c2=findViewById(R.id.checkBox2);
        c3=findViewById(R.id.checkBox3);
        c4=findViewById(R.id.checkBox4);
        c5=findViewById(R.id.checkBox5);
        submit=findViewById(R.id.submit);
        clear=findViewById(R.id.clear);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer result;
                result = new StringBuffer();
                int counter=0;

                if(c1.isChecked())
                {
                    result.append("\n\n wearing a mask when outside");
                    counter=counter+1;
                }
                if(c2.isChecked())
                {
                    result.append("\n\n washing hands frequently");
                    counter=counter+1;
                }
                if(c3.isChecked())
                {
                    result.append("\n \nmaintaining 2 feet distance\n");
                    counter=counter+1;

                }
                if(c4.isChecked())
                {
                    result.append("\n \ncovering nose and mouth while sneezing and coughing");
                    counter=counter+1;

                }
                if(c5.isChecked())
                {
                    result.append("\n \n taking health diets\n");
                    counter=counter+1;

                }

                String r=result.toString();
                Intent i=new Intent(MainActivity.this,Activity2.class);
                i.putExtra("result",r);
                i.putExtra("value",counter);
                //startActivity(i);
                startActivityForResult(i,1);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1.setText("");
                e1.setText("");
                if(c1.isChecked())
                    c1.toggle();
                if(c2.isChecked())
                    c2.toggle();
                if(c3.isChecked())
                    c3.toggle();
                if(c4.isChecked())
                    c4.toggle();
                if(c5.isChecked())
                    c5.toggle();


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            if(resultCode==RESULT_OK){

                String val=data.getStringExtra("val");
                t1.setText(val);
            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "State of Main Activity Changed from onCreate() to onStart()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getSimpleName(),"State of Main Activity Changed from onCreate() to onStart()");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "State of Main Activity Changed from onStart() to onResume()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getSimpleName(),"State of Main Activity Changed from onStart() to onResume()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "State of Main Activity Changed from onResume() to onPause()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getSimpleName(),"State of Main Activity Changed from onResume() to onPause()");
    }
    @Override
    protected void onStop() {
        Toast.makeText(this, "State of Main Activity Changed from onPause() to onStop()", Toast.LENGTH_SHORT).show();
        super.onStop();
        Log.d(MainActivity.class.getSimpleName(),"State of Main Activity Changed from onPause() to onStop()");
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this,"State of Main Activity Changed from onStop() to onDestroy()", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        Log.d(MainActivity.class.getSimpleName(),"State of Main Activity Changed from onStop() to onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "State of Main Activity Changed from v to onRestart()", Toast.LENGTH_SHORT).show();
        Log.d(MainActivity.class.getSimpleName(),"State of Main Activity Changed from v to onRestart()");

    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        final TextView text = (TextView)findViewById(R.id.re);
        CharSequence t2 = text.getText();
        outState.putCharSequence("Text", t2);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final TextView text = (TextView)findViewById(R.id.re);
        CharSequence t2 = savedInstanceState.getCharSequence("Text");
        text.setText(t2);
    }
}
