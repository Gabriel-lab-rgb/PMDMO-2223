package iesmm.pmdm.brujulapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {


    private TextView orientacion;
    private TextView punto;

    private ImageView image;
    // private TextView direct;
    private SensorManager sensorManager;
    private Sensor sensorMagnetico;

    private Sensor acelerometro;
    private final String LOGTAG = "PMDM";
    float[] mGravity = new float[3];
    float[] mGeomagnetic = new float[3];
    float c=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = this.findViewById(R.id.imageView);

        orientacion = (TextView) this.findViewById(R.id.Orientation);
        punto = (TextView) this.findViewById(R.id.punto);
        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            sensorMagnetico = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            // Log.i(LOGTAG, "Sensor acelerometro inicializado");
        } else {
            Toast.makeText(this, "El sensor magnetico no esta disponible ", Toast.LENGTH_SHORT);
        }

        if (sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null) {
            acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            // Log.i(LOGTAG, "Sensor acelerometro inicializado");
        } else {
            Toast.makeText(this, "El sensor acelerometro no esta disponible ", Toast.LENGTH_SHORT);
        }
        sensorManager.registerListener(this, sensorMagnetico, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, mGravity, 0, 3);

        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, mGeomagnetic, 0, 3);

        }
        if (mGravity != null && mGeomagnetic != null) {
            float[] R = new float[9];
            float[] I = new float[9];
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);
                float azimut = orientation[0]; // orientation contains: azimut, pitch and roll
                float azimuthInDegress = (float) (Math.toDegrees(azimut) + 360) % 360;
                orientacion.setText((int) azimuthInDegress + "º");

                if ((int) azimuthInDegress == 360 || (int) azimuthInDegress == 0) {
                    punto.setText("N");
                } else if ((int) azimuthInDegress < 360 && (int) azimuthInDegress > 270) {
                    punto.setText("NO");
                } else if ((int) azimuthInDegress == 270) {
                    punto.setText("O");
                } else if ((int) azimuthInDegress < 270 && (int) azimuthInDegress > 180) {
                    punto.setText("SO");
                } else if ((int) azimuthInDegress == 180) {
                    punto.setText("S");
                } else if ((int) azimuthInDegress < 180 && (int) azimuthInDegress > 90) {
                    punto.setText("SE");
                } else if ((int) azimuthInDegress == 90) {
                    punto.setText("E");
                } else if ((int) azimuthInDegress > 0 && (int) azimuthInDegress < 90) {
                    punto.setText("NE");
                }
                RotateAnimation rotate = new RotateAnimation(c,azimuthInDegress, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                rotate.setDuration(250);
                rotate.setFillAfter(true);
                image.startAnimation(rotate);
                c=azimuthInDegress;
                //image.animate().rotation((int) azimuthInDegress).setDuration(100).start();
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorMagnetico, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
    }
}