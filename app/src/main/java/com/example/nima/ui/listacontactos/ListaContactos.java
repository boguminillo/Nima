package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.nima.R;
import com.example.nima.databinding.FragmentListaContactosBinding;

import java.util.ArrayList;

import javax.annotation.Nullable;


public class ListaContactos extends Fragment {

    public static ListaContactos newInstance() {
        return new ListaContactos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ContactoViewModel.getNombresClientes();
        View vista = inflater.inflate(R.layout.fragment_lista_contactos, container, false);
        ContactoViewModel mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
        // este observer se ejecuta cuando se actualiza la lista de nombres
        mViewModel.getNombres().observe(getViewLifecycleOwner(), contactos -> {
            ListView lvContactos = vista.findViewById(R.id.listViewContactos);
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>((Context) getActivity(), android.R.layout.simple_list_item_1, contactos);
            lvContactos.setAdapter(adaptador);
        });
        Button btnClienes = vista.findViewById(R.id.bntClientes);
        btnClienes.setOnClickListener(v -> ContactoViewModel.getNombresClientes());
        Button btnProveedores = vista.findViewById(R.id.btnProveedores);
        btnProveedores.setOnClickListener(v -> ContactoViewModel.getNombresProveedores());
        return vista;
    }

}