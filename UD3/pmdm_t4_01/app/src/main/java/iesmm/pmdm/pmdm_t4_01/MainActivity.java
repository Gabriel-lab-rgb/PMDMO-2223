package iesmm.pmdm.pmdm_t4_01;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import iesmm.pmdm.pmdm_t4_01.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements GestorApp{

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        confirmarPermisoLlamada();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){

            case R.id.create_marcador:
                abrirMarcadorllamada();
                break;
            case R.id.create_llamado_numero:
                macarLlamada("123");
                break;
            case R.id.create_navegador_web:
                abrirNavegador("google.es");
                break;
            case R.id.create_llamada:
                realizarLlamada("123");
                break;
            case R.id.create_sms:
                mandarSms("text de prueba");
                break;
            case R.id.create_sms_compartir:
                mandarSmsTo("text de prueba","123");
                break;

        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void mandarSms(String contenido) {
        Intent t=new Intent(Intent.ACTION_SEND);
        t.putExtra(Intent.EXTRA_TEXT,contenido);
        t.setType("text/plain");
        this.startActivity(t);

    }

    @Override
    public void mandarSmsTo(String contenido, String telefono) {
        Intent t=new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:"+telefono));
        t.putExtra("sms_body",contenido);
        this.startActivity(t);

    }

    @Override
    public void abrirMarcadorllamada() {
        this.startActivity(new Intent(Intent.ACTION_DIAL));
    }

    @Override
    public void macarLlamada(String telefono) {
        Intent t=new Intent(Intent.ACTION_DIAL);
        Uri uri= Uri.parse("tel:"+telefono);
        t.setData(uri);
        this.startActivity(t);
    }

    @Override
    public void realizarLlamada(String telefono) {
            Uri uri= Uri.parse("tel:"+telefono);
            this.startActivity(new Intent(Intent.ACTION_CALL,uri));
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private boolean confirmarPermisoLlamada() {
        boolean confirmado = false;

        if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            this.requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 0);
        }
        else
            confirmado = true;

        return confirmado;
    }

    public void abrirNavegador(String url){
       Intent t  =new Intent(Intent.ACTION_VIEW);
       Uri uri=Uri.parse(url);
       t.setData(uri);
       this.startActivity(t);
       //this.startActivity(new Intent(Intent.ACTION_DIAL,uri));

    }
}