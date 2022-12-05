package iesmm.pmdm.pmdm_t4_05;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Instanciación de componentes visuales para su control
        Button start = (Button) this.findViewById(R.id.button);

        // 2. Vinculamos el control del escuchador de eventos con el componente
        start.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                ActualizarProgressBar progress=new ActualizarProgressBar();
                progress.execute();
                break;
        }
    }


    /* Muestra un cuadro de diálogo con barra de progreso */
    private void showProgress() {
        // OTRA OPCIÓN: Método estático de inicio: ProgressDialog.show(this, titulo, mensaje);
        progress = new ProgressDialog(this);
        progress.setTitle("Descarga simulada"); // Titulo
        progress.setMessage("Cargando"); // Contenido

        // Propiedades de configuración
        progress.setMax(100); // Valor máximo de la barra de progreso
        progress.setCancelable(true); // Permitir que se cancele por el usuario

        // Tipo de barra de progreso: ProgressDialog.STYLE_SPINNER / STYLE_HORIZONTAL
         //progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        // Mostrar cuadro de diálogo
        progress.show();

        // Incrementar valor del progreso en n. unidades
        // Aumenta en 10 unidades

        // Obtener valor de la barra de progreso
       // progress.getProgress();

        // Obtener valor máximo de la barra de progreso
        // progress.getMax();

        // Finalización del cuadro de diálogo
        // progress.cancel();

    }


    private class ActualizarProgressBar extends AsyncTask<Void, Integer, Void> {

        private final String TAG="PMDM";
        private final int DELAY=1000;

        /*
        onPreExecute:
        Método llamado antes de empezar el procesamiento en segundo plano de doInBackground.
         */
        @Override
        protected void onPreExecute() {
            Log.i(TAG,"Se inicia la tarea asincrona");
            showProgress();
        }

        /*
        doInBackground:
        este método se ejecuta en un thread separado, lo que le permite ejecutar un tratamiento pesado en una tarea de segundo plano.
        Recibe como parámetros los declarados al llamar al método execute(Params).
         */
        @Override
        protected Void doInBackground(Void... params) {
            //GENERAR NÚMEROS ALEATORIOS EN UN NÚMERO INFINITO;
            Log.i(TAG,"se inicia doInBackground");
            // publishProgress(10);
            while (progress.getProgress()<progress.getMax()){
                publishProgress(10);

                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        /*
        onProgressUpdate:
        es llamado por publishProgress(), dentro de doInBackground(Params) (su uso es muy común para por ejemplo actualizar el porcentaje de un componente ProgressBar).
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG,"se actualiza ProgressBar");

            switch (progress.getProgress()){
                case 40:
                    progress.hide();
                    break;
                case 90:
                    progress.show();
                    break;
            }
            progress.incrementProgressBy(values[0]);
        }

        /*
        Este método es llamado tras finalizar doInBackground(Params).
        Recibe como parámetro el resultado devuelto por doInBackground(Params).
         */
        @Override
        protected void onPostExecute(Void param) {
            Log.i(TAG,"se finaliza la tarea asincrona");
            progress.cancel();
        }
    }
}