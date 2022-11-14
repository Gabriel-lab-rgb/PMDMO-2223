package iesmm.pmdm.pmdm_t3_configapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /** true -> el menú ya está visible */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.reset_settings:
                try {
                    reestablecerConfiguracion();
                    Toast.makeText(this,"configuración restablecida con exito",Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(this,"Error al cargar los datos",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.save_settings:
                try{
                    almacenarConfiguracion();
                    Toast.makeText(this,"Datos almacenados con exito",Toast.LENGTH_LONG).show();
                }catch (Exception e){

                    Toast.makeText(this,"Error al almacenar los datos",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.load_settings:
                recuperarConfiguracion();
                Toast.makeText(this,"Datos recuperados con exito",Toast.LENGTH_LONG).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void recuperarConfiguracion() {
        SharedPreferences preferencias=this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String email=preferencias.getString("email",null);
        String contaseña=preferencias.getString("contraseña",null);
        TextView campo_email=this.findViewById(R.id.campo_email);
        campo_email.setText(email);
        TextView campo_contraseña=this.findViewById(R.id.campo_password);
        campo_contraseña.setText(contaseña);

    }

    private void almacenarConfiguracion() {

        int contador=0;
        TextView campo_email=this.findViewById(R.id.campo_email);
        TextView campo_contraseña=this.findViewById(R.id.campo_password);


        SharedPreferences preferencias=this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        if (preferencias.contains("contador") == true) {
            // Cogemos el valor de "Contador". Si no existe, devuelve 0
           contador= preferencias.getInt("contador",0);
           contador++;

           SharedPreferences.Editor editor=preferencias.edit();
           editor.putString("email",campo_email.getText().toString());
           editor.putString("contraseña",campo_contraseña.getText().toString());
           editor.putInt("contador",contador);
           editor.commit();
           Toast.makeText(this,"Has modificado la cuenta " + contador + " veces",Toast.LENGTH_LONG).show();

            }else{
            SharedPreferences.Editor editor=preferencias.edit();
            contador++;
            editor.putInt("contador",1);
            editor.putString("emailR",campo_email.getText().toString());
            editor.putString("contraseñaR",campo_contraseña.getText().toString());
            editor.putString("email",campo_email.getText().toString());
            editor.putString("contraseña",campo_contraseña.getText().toString());
            editor.commit();
            Toast.makeText(this,"Has modificado la cuenta " + contador + " veces",Toast.LENGTH_LONG).show();

            }
        campo_email.setText("");
        campo_contraseña.setText("");
    }

    private void reestablecerConfiguracion() {

        SharedPreferences preferencias=this.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String email=preferencias.getString("emailR",null);
        String contraseña=preferencias.getString("contraseñaR",null);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("email",email);
        editor.putString("contraseña",contraseña);
        editor.commit();
        TextView campo_email=this.findViewById(R.id.campo_email);
        campo_email.setText(email);
        TextView campo_contraseña=this.findViewById(R.id.campo_password);
        campo_contraseña.setText(contraseña);

    }


}