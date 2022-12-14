package iesmm.pmdm.pmdm_t4_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Cuenta> cuentas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        leerUsuarios();

        Button b = this.findViewById(R.id.boton_iniciar_sesion);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view,"Ha pulsado aquí",Snackbar.LENGTH_LONG).show();*/
                //comprobarlo introduci do en los campos
                String correo = ((TextView) findViewById(R.id.input_usuario)).getText().toString();
                String password = ((TextView) findViewById(R.id.input_contrasena)).getText().toString();

                if (getAccess(correo, password)) {

                    Bundle bundle = new Bundle();
                    Cuenta cuenta = obtenerCuenta(correo);
                    if (cuenta != null) {
                        bundle.putString("usuario", cuenta.getUsuario());
                        bundle.putString("email", cuenta.getEmail());
                        bundle.putString("telefono", cuenta.getTelefono());
                    }

                    //lanzamiento del Intent
                    Intent i = new Intent(getApplicationContext(), DetailActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                } else {
                    Snackbar.make(view, "Correo electronico o contraseña incorrectos", Snackbar.LENGTH_LONG).show();

                }

            }


        });
    }

    /**
     * Devuelve true si el email y password son correctos y si estan en el fichero
     *
     * @param correo
     * @param password
     * @return
     */
    private boolean getAccess(String correo, String password) {

        boolean correcto = false;
        for (int i = 0; i < cuentas.size(); i++) {

            if (cuentas.get(i).getEmail().equals(correo)) {
                if (cuentas.get(i).getContraseña().equals(password)) {
                    correcto = true;
                } else {
                    correcto = false;
                }
            }

        }
        return correcto;
    }

    private Cuenta obtenerCuenta(String correo) {
        Cuenta cuenta = null;
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getEmail().equals(correo)) {
                cuenta = cuentas.get(i);
            }
        }
        return cuenta;
    }

    private void leerUsuarios() {

        BufferedReader b = null;
        try {

            InputStreamReader inputStreamReader = new InputStreamReader(this.openFileInput("users.csv"), "UTF-8");
            b = new BufferedReader(inputStreamReader);
            String linea = "";
            String[] array;
            while ((linea = b.readLine()) != null) {
                array = linea.split(":");
                cuentas.add(new Cuenta(array[0], array[1], array[2], array[3]));
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}