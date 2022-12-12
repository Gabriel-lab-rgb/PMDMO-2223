package iesmm.pmdm.pmdm_t4_simoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttons[]; // Botones
    private int colors[]; // Colores asociados
    private final int FACIL = 10;
    private int contador=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definición estática de botones y colores
        buttons = new Button[4];
        buttons[0] = this.findViewById(R.id.b1);
        buttons[1] = this.findViewById(R.id.b2);
        buttons[2] = this.findViewById(R.id.b3);
        buttons[3] = this.findViewById(R.id.b4);

        // Colores iniciales
        /*
        buttons[0].setBackgroundColor(Color.GREEN);
        buttons[1].setBackgroundColor(Color.RED);
        buttons[2].setBackgroundColor(Color.YELLOW);
        buttons[3].setBackgroundColor(Color.BLUE);
        */

        colors = new int[4];
        colors[0] = Color.GREEN;
        colors[1] = Color.RED;
        colors[2] = Color.YELLOW;
        colors[3] = Color.BLUE;

        Button go = (Button) this.findViewById(R.id.bCentral);
        go.setOnClickListener(this);


        // Inicio de tarea asíncrona
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bCentral:
                ActualizarColores p = new ActualizarColores();
                p.execute();
                contador++;
                TextView text=this.findViewById(R.id.countScore);
                text.setText("" +contador);
                break;
        }
    }


    private class ActualizarColores extends AsyncTask<Void, Integer, Void> {

        private final String TAG = "PMDM";
        private final int DELAY = 2000;
        private int i = 0;

        /*
        onPreExecute:
        Método llamado antes de empezar el procesamiento en segundo plano de doInBackground.
         */
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "Se inicia la tarea asincrona");

        }

        private int generarNumero() {
            return (int) (Math.random() * buttons.length );
        }

        ;

        /*
        doInBackground:
        este método se ejecuta en un thread separado, lo que le permite ejecutar un tratamiento pesado en una tarea de segundo plano.
        Recibe como parámetros los declarados al llamar al método execute(Params).
         */
        @Override
        protected Void doInBackground(Void... params) {
            //GENERAR NÚMEROS ALEATORIOS EN UN NÚMERO INFINITO;
            Log.i(TAG, "se inicia doInBackground");

            while (i < FACIL) {
                publishProgress(generarNumero());
                try {
                    Thread.sleep(DELAY);
                    publishProgress(4);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                i++;
            }

            return null;
        }

        /*
        onProgressUpdate:
        es llamado por publishProgress(), dentro de doInBackground(Params) (su uso es muy común para por ejemplo actualizar el porcentaje de un componente ProgressBar).
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "se actualiza botones");

            switch (values[0]) {
                case 0:
                case 1:
                case 2:
                case 3:
                    buttons[values[0]].setBackgroundColor(colors[values[0]]);
                    Toast.makeText(getApplicationContext(),""+ values[0], Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    for (int i = 0; i < buttons.length; i++) {
                        buttons[i].setBackgroundColor(Color.BLACK);
                    }
                    break;

            }


        }

        /*
        Este método es llamado tras finalizar doInBackground(Params).
        Recibe como parámetro el resultado devuelto por doInBackground(Params).
         */
        @Override
        protected void onPostExecute(Void param) {
            Log.i(TAG, "se finaliza la tarea asincrona");
            Toast.makeText(getApplicationContext(), "Fin del juego", Toast.LENGTH_SHORT).show();

        }
    }
}