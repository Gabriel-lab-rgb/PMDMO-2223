package iesmm.pmdm.brujulapp;

import static java.lang.Math.sqrt;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private TextView text;
   // private TextView direct;
    private SensorManager sensorManager;
    private Sensor giroscopio;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text=(TextView)this.findViewById(R.id.text);
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null){
            giroscopio = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
           // Log.i(LOGTAG, "Sensor acelerometro inicializado");
        }
        else {
            Toast.makeText(this, "El sensor de giroscopio no esta disponible", Toast.LENGTH_SHORT);
        }
        sensorManager.registerListener(this, giroscopio, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {

                // Axis of the rotation sample, not normalized yet.
                float axisX = event.values[0];
                float axisY = event.values[1];
                float axisZ = event.values[2];

                // Calculate the angular speed of the sample
                float omegaMagnitude = (float) sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);
                text.setText(String.valueOf(omegaMagnitude));

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}