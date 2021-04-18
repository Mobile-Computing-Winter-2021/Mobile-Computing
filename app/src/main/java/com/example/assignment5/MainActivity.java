package com.example.assignment5;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity {
    final Context context = this;

    private ListView lwifi;
    private TextView showtest;
    private Button bwifi,showrssi,collect,testroom,knn,enter,imu;
    private WifiManager wifiManager;
    private  static  List<ScanResult>listofwifi;
   // WifiReciever wifiReciever;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<Integer> rssiarraylist = new ArrayList<>();
    private ArrayList<Integer> rssinewarraylist = new ArrayList<>();
    private ArrayAdapter adapter;

    EditText roomno;
    int edit;

    List<ScanData> locations = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDb database;
    int roomval=0;
    boolean clickedscan=false;
    boolean clicked=false;
    boolean clickedtest=false;

    ArrayList list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lwifi=findViewById(R.id.listofwifi);
        bwifi=findViewById(R.id.scanwifi);
        showrssi=findViewById(R.id.showrssi);
        collect=findViewById(R.id.collectdata);
//        roomno=findViewById(R.id.roomno);
        testroom=findViewById(R.id.test);
        showtest=findViewById(R.id.testview);
        knn=findViewById(R.id.buttonknn);
        enter=findViewById(R.id.enter);
        imu=findViewById(R.id.buttonindoorloc);
        ScanData scanData=new ScanData();

        RoomDb db9 = RoomDb.getInstance(getApplicationContext());
        // db3.clearAllTables();
        ScanDao dao9 = db9.scanDao();
        dao9.getAllscannedValue();

        imu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3=new Intent(MainActivity.this,IMULocalization.class);
                startActivity(intent3);
            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View promptsView = layoutInflater.inflate(R.layout.prompt, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);
                final EditText Input = (EditText) promptsView.findViewById(R.id.input);
                
                alertDialogBuilder.setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        edit= Integer.parseInt(Input.getText().toString());
                        Log.d("RUN",edit+"");
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });



        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        getWificonnection();
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }

        Log.d("RUN","1");
        //registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        knn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(MainActivity.this, TestKnn.class);
                startActivity(intent2);
            }
        });
        showrssi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, Plot.class);
                intent.putExtra("rssilist",rssiarraylist);
                intent.putExtra("aps",arrayList);
                startActivity(intent);
            }
        });

        bwifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedscan=true;
                registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                registerReceiver(wifiReciever,new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));

                wifiManager.startScan();
                Log.d("RUN","2");
               // Log.d("RUN",test+"");

            }
        });

        collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 clicked=true;


                registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                registerReceiver(wifiReciever,new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
                wifiManager.startScan();


            }
        });

        testroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedtest=true;
                Log.d("RUN","in1");
                registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                registerReceiver(wifiReciever,new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
                wifiManager.startScan();

            }
        });



    }


    private void getWificonnection() {

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
            if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this, "loction is off", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                Toast.makeText(MainActivity.this, "location is on", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "searching", Toast.LENGTH_SHORT).show();
        }
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(MainActivity.this, "permission granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(MainActivity.this, "permission denied", Toast.LENGTH_SHORT).show();
                return;
            }
            break;
        }
    }

    BroadcastReceiver wifiReciever= new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("RUN","inn");

            String action=intent.getAction();
            arrayList.clear();
            rssiarraylist.clear();

            ArrayList<Integer> l1 = new ArrayList<>();
            ArrayList<Integer> l2 = new ArrayList<>();
//            String text=roomno.getText().toString();
//            if(!"".equals(text)){
//                roomval=Integer.parseInt(text);
//            }

//            if(!"".equals(edit)){
//                roomval=Integer.parseInt(edit);
//            }

            roomval=edit;

            ScanData scanData=new ScanData();

            RoomDb db3 = RoomDb.getInstance(getApplicationContext());
         //   db3.clearAllTables();

            ScanDao dao3 = db3.scanDao();
//            dao3.getAllscannedValue();
            if(action.equals(wifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
               listofwifi = wifiManager.getScanResults();


                if(listofwifi.size()>0){

                    for (ScanResult scanResult :listofwifi) {
                        Log.d("RUN","enter");
                       // int i=scanResult.level;
                      //  Log.d("RUN",i+"");

                            if(scanResult.SSID!=""){
                            arrayList.add(scanResult.SSID);
                            int val = wifiManager.calculateSignalLevel(scanResult.level, 10);
                            rssinewarraylist.add(scanResult.level);

                            rssiarraylist.add(val);}


                        if(clicked) {
                            //Adding the values in the database
                            scanData.setRoomNo(roomval);
                            scanData.setSSID(scanResult.SSID);
                            scanData.setRSSI(scanResult.level);
                            dao3.insertscandata(scanData);
                        }

//                        if(clickedtest){
//                            RoomDb db4 = RoomDb.getInstance(getApplicationContext());
//                             ScanDao dao4 = db4.scanDao();
//                            List<ScanData> list = dao4.getAllscannedValue();
//
//
//                        }

                      //  Log.d("RUN",scanResult.)
                    }
                    clicked=false;
                    Log.d("RUN", String.valueOf(rssiarraylist.size()));

                    Log.d("RUN","exit");
                    if(clickedtest){
                        Log.d("RUN","testenter");
                        RoomDb db4 = RoomDb.getInstance(getApplicationContext());
                        ScanDao dao4 = db4.scanDao();
                        List<ScanData> list = dao4.getAllscannedValue();
                        Log.d("RUN", String.valueOf(list.size()));
//                        ArrayList<Integer> l1 = new ArrayList<>();
//                        ArrayList<Integer> l2 = new ArrayList<>();
                        Log.d("RUN","in2");
                        Log.d("RUN","in2");
                        for(int j=0;j<rssinewarraylist.size();j++){
                            Log.d("RUN","in3");
                            for(int i=0;i<list.size();i++){
                                Log.d("RUN","in3");
                               Log.d("RUN","ROOM NUMBER enter");
//                               Log.d("RUN",arrayList.get(j));
//                               Log.d("RUN",list.get(i).getSSID());
//                                Log.d("RUN", String.valueOf((arrayList.get(j).equals(list.get(i).getSSID()))));


                                    if (arrayList.get(j).equals(list.get(i).getSSID())) {

                                        l1.add(list.get(i).getRoomNo());
                                        int difference = abs((rssinewarraylist.get(j) - list.get(i).getRSSI()));
                                        l2.add(difference);

                                }
                            }
                        }
                        Log.d("RUN",l1.toString());
                        Log.d("RUN",l2.toString());
                        Log.d("RUN",l2.get(0).toString());
                        int min = l2.get(0);
                        for (int i : l2){
                            min = min < i ? min : i;
                        }
                       int index= l2.indexOf(Collections.min(l2));
                        Log.d("RUN", String.valueOf(l2.indexOf(Collections.min(l2))));
                        showtest.setText("Room Number is:"+ l1.get(index));

                        clickedtest=false;


                    }

                    if(clickedscan){
                    ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1,arrayList);
                    lwifi.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();
                    clickedscan=false;}
                }
            }

        }
    };
}

