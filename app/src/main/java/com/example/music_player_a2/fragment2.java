package com.example.music_player_a2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import static android.content.res.Resources.getSystem;
import static androidx.core.content.ContextCompat.getSystemService;

public class fragment2 extends Fragment {

    Button Download,play_new;
    String url="http://faculty.iiitd.ac.in/~mukulika/s1.mp3";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2,container,false);
        Download=  view.findViewById(R.id.button4);
        //play_new=view.findViewById(R.id.button5);


     /*   play_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    MediaPlayer mediaPlayer;

                    mediaPlayer.start();
                }

        });*/





        Download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckNetworkConnection()){
                    Toast.makeText(getActivity(),"Connected",Toast.LENGTH_SHORT).show();
                    new download().execute(url);

                }
                else{
                    Toast.makeText(getActivity(),"Not Connected",Toast.LENGTH_SHORT).show();
                }
            }
            public boolean CheckNetworkConnection(){
                try {
                    ConnectivityManager connectivityManager=(ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo networkInfo=null;
                    if(connectivityManager!=null){
                        networkInfo=connectivityManager.getActiveNetworkInfo();
                    }
                    return networkInfo!=null && networkInfo.isConnected();
                }catch (NullPointerException e){
                    return false;
                }

            }

            class download extends AsyncTask<String, Integer,String>{
                ProgressDialog p;

                @Override
                protected void onPreExecute() {
                    p=new ProgressDialog(getActivity());
                  p.setTitle("Download in progress...");
                    p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    p.setMax(100);
                    p.setProgress(0);
                    p.show();

                }
                String path=getActivity().getApplicationContext().getFilesDir().getAbsolutePath();
                @Override
                protected String doInBackground(String... file_url) {
                    int count;

                    int length=0;
                    try{

                        URL url=new URL(file_url[0]);
                        HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                        //conn.connect();
                        conn.setRequestMethod("GET");
                        conn.setDoOutput(true);
                        conn.connect();
                        length=conn.getContentLength();

                        File folder=new File(path);
                        if(!folder.exists()){
                            folder.mkdir();
                        }
                        File input_file=new File(folder,"downloaded.mp3");

                        InputStream ip=new BufferedInputStream(url.openStream(), 10240);

                       // OutputStream op=getActivity().openFileOutput("input_file",Context.MODE_PRIVATE);
                       OutputStream op=new FileOutputStream(input_file);
                        byte[] data = new byte[1024];
                        int  total = 0;
                        while ((count = ip.read(data)) != -1) {
                            op.write(data, 0, count);
                           int progress=(int) total*100/length;
                           publishProgress(progress);
                        }

                        ip.close();
                        //op.flush()
                        op.close();

                    } catch (Exception e) {
                        Log.e("Error: ", e.getMessage());}
                    return "Download Complete";
                    }

                @Override
                protected void onProgressUpdate(Integer... values) {
                    p.setProgress(values[0]);
                }

                @Override
                protected void onPostExecute(String s) {
                    p.hide();
                    Toast.makeText(getContext(), "Music Download complete.", Toast.LENGTH_SHORT).show();

                    
                }



                }


        });


        return view;
    }
    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(pb);
    }

    PowerBroadcast pb;

    public void onResume() {
        super.onResume();
        pb=new PowerBroadcast();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        getActivity().registerReceiver(pb,intentFilter);
    }

}

