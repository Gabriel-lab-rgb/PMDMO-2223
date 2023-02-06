package iesmm.pmdm.googlemaps;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import iesmm.pmdm.googlemaps.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
  /*  private  Geocoder code;
    private List<Address> d;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng huelva = new LatLng(37.2708679,-6.9396903);
        LatLng sevilla=new LatLng(37.3754338,-5.9900776);
        LatLng cadiz=new LatLng(36.5181892,-6.3075344);
        mMap.addMarker(new MarkerOptions().position(huelva).title("huelva"));
        mMap.addMarker(new MarkerOptions().position(sevilla).title("sevilla"));
        mMap.addMarker(new MarkerOptions().position(cadiz).title("cadiz"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sevilla));
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerClickListener(this);
        //mMap.setOnMapClickListener(this);

    }



    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
       /* Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }*/

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        mMap.addMarker(new MarkerOptions().position(latLng));



/*
        Geocoder geocoder = new Geocoder(this);
        // 1. Obtenemos el objeto Address según la psoición marcada
        try {
            List<Address> direcciones = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            // 2. Reolución del objeto Address: calle, ciudad, código postal...
            Address direccion = direcciones.get(0);
            String pais = direccion.getCountryCode();
            String ciudad = direccion.getLocality();
            String calle = direccion.getAddressLine(0);

            Toast.makeText(this,"PAIS:" + pais + "CIUDAD: " + ciudad + "CALLE: " + calle,Toast.LENGTH_LONG);
        } catch (IOException e) {
            Toast.makeText(this, "Posición incorrecta calculada", Toast.LENGTH_SHORT).show();
        }*/
    }
}