package iesmm.pmdm.pmdmo_t4_animaciones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView  image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);

        Animation animacion= AnimationUtils.loadAnimation(this, R.anim.anim4);
        image.startAnimation(animacion);
        image.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case  R.id.imageView:
                Animation animacion= AnimationUtils.loadAnimation(this, R.anim.anim2);
                image.startAnimation(animacion);
                break;
        }
    }
}