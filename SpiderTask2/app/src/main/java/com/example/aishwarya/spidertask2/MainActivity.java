package com.example.aishwarya.spidertask2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    private ViewFlipper mViewFlipper;
    public int seconds = 0;
    public int minutes = 0;
    Timer t = new Timer();
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    int a;
    MediaPlayer mediaPlayer;
    Button enable,disable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.main_timer_text);
        tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
        enable=(Button)findViewById(R.id.en);
        disable=(Button)findViewById(R.id.dis) ;
        enable.setClickable(true);
        disable.setClickable(false);

        //handling the music spinner
        spinner=(Spinner)findViewById(R.id.music);
        adapter=(ArrayAdapter.createFromResource(this,R.array.Music,android.R.layout.simple_spinner_item));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                switch(position){
                    case 0:a=1;
                        break;
                    case 1:a=2;
                        break;
                    case 2:a=3;
                        break;
                    case 3:a=4;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
               a=0;
            }

        });



        //onClickListeners for the play and pause slideshow buttons

        findViewById(R.id.play).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewFlipper = (ViewFlipper)findViewById(R.id.view_flipper);
                //sets auto flipping
                mViewFlipper.setAutoStart(true);
                mViewFlipper.setFlipInterval(3000);
                mViewFlipper.setInAnimation(MainActivity.this, android.R.anim.slide_in_left); //use either the default slide animation in sdk or create your own ones
                mViewFlipper.setOutAnimation(MainActivity.this, android.R.anim.slide_out_right);
                mViewFlipper.startFlipping();

                try {
                    t.scheduleAtFixedRate(new TimerTask() {

                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {

                                @Override
                                public void run() {
                                    TextView tv = (TextView) findViewById(R.id.main_timer_text);
                                    tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));
                                    if (seconds != 60) {
                                        seconds += 1;
                                    }
                                    if (seconds == 60) {
                                        seconds = 0;
                                        minutes = minutes + 1;

                                    }
                                    tv.setText(String.valueOf(minutes) + ":" + String.valueOf(seconds));


                                }

                            });
                        }

                    }, 0, 1000);
                }
                catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewFlipper = (ViewFlipper)findViewById(R.id.view_flipper);
                //stop auto flipping
                mViewFlipper.stopFlipping();
                t.cancel();
                TextView tv = (TextView) findViewById(R.id.main_timer_text);
                tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
            }
        });


        //onClickListeners for the play and pause music buttons

        findViewById(R.id.playm).setOnClickListener(new View.OnClickListener() {
                                                       @Override
                                                       public void onClick(View v) {

                                                           Runnable x=new Runnable() {
                                                               @Override
                                                               public void run() {
                                                                   switch (a){
                                                                       case 1:
                                                                           track1();
                                                                           break;
                                                                       case 2:
                                                                           track2();
                                                                           break;
                                                                       case 3:
                                                                           track3();
                                                                           break;
                                                                       case 4:
                                                                           track4();
                                                                           break;
                                                                   }
                                                               }
                                                           };

                                                           Thread music=new Thread(x);
                                                           music.start();
                                                       }
                                                   });

        findViewById(R.id.stopm).setOnClickListener(new View.OnClickListener() {


                                                        @Override
                                                        public void onClick(View v) {

                                                            mediaPlayer.pause();

                                                        }
                                                    });


        //onClick Listeners for enable and disable button
        enable=(Button)findViewById(R.id.en);
        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                enable();
                enable.setClickable(false);
                disable.setClickable(true);
            }
        });

        disable=(Button)findViewById(R.id.dis);
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    disable();
               disable.setClickable(false);
               enable.setClickable(true);
            }
        });

    }

    public void track1(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music1);
        mediaPlayer.start();
    }
    public void track2(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music2);
        mediaPlayer.start();
    }
    public void track3(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music3);
        mediaPlayer.start();
    }
    public void track4(){
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music4);
        mediaPlayer.start();
    }


    //Handling the enable button
    public void enable(){

            enable.setText("ENABLE-ON");
            disable.setText("DISABLE-OFF");
            //using the sensor
            SensorManager sm;
            Sensor s;
            sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            s = sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
    }
    //Handling the disable button
    public void disable(){
            enable.setText("ENABLE-OFF");
            disable.setText("DISABLE-ON");

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        try {
            mViewFlipper.showNext();
        }
        catch (Exception e) {
            Toast.makeText(MainActivity.this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
