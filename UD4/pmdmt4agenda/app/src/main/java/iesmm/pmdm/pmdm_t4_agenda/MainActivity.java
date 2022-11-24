package iesmm.pmdm.pmdm_t4_agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String FILENAME  ="contactos.csv";
    private ArrayList<Contacto>contactos=new ArrayList<>();
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recuperarDatos();
        addItems(contactos);
    }

    private void recuperarDatos() {
        try {

                BufferedReader buffer=new BufferedReader(new FileReader(String.valueOf(this.openFileInput(FILENAME))));
                String linea="";
                while(buffer.readLine()!=null){
                    if(!linea.isEmpty()){
                        String[] cadena= linea.split(";");
                        contactos.add(new Contacto(cadena[0],cadena[1],cadena[2]));

                    }
                }


        } catch (FileNotFoundException e) {
            e.printStackTrace();

    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addItems(ArrayList contactos){
        ListView lista=this.findViewById(R.id.listView1);
        adapter =new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1,contactos);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               /* muestraDialogo(adapter.getItem(i));*/
            }
        });

    }
}