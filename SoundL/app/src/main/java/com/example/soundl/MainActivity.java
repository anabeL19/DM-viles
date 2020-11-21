package com.example.soundl;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensor_mng;
    Sensor acelerometro;
    int latigo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        sensor_mng = (SensorManager) getSystemService(SENSOR_SERVICE);
        acelerometro = sensor_mng.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        System.out.println("giro x: " + x);
        if (x < -5 && latigo == 0) {
            latigo ++;
            getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
            //Toast.makeText(this, "giro x", Toast.LENGTH_LONG).show();
        } else if (x > 5 && latigo == 1) { //hacia la izq
            latigo ++;
            getWindow().getDecorView().setBackgroundColor(Color.BLUE);
        }

        if (latigo == 2) {
            Sound();
            latigo = 0;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    private void Sound() {
        MediaPlayer player = MediaPlayer.create(this, R.raw.whip);
        player.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensor_mng.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensor_mng.unregisterListener(this);
    }
}