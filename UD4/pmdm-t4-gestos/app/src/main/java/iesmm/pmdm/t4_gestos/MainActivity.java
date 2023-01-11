package iesmm.pmdm.t4_gestos;

import androidx.appcompat.app.AppCompatActivity;

import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/* Info:
https://developer.android.com/develop/ui/views/touch-and-input/gestures
 */
public class MainActivity extends AppCompatActivity implements View.OnTouchListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private GestureOverlayView touch; // Contenedor componente que procesa todo tipo de gestos
    private GestureDetector detectorGestos; // Detector de tipos de gestos
    private final String LOG_TAG = "PMDM";
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Búsqueda de los componentes en la vista
        touch = (GestureOverlayView) this.findViewById(R.id.gestureOverlayView);

        // 2. Inicializador del detector de gestos (además para que procese los double tap)
        detectorGestos = new GestureDetector(this, this);
        detectorGestos.setOnDoubleTapListener(this);

        // 3. Vinculación de los escuchadores a cada uno de los componentes
        touch.setOnTouchListener(this);

        image=this.findViewById(R.id.imageView);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        Toast.makeText(this, "metodo onDoubleTap", Toast.LENGTH_SHORT).show();
        Log.i(LOG_TAG, "onDoubleTap");
        Animation animacion= AnimationUtils.loadAnimation(this, R.anim.anim2);
        image.startAnimation(animacion);
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        Toast.makeText(this, "metodo onDoubleTapEvent", Toast.LENGTH_SHORT).show();
        Log.i(LOG_TAG, "onDoubleTapEvent");
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.i(LOG_TAG, "onDown");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.i(LOG_TAG, "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Toast.makeText(this, "metodo onSingleTapUp", Toast.LENGTH_SHORT).show();
        Log.i(LOG_TAG, "onsingleTapUp");
        Animation animacion= AnimationUtils.loadAnimation(this, R.anim.anim);
        image.startAnimation(animacion);
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Log.i(LOG_TAG, "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Toast.makeText(this, "metodo onLongPress", Toast.LENGTH_SHORT).show();
        Log.i(LOG_TAG, "onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Toast.makeText(this, "metodo onFling", Toast.LENGTH_SHORT).show();
        Log.i(LOG_TAG, "onFling");
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // Al producirse un toque se procesa el tipo de gesto
        this.detectorGestos.onTouchEvent(motionEvent);

        return false;
    }
}