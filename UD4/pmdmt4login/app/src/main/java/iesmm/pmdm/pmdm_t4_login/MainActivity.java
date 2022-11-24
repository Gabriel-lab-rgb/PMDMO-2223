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

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b= this.findViewById(R.id.boton_iniciar_sesion);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view,"Ha pulsado aquí",Snackbar.LENGTH_LONG).show();*/
                //comprobarlo introduci do en los campos
                String correo= ((TextView)findViewById(R.id.input_usuario)).getText().toString();
                String password=((TextView)findViewById(R.id.input_contrasena)).getText().toString();
                leerUsuarios(view);
                if(getAccess(correo,password)){
                   Bundle bundle=new Bundle();
                   bundle.putString("email",correo);
                   //lanzamiento del Intent
                    Intent i=new Intent(getApplicationContext(),DetailActivity.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }else{
                    Snackbar.make(view,"Error de conexión",Snackbar.LENGTH_LONG).show();

                }

            }


        });
    }

    /**
     *
     *  Devuelve true si el email y password son correctos y si estan en el fichero
     * @param correo
     * @param password
     * @return
     */
    private boolean getAccess(String correo, String password) {

        return true;
    }

    private void leerUsuarios(View view){

        BufferedReader b= null;
        try {

            InputStreamReader inputStreamReader = new InputStreamReader( this.openFileInput("users.csv"),"UTF-8");
            b = new BufferedReader(inputStreamReader);
            String linea="";
            String[] array=new String[2];
            while ((linea=b.readLine())!=null){
                array=linea.split(":");

            }
            Snackbar.make(view,array[0],Snackbar.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}