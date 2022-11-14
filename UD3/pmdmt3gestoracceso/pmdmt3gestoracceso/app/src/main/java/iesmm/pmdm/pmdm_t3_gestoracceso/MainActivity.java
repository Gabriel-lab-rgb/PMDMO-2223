package iesmm.pmdm.pmdm_t3_gestoracceso;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MainActivity extends AppCompatActivity {

    private final String LONTAG ="PMDM";
    private final String FILENAME  ="acceso.dat";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registrarEstado("entrada");
        recuperarEstado();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onDestroy() {
        super.onDestroy();
        registrarEstado("salida");

    }

    private void recuperarEstado() {

        try {
            TableLayout tabla=this.findViewById(R.id.Tabla);
            DataInputStream f=new DataInputStream(this.openFileInput(FILENAME));

            while (f.available() > 0) {

                String k = f.readUTF();
                String[] partes = k.split(";");
                TableRow fila= crearFila(partes);
                tabla.addView(fila);
            }
            Log.i(LONTAG, "recuperado");
            f.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private TableRow crearFila(String[] array) {

        TableRow fila=new TableRow(this);

        for(int i=0;i< array.length;i++){
            TextView texto=new TextView(this);
            texto.setText(array[i]);
            texto.setGravity(Gravity.CENTER);
            fila.addView(texto);
        }

        return fila;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void registrarEstado(String entrada) {

        Log.i(LONTAG,"Se registra la entrada");
        try {

            String date =DateTimeFormatter.ofPattern("MMM dd yyyy").format(LocalDateTime.now());
            String time =DateTimeFormatter.ofPattern("hh:mm:ss a").format(LocalDateTime.now());

            DataOutputStream fentrada=new DataOutputStream(this.openFileOutput(FILENAME, Context.MODE_APPEND));
            //añadir al fichero
            fentrada.writeUTF(entrada + ";" + date + ";" + time + ";");
            fentrada.writeUTF("\n");

            //cierre del flujo
            fentrada.close();


        } catch (FileNotFoundException e) {
            Log.i(LONTAG,"Fichero no encontrado");
        }catch (IOException e){
            Log.i(LONTAG,"Error rn E/S");
        }catch (Exception e){
            Log.i(LONTAG,"Error general");
        }
    }

}