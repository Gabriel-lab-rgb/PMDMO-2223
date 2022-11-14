package iesmm.pmdm.toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recuperarValores();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; /** true -> el menú ya está visible */
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {




        switch ( item.getItemId()){

           /* case R.id.acercaDe:
                Toast.makeText(this,"Acerca de...",Toast.LENGTH_LONG).show();*/
            case R.id.fondo_rojo:
                //almacenar configuracion local
               almacenarConfiguracion(Color.RED);
                break;
            default:
                Toast.makeText(this,item.getTitle().toString(),Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void almacenarConfiguracion(int color){


        ConstraintLayout layout = this.findViewById(R.id.MENU);
        layout.setBackgroundColor(color);

        SharedPreferences preferencias=this.getSharedPreferences("elspreferencias", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("color",String.valueOf(color));
        editor.commit();

    }

    private void recuperarValores() {

        SharedPreferences preferencias=this.getSharedPreferences("elspreferencias", Context.MODE_PRIVATE);
        String scolor=preferencias.getString("color",null);

        if(scolor!=null){
            ConstraintLayout layout =  this.findViewById(R.id.MENU);
            layout.setBackgroundColor(Integer.parseInt(scolor));

        }


    }


}