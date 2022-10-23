package com.example.nima.ui.eventos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.nima.R;
import com.example.nima.data.model.Evento;

import java.text.SimpleDateFormat;

import javax.annotation.Nullable;


public class ListaEventos extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        EventoViewModel.getEventos();
        View vista = inflater.inflate(R.layout.fragment_lista_eventos, container, false);
        ListView lvEventos = vista.findViewById(R.id.listViewEventos);
        EventoViewModel mViewModel = new ViewModelProvider(this).get(EventoViewModel.class);
        // funcion que al hacer click en un evento abrira el formulario para editarlo
        lvEventos.setOnItemClickListener((parent, view, position, id) -> {
            Evento evento = (Evento) parent.getItemAtPosition(position);
            NavController navController = Navigation.findNavController(view);
            EventoViewModel.setEvento(evento);
            navController.navigate(R.id.nav_formulario_evento);
        });
        // este observer se ejecuta cuando se carga la lista de nombres
        mViewModel.getLista().observe(getViewLifecycleOwner(), eventos -> {
            ArrayAdapter<Evento> adaptador = new ArrayAdapter<Evento>(getActivity(), android.R.layout.simple_list_item_2, android.R.id.text1, eventos) {
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView text1 = view.findViewById(android.R.id.text1);
                    TextView text2 = view.findViewById(android.R.id.text2);

                    //formato que utilizaremos para mostrar la fecha
                    SimpleDateFormat formato = EventoViewModel.FORMATO_FECHA;
                    text1.setText(eventos.get(position).getNombre());
                    text2.setText(formato.format(eventos.get(position).getFecha()));
                    return view;
                }
            };
            lvEventos.setAdapter(adaptador);
            });
            return vista;
        }

    }