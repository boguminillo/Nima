package com.example.nima.ui.eventos;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nima.R;
import com.example.nima.data.model.Evento;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

public class MapsFragment extends Fragment {

    private Marker marcador;
    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            EventoViewModel mViewModel = new ViewModelProvider(requireActivity()).get(EventoViewModel.class);
            EventoViewModel.getEventos();
            // observador para mover el mapa a la posicion del evento
            mViewModel.getPosicion().observe(getViewLifecycleOwner(), posicion -> {
                if (posicion != null) {
                    if (marcador != null) {
                        marcador.remove();
                    }
                    marcador = googleMap.addMarker(new MarkerOptions().position(posicion).title("Nueva posicion del evento"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 17));
                    EventoViewModel.flushPosicion();
                }
            });
            // observador para mostrar los eventos en el mapa
            mViewModel.getLista().observe(getViewLifecycleOwner(), lista -> {
                if (lista != null) {
                    for (Evento evento : lista) {
                        Geocoder geocoder = new Geocoder(getContext());
                        try {
                            Address direccion = geocoder.getFromLocationName(evento.getDireccion(), 1).get(0);
                            LatLng pos = new LatLng(direccion.getLatitude(), direccion.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(pos).title(evento.getNombre()));
                        } catch (IOException e) {
                            Toast.makeText(getContext(), "Error al obtener la direccion", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            // Evento para seleccionar nuevo lugar en el mapa
            googleMap.setOnMapLongClickListener(latLng -> {
                if (marcador != null) marcador.remove();
                marcador = googleMap.addMarker(new MarkerOptions().position(latLng).title("Nueva posicion del evento"));
                Geocoder geocoder = new Geocoder(getContext());
                String direccion;
                try {
                    direccion = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1).get(0).getAddressLine(0);
                    EventoViewModel.setDireccion(direccion);
                } catch (IOException e) {
                    Toast.makeText(getContext(), "Error al obtener la direccion", Toast.LENGTH_SHORT).show();
                }
            });
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    @Override
    public void onResume() {
        EventoViewModel.flushListaEventos();
        super.onResume();
    }
}