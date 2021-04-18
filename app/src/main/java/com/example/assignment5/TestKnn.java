package com.example.assignment5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Math.sqrt;

public class TestKnn extends AppCompatActivity {

    EditText k;
    TextView knnout;
    Button knnget;
    private WifiManager wifiManager;

    private  static  List<ScanResult> listofwifi;
    // WifiReciever wifiReciever;
    private ArrayList<String> arrayList = new ArrayList<>();
    private ArrayList<Integer> rssiarraylist = new ArrayList<>();
    private ArrayList<Integer> rssinewarraylist = new ArrayList<>();

    ArrayList<sensedata> alllist=new ArrayList<sensedata>();
    int roomval=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_knn);

        knnget=findViewById(R.id.buttontestknn);
        k=findViewById(R.id.editTextTextk);
        knnout=findViewById(R.id.textView2);
        Log.d("RUN","1");
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        Log.d("RUN","2");



        knnget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("RUN","3");
                registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
                registerReceiver(wifiReciever,new IntentFilter(WifiManager.RSSI_CHANGED_ACTION));
                Log.d("RUN","4");
              boolean test=  wifiManager.startScan();
              Log.d("RUN",test+"");

            }
        });
    }




    BroadcastReceiver wifiReciever= new BroadcastReceiver() {

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("RUN","inn");

            String action=intent.getAction();
            arrayList.clear();
            rssiarraylist.clear();

            ArrayList<Integer> l1 = new ArrayList<>();
            ArrayList<Integer> l2 = new ArrayList<>();

            Log.d("RUN","6");
            if(action.equals(wifiManager.SCAN_RESULTS_AVAILABLE_ACTION)){
                listofwifi = wifiManager.getScanResults();


                if(listofwifi.size()>0){

                    for (ScanResult scanResult : listofwifi) {
                        Log.d("RUN","enter");

                        arrayList.add(scanResult.SSID);
                        int val=wifiManager.calculateSignalLevel(scanResult.level,10);
                        rssinewarraylist.add(scanResult.level);

                        rssiarraylist.add(val);


                    }
                    Log.d("Run","first");
                    RoomDb db5 = RoomDb.getInstance(getApplicationContext());
                    ScanDao dao5 = db5.scanDao();
                    List<ScanData> list = dao5.getAllscannedValue();

                    for(int j=0;j<rssinewarraylist.size();j++){
                        Log.d("Run","entered");
                        for(int i=0;i<list.size();i++){
                            Log.d("Run","again");

                            if(arrayList.get(j).equals(list.get(i).getSSID())){

                                l1.add(list.get(i).getRoomNo());
                                Log.d("RUN","hey");

                                int x=rssinewarraylist.get(j);
                                int y=list.get(i).getRSSI();

                                int val1=x*x-y*y;
                                int absval=Math.abs(val1);
                                double diff= sqrt(absval);
                                l2.add((int) diff);

                                alllist.add(new sensedata(list.get(i).getRoomNo(), (int) diff));


                            }
                            }
                        }
                    }

                    //Log.d("RUN",alllist.toString());

                for(int i=0;i<l2.size();i++){
                    for(int j=i+1;j<l2.size();j++){
                        if(l2.get(i)>l2.get(j)){
                            Collections.swap(l2, i, j);
                            Collections.swap(l1, i, j);

                        }
                    }
                }

                String neighbour=k.getText().toString();
                if(!"".equals(neighbour)){
                    roomval=Integer.parseInt(neighbour);
                }

                ArrayList<Integer> l1k = new ArrayList<>();
                int counter=0;
                for(int i=0;i<l1.size();i++){

                    if(roomval==counter){
                        break;
                    }
                    else {
                        l1k.add(l1.get(i));
                        counter = counter + 1;
                    }


                }

                String room;
                Map<String, Long> counterMap = l1k.stream().collect(Collectors.groupingBy(Object::toString,Collectors.counting()));

                Map.Entry<String, Long> entry = counterMap .entrySet().iterator().next();
                room=entry.getKey();
                long x=entry.getValue();

                for (Map.Entry<String, Long> newentry : counterMap.entrySet()) {

                    Log.d("RUN",newentry.getKey() + "/" + newentry.getValue());
                    //System.out.println(entry.getKey() + "/" + entry.getValue());

                   // x=entry.getValue();
                    if(newentry.getValue()>x){
                        x=newentry.getValue();
                        room=entry.getKey();
                    }

                   // knnout.setText(entry.getKey());
                    break;
                }
                knnout.setText("ROOM Number is:"+room);





                }


            }


    };

}