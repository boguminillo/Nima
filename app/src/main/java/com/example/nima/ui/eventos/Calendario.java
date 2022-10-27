package com.example.nima.ui.eventos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import com.example.nima.R;
import com.example.nima.data.model.Evento;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Nullable;


public class Calendario extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_calendario, container, false);
        CalendarView calendarView = vista.findViewById(R.id.calendarView);
        ListView lvEventos = vista.findViewById(R.id.listViewEventos);
        EventoViewModel mViewModel = new ViewModelProvider(this).get(EventoViewModel.class);
        // funcion que al seleccionar una fecha pedira los eventos de esa fecha
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Para no utilizar el constructor deprecado utilizamos un formateador, no podemos utilizar el creado anteriormente porque no sabremos el formato que tiene
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatoBarra = new SimpleDateFormat("dd/MM/yyyy");
            Date fecha;
            try {
                fecha = formatoBarra.parse(dayOfMonth + "/" + (month + 1) + "/" + year);
            } catch (Exception e) {
                fecha = new Date();
            }
            EventoViewModel.getEventos(fecha);
        });
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
                    String formatoFecha = PreferenceManager.getDefaultSharedPreferences(getContext()).getString(getResources().getString(R.string.formato_fechas), "dd/MM/yyyy");
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
                    text1.setText(eventos.get(position).getNombre());
                    text2.setText(formato.format(eventos.get(position).getFecha()));
                    return view;
                }
            };
            lvEventos.setAdapter(adaptador);
        });
        return vista;
    }

    @Override
    public void onResume() {
        EventoViewModel.flushListaEventos();
        super.onResume();
    }

}