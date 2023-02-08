package iesmm.pmdm.mapsprueba;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MapsFragment extends Fragment implements GoogleMap.OnMapClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private BottomSheetDialog bottomSheetDialog;
    private View bottomSheetView;
    private Geocoder geocoder;
    private TextView pais;
    private TextView ciudad;
    private TextView calle;
    private ArrayList<MarkerOptions> marcadores;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        geocoder = new Geocoder(view.getContext());
        marcadores = new ArrayList<>();
        bottomSheetDialog = new BottomSheetDialog(view.getContext(), R.style.BottomSheetDialogTheme);
        bottomSheetView = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.layout_bootom_sheet, view.findViewById(R.id.bottomSheetContainer));
        pais = bottomSheetView.findViewById(R.id.textPais);
        ciudad = bottomSheetView.findViewById(R.id.textCiudad);
        calle = bottomSheetView.findViewById(R.id.textCalle);
        bottomSheetDialog.setContentView(bottomSheetView);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.setOnMapClickListener(MapsFragment.this::onMapClick);
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        MarkerOptions marker = new MarkerOptions().position(latLng);
        mMap.addMarker(marker);
        marcadores.add(marker);
        try {
            List<Address> direcciones = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
            Address direccion = direcciones.get(0);
            pais.setText(direccion.getCountryName());
            ciudad.setText(direccion.getLocality());
            calle.setText(direccion.getAddressLine(0));
            /*Toast.makeText(this,"PAIS:" + pais + "CIUDAD: " + ciudad + "CALLE: " + calle,Toast.LENGTH_LONG);*/

            bottomSheetView.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    marcadores.remove(marker);
                    bottomSheetDialog.dismiss();
                    mMap.clear();
                    for (MarkerOptions m : marcadores) {
                        mMap.addMarker(m);
                    }
                }
            });
            bottomSheetDialog.show();
        } catch (IOException e) {
            /* Toast.makeText(this, "Posición incorrecta calculada", Toast.LENGTH_SHORT).show();*/
        }


    }
}