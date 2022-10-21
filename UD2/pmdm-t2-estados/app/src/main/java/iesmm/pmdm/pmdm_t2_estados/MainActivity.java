package iesmm.pmdm.pmdm_t2_estados;
import androidx.appcompat.app.AppCompatActivity;



import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;
import android.util.Log;
import android.speech.tts.TextToSpeech;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener{

    private final String TAG="PMDM";
    private int restarts =0;
    private int destroy =0;
    private int stop=0;
    private int pause =0;
    private int resume=0;
    private TextToSpeech sintetizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sintetizador = new TextToSpeech(this, this);
    }



    @Override
    protected void onRestart() {

        super.onRestart();
        restarts=restarts +1;
        Log.i(TAG,"La App se ha restaurado " + String.valueOf(restarts)+ "veces");
        showToast("La App se ha restaurado " + String.valueOf(restarts) + "veces");
        speak("La App se ha restaurado " + String.valueOf(restarts) + "veces");


        /*String[] palabras=this.getResources().getStringArray(R.array.palabras);
        for (String palabra: palabras) {
            Log.i(TAG,palabra);
        }*/
        /*showToast(this.getResources().getString(R.string.restart));*/

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        resume=resume +1;
        Log.i(TAG, "La App se a resumido" +String.valueOf(resume) + "veces");
        showToast("La App se a resumido" +String.valueOf(resume)+ "veces");
        speak("La App se a resumido" +String.valueOf(resume) + "veces");
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop=stop +1;
        Log.i(TAG, "La App se ha parado " + String.valueOf(stop) + "veces");
        showToast("La App se ha parado " + String.valueOf(stop) + "veces");
        speak("La App se ha parado " + String.valueOf(stop)  + "veces");
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause=pause +1;
        Log.i(TAG, "La App se ha pausado " + String.valueOf(pause) + "veces");
        showToast("La App se ha pausado " + String.valueOf(pause)  + "veces");
        speak("La App se ha pausado " + String.valueOf(pause) + "veces");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        destroy=destroy+1;
        Log.i(TAG,"La App se ha destruido " + String.valueOf(destroy)  + "veces");
        showToast("La App se ha destruido " + String.valueOf(destroy) + "veces");
        speak("La App se ha destruido " + String.valueOf(destroy) + "veces");
        sintetizador.shutdown();

        /*showToast(this.getResources().getString(R.string.destroy));*/

    }

    private void showToast(String mensaje){
        Context context= getApplicationContext();
        Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
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