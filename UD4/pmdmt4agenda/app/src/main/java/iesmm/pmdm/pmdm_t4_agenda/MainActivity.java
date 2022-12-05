package iesmm.pmdm.pmdm_t4_agenda;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME = "contactos.csv";
    private ArrayList<Contacto> contactos = new ArrayList<>();
    private ArrayAdapter<Contacto> adapter;

    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirmarPermisoLlamada();
        recuperarDatos();
        addItems(contactos);
    }

    private void recuperarDatos() {
        BufferedReader b = null;
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(this.openFileInput(FILENAME), "UTF-8");
            b = new BufferedReader(inputStreamReader);
            String linea = "";
            while ((linea = b.readLine()) != null) {
                if (!linea.isEmpty()) {
                    String[] cadena = linea.split(";");
                    contactos.add(new Contacto(cadena[0], cadena[1], cadena[2]));
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addItems(ArrayList contactos) {
        contactos.sort(Comparator.comparing(Contacto::getNombre)
                .thenComparing(Contacto::getNombre));
        ListView lista = this.findViewById(R.id.listView1);
        adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, contactos);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                muestraDialogo(adapter.getItem(i));
            }
        });

    }

    private void muestraDialogo(Contacto contacto) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Deseas realizar alguna acción?")
                .setCancelable(false)
                .setPositiveButton("Llamar al " + contacto.getTelefono(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        realizarLlamada(contacto.getTelefono());
                    }
                })
                .setNeutralButton("Enviar Mensaje", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        enviarMensaje();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }


    public void realizarLlamada(String telefono) {
        Uri uri = Uri.parse("tel:" + telefono);
        this.startActivity(new Intent(Intent.ACTION_CALL, uri));
    }

    public void enviarMensaje() {

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setPackage("com.whatsapp");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean confirmarPermisoLlamada() {
        boolean confirmado = false;

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            this.requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 0);
        } else
            confirmado = true;

        return confirmado;
    }
}