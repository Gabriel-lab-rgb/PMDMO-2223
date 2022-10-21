package com.example.pmdm_t2_xilofono;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech sintetizador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sintetizador = new TextToSpeech(this, this);
    }
/*metodo que responde a la accion del boton*/
    public void pulsaBoton(View view){

        switch (view.getId()){

            case R.id.button_c1:

                //lamar al sisntentizador
                speak("do");
                break;
            case R.id.button_d:
                speak("re");
                break;

            case R.id.button_e:
                speak("mi");
                break;

            case R.id.button_f:
                speak("fa");
                break;

            case R.id.button_g:
                speak("sol");
                break;

            case R.id.button_a:
                speak("la");
                break;
            case R.id.button_b:
                speak("si");
                break;

            case R.id.button_c2:
                speak("do");
                break;

            default:
                break;
        }




    }

    @Override
    public void onInit(int i) {
        sintetizador.setLanguage(Locale.getDefault());
        sintetizador.setPitch(1.5f);
        sintetizador.setSpeechRate(1);
    }

    public void speak(String text){
        sintetizador.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
}