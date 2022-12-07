package iesmm.pmdm.recyclerview_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPaisaje;
    private RecyclerViewAdapter adaptadorPaisaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPaisaje=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerViewPaisaje.setLayoutManager(new LinearLayoutManager(this));

        adaptadorPaisaje=new RecyclerViewAdapter(obtenerPaisaje());
        recyclerViewPaisaje.setAdapter(adaptadorPaisaje);

    }

    public List<PaisajeModelo> obtenerPaisaje(){
        List<PaisajeModelo> paisaje=new ArrayList<>();
        paisaje.add(new PaisajeModelo("España","sevilla",R.drawable.img1));
        paisaje.add(new PaisajeModelo("España","malaga",R.drawable.img2));
        paisaje.add(new PaisajeModelo("España","madrid",R.drawable.img3));
        paisaje.add(new PaisajeModelo("España","bilbao",R.drawable.img4));
        return  paisaje;
    }
}