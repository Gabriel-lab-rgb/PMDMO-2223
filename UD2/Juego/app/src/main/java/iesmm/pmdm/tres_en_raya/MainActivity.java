package iesmm.pmdm.tres_en_raya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private JuegoTresEnRaya mJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // EJECUCIÓN INICIAL DE LA LÓGICA DEL VIDEOJUEGO
        mJuego =new JuegoTresEnRaya();
    }
}