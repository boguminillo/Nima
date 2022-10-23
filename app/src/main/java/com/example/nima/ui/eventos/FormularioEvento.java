package com.example.nima.ui.eventos;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nima.data.model.Evento;
import com.example.nima.databinding.FragmentFormularioEventoBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioEvento extends Fragment {

    private FragmentFormularioEventoBinding binding;
    // esta variable se utilizara para comprobar si se ha editado el nombre
    String nombreOriginal = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFormularioEventoBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        EventoViewModel mViewModel = new ViewModelProvider(this).get(EventoViewModel.class);
        //formato que utilizaremos para mostrar la fecha
        SimpleDateFormat formato = EventoViewModel.FORMATO_FECHA;
        // observador que cargara los datos del cliente si es que se ha seleccionado uno
        mViewModel.getEvento().observe(getViewLifecycleOwner(), evento -> {
            if (evento != null) {
                binding.idNombre.setText(evento.getNombre());
                binding.idDireccion.setText(evento.getDireccion());
                binding.idDescripcion.setText(evento.getDescripcion());
                binding.idFecha.setText(formato.format(evento.getFecha()));
                nombreOriginal = evento.getNombre();
                binding.btnBorrar.setVisibility(View.VISIBLE);
            }
        });
        // funcion al pulsar el campo de fecha
        binding.idFecha.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
            datePickerDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
                // es necesario restar 1900 porque las fechas empiezan a contar en ese año
                Date fecha = new Date(year - 1900, month, dayOfMonth);
                binding.idFecha.setText(formato.format(fecha));
            });
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show();
        });
        // funcion del boton de guardar
        binding.btnGuardar.setOnClickListener(v -> {
            Evento evento = new Evento();
            evento.setNombre(binding.idNombre.getText().toString());
            Date fecha = formato.parse(binding.idFecha.getText().toString(), new java.text.ParsePosition(0));
            evento.setFecha(fecha);
            evento.setDireccion(binding.idDireccion.getText().toString());
            evento.setDescripcion(binding.idDescripcion.getText().toString());
            EventoViewModel.addUpdateEvento(evento);
            // si se ha editado el nombre se elimina el contacto con el nombre original
            if (!nombreOriginal.equals("") && !nombreOriginal.equals(evento.getNombre())) {
                EventoViewModel.deleteEvento(nombreOriginal);
            }
            requireActivity().onBackPressed();
        });
        // funcion del boton de borrar
        binding.btnBorrar.setOnClickListener(v -> {
            EventoViewModel.deleteEvento(nombreOriginal);
            requireActivity().onBackPressed();
        });
        // funcion del boton de cancelar
        binding.btnCancelar.setOnClickListener(v -> requireActivity().onBackPressed());
        return vista;
    }

}