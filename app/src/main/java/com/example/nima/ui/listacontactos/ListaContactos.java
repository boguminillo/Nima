package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import androidx.fragment.app.Fragment;

import com.example.nima.R;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class ListaContactos extends Fragment {

    public static ListaContactos newInstance() {
        return new ListaContactos();
    }

    //Este metodo muestra la pantalla del fragmento lista contactos
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_lista_contactos, container, false);
        ListView lvContactos = vista.findViewById(R.id.listViewContactos);
        ArrayList<String> contactos = ContactoViewModel.getNombresList();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contactos);
        lvContactos.setAdapter(adaptador);
        return vista;
    }

}