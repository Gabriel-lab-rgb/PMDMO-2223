package com.example.pmdm_t4_sensores;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private final String LOGTAG = "PMDM";
    private Vibrator vibrator;
    private MediaPlayer music;
    private MediaPlayer efecto;
    private View layout;
    private TextView value;
    private SensorManager sensorManager;        //Gestor de sensores
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = this.findViewById(R.id.layout);
        value = this.findViewById(R.id.texto);
        //obtener la disponibilidad del sensor de proximidad
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        //Compruebo la existencia del sensor

        if (sensor != null) {
            //Opero con el sensor de proximidad
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
            //obtener el servicio vibrator
            vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
            //vibrator.vibrate(3000);

            //Patron de secuencias
            //valor 0:
            //valor 1: valor  es el momento de la vibracion
            //valor 2:el momento de silencio
            //valor 3:segundo momento de vibracion
            //valor 4:momento de silencio
            //long  secuencias[]={0,500,1000,1000,100,2000};
            //de forma indefinida 0 ,para que lo haga una vez -1;
            // vibrator.vibrate(secuencias,-1);
        } else {
            Toast.makeText(this, "Sensor no disponible", Toast.LENGTH_SHORT).show();
        }


    }

    //Estados de la aplicación en caso de que se pause o se reanude
    @Override
    protected void onPostResume() {
        super.onPostResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        music = MediaPlayer.create(this, R.raw.nature015);
        efecto=MediaPlayer.create(this,R.raw.caballo);
        music.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }


    //Informa del valor obtenido en la medición

    @SuppressLint("ResourceAsColor")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            Log.d(LOGTAG, String.valueOf("Medida obtenida" + sensorEvent.values[0]));

            float proximity = sensorEvent.values[0];

            if (proximity >= 0 && proximity <= 0.4) {
                long  secuencia[]={0,1000};
                vibrator.vibrate(secuencia,-1);
                value.setText("Alejate");
                layout.setBackgroundColor(R.color.red_oscuro);
                efecto.start();
            } else if (proximity > 0.4 && proximity <= 0.8) {
                value.setText("Acercate");
                layout.setBackgroundColor(R.color.red_claro);
            } else if (proximity > 0.8 && proximity <= 1.2) {
                value.setText("Acercate");
                layout.setBackgroundColor(R.color.naranja);
            } else if (proximity > 1.2) {
                value.setText("Acercate");
                layout.setBackgroundColor(R.color.amarillo);
            }
        }
    }

    //Informa el momento en el que cambia la precisión
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}