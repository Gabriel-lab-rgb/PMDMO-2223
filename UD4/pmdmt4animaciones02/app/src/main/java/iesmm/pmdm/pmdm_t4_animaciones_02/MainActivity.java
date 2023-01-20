package iesmm.pmdm.pmdm_t4_animaciones_02;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Animation.AnimationListener {

    private ImageView image;
    private Button boton;
    private AnimationDrawable animacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = this.findViewById(R.id.imageView);
        boton = this.findViewById(R.id.boton);

        image.setOnClickListener(this);
        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.imageView) {

            loadAnimacion(v, R.anim.rotacion);

        } else if (v.getId() == R.id.boton) {

            if (animacion != null && animacion.isRunning()) {
                animacion.stop();
            } else {
                image.setBackgroundResource(R.drawable.sprites);
                animacion = (AnimationDrawable) image.getBackground();
                animacion.start();

            }
        }

    }

    private void loadAnimacion(View v, int rotation) {
        Animation animacion=AnimationUtils.loadAnimation(this, rotation);
        animacion.setAnimationListener(this);
        v.startAnimation(animacion);
        //v.startAnimation(AnimationUtils.loadAnimation(this, rotation));
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Toast.makeText(this,"animacion iniciada",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Toast.makeText(this,"animacion finalizada",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}