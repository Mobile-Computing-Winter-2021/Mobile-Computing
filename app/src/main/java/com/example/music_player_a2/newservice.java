package com.example.music_player_a2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

public  class newservice extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    private MediaPlayer mediaPlayer;
    int[] songs= {
            R.raw.galliyan,R.raw.kalhonaho,R.raw.teri_mitti,R.raw.tum

    };

    private void play_music(final int next){
        if(next>=songs.length)return;
         mediaPlayer = MediaPlayer.create(this, songs[next]);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                play_music(next+1);
            }
        });
        mediaPlayer.start();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //p=MediaPlayer.create(this,R.raw.kalhonaho);
       // p.setLooping(true);
        //p.start();

            play_music(0);
        Toast.makeText(this,"playing music", Toast.LENGTH_LONG).show();
        String text="play music";
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel notificationChannel=new NotificationChannel("Channelid1","playing music", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(notificationChannel);

        }
        Intent ine=new Intent(this,MainActivity.class);
        Log.d("text",text);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,ine,0);
        Notification notification=new NotificationCompat.Builder(this,"Channelid1").setContentTitle("playing music")
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1,notification);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"music stopped", Toast.LENGTH_LONG).show();
        mediaPlayer.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
