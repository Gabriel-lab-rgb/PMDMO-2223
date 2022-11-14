package iesmm.pmdm.pmdm_t3_listview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private final String letrasDni="TRWAGMYFPDXBNJZSQVHLCKE";
    private final String FILENAME="clientes-";
    ArrayList cadenas=new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void clearItems(View view) {

       escribirFichero();
       adapter.clear();
    }

public boolean comprobarDni(String dni){
         boolean correcto;
        String intDni=dni.substring(0,8);
    if (isNumeric(intDni) == true) {
        int resto=Integer.parseInt(intDni) % 23;
        char letraDni=dni.charAt(8);

        if( letrasDni.charAt(resto)== letraDni){
            if(repetido(dni)==false){
                correcto=true;
            }else{
                Toast.makeText(this,"El dni introducido ya existe",Toast.LENGTH_LONG).show();
                correcto=false;
            }
        }else{
            Toast.makeText(this,"El dni introducido es incorrecto",Toast.LENGTH_LONG).show();
            correcto=false;
        }

    }else{
        correcto=false;
        Toast.makeText(this,"No puedes introducir otro carecter que no sea número",Toast.LENGTH_LONG).show();
    }
    return correcto;

}

    public void putItem(View view) {

        EditText text=this.findViewById(R.id.editText);

        if(text.getText().length()==9){

          if(comprobarDni(text.getText().toString())) {
              Toast.makeText(this, "correcto", Toast.LENGTH_LONG).show();
              cadenas.add(text.getText().toString());
              Collections.sort(cadenas);
              addItems(cadenas);
              text.setText("");
          }
        }else{
            Toast.makeText(this,"Introduce un dni valido",Toast.LENGTH_LONG).show();
        }


    }

    private void addItems(ArrayList cadenas){
        ListView lista=this.findViewById(R.id.listView1);
        adapter =new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,cadenas);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                muestraDialogo(adapter.getItem(i));
            }
        });

    }
    private void muestraDialogo(Object dni){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Deseas realizar alguna acción?")
                .setCancelable(false)
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        adapter.remove(dni);
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void escribirFichero() {

        File file=this.getExternalFilesDir(null);
        if(file.canWrite()){
            String date = DateTimeFormatter.ofPattern("ddMMyyyy").format(LocalDateTime.now());
            File f=new File(file,FILENAME +date + ".txt");
            Toast.makeText(getApplicationContext(), f.getAbsolutePath(),Toast.LENGTH_LONG).show();

            FileWriter fi= null;
            try {
                fi = new FileWriter(f);

                for(int i = 0; i< adapter.getCount(); i++){
                    fi.write(adapter.getItem(i).toString() + "\n");
                }
                fi.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }else{
            Toast.makeText(this,"Error al generar el fichero",Toast.LENGTH_LONG).show();
        }
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean repetido(String cadena) {
        boolean encontrado = false;

        for(int i = 0; i < cadenas.size(); i++) {
            if(cadenas.get(i).equals(cadena)) {
                encontrado=true;
            }
        }
        return encontrado;
    }
}