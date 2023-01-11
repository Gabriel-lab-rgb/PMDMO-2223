package com.example.pmdm_t4_sensores_02;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity implements SensorEventListener, View.OnClickListener {

    private SensorManager sensorManager; // Gestor de sensores
    private Sensor acelerometro; // Objeto sensor a medir
    private Sensor luxometro;
    private View layout;
    private TextView ejeX, ejeY, ejeZ, time;
    private Button start, stop;
    private final String LOGTAG = "PMDM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Objetos del layout
        start = this.findViewById(R.id.start);
        stop = this.findViewById(R.id.stop);
        ejeX = this.findViewById(R.id.ejeX);
        ejeY = this.findViewById(R.id.ejeY);
        ejeZ = this.findViewById(R.id.ejeZ);

        // Asociación de eventos escuchadores

        // Inicio por defecto
        stop();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start:
                start();
            case R.id.stop:
                stop();
        }

    }

    /* Informa el sensor de un valor nuevo recogido: Un objeto SensorEvent contiene información sobre los nuevos datos del sensor
    , por ejemplo, la exactitud de los datos, el sensor que generó los datos, la marca de tiempo en la que se generaron los datos
    y los nuevos datos que registró el sensor. */
    // Comprensión de unidades y valores: https://developer.android.com/guide/topics/sensors/sensors_motion?hl=es-419
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x, y, z;

        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            x = sensorEvent.values[0];
            ejeX.setText(String.valueOf(sensorEvent.values[0]));
            y = sensorEvent.values[1];
            ejeY.setText(String.valueOf(sensorEvent.values[1]));
            z = sensorEvent.values[2];
            ejeZ.setText(String.valueOf(sensorEvent.values[2]));
        }
    }

    /*
    Método para definir el cambio de exactitud y precisión de un sensor.
    La exactitud está representada por una de las cuatro constantes de estado:
    SENSOR_STATUS_ACCURACY_LOW, SENSOR_STATUS_ACCURACY_MEDIUM, SENSOR_STATUS_ACCURACY_HIGH o SENSOR_STATUS_UNRELIABLE.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /* Métodos de control de estado en la app para vincular y desvincular el escuchador del sensor */

    /* Cancela el registro de los objetos de escucha del sensor cuando se ha terminado de usar el sensor o cuando se detenga la actividad del sensor */
    @Override
    protected void onPause() {
        super.onPause();
        stop();
    }


    public void start() {
        // Obtener disponibilidad del sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        luxometro = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // Valorar disponibilidad del sensor
        if (acelerometro != null && luxometro != null) {
            sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager.registerListener(this, luxometro, SensorManager.SENSOR_DELAY_NORMAL);
        } else {

            Toast.makeText(this, "Sensor no disponible", Toast.LENGTH_LONG);
        }

    }

    public void stop() {
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }
    }

    public String formatearValor(float num) {
        return String.valueOf(Math.floor(num * 100) / 100);
    }

    public String hora() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}