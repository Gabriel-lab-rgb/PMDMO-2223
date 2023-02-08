package iesmm.pmdm.socialdrivemm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    private int cont;
    private String telefonoUsuario;
    private String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = this.findViewById(R.id.boton_iniciar_sesion);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Comprobar lo introducido en los campos
                String usuario = ((TextView) findViewById(R.id.input_usuario)).getText().toString();
                String contraseña = ((TextView) findViewById(R.id.input_contrasena)).getText().toString();

                if(getAccess(usuario,contraseña)){
                    //Construccion del conjunto de datos a transmitir
                   /* Bundle bundle = new Bundle();
                    bundle.putString("bienvenido", usuario);*/


                    // Llamada del Intent
                    Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                    /*i.putExtras();*/
                    startActivity(i);
                } else {
                    Snackbar.make(view, "Error de conexion, el correo o la contraseña no son correctos", Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }


    private boolean getAccess(String email, String contraseña) {

        /*
        try {
            FileInputStream fin = this.openFileInput(FILENAME);
            DataInputStream dis = new DataInputStream(fin);


            // Leemos linea por linea el fichero y separamos con un split para averiguar los diferentes parametros
            while(dis.available()>0) {
                String linea = dis.readLine();
                String[] parts =  linea.split(":");

                String password = parts[1];
                String correo = parts[2];


                if (email.equalsIgnoreCase(correo) && contraseña.equalsIgnoreCase(password)) {
                    cont++;                                      //Lo hago con un contador porque si le doy el valor de true o false
                    telefonoUsuario= parts[3];                            // en esta parte, al estar recorriendo toda la lista solo obtendrá el valor de la ultima linea
                    nombreUsuario = parts[0];
                }else {

                }
            }

        }catch (Exception e){

        }

        if (cont == 1){
            acceso = true;
        } else {
            acceso = false;
        }
*/
        return true;
    }
}