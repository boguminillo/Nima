package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentListaContactosBinding binding = FragmentListaContactosBinding.inflate(getLayoutInflater());
        ListView lvContactos = binding.listViewContactos;
        ContactoViewModel mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
        mViewModel.getLista().observe(this, contactos -> {
            ArrayList<String> strContactos = new ArrayList<>(contactos.keySet());
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>((Context) getActivity(), android.R.layout.simple_list_item_1, strContactos);
            lvContactos.setAdapter(adaptador);
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ContactoViewModel.getNombresList();
        return inflater.inflate(R.layout.fragment_lista_contactos, container, false);
    }

    //Este metodo muestra la pantalla del fragmento lista contactos
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View vista = inflater.inflate(R.layout.fragment_lista_contactos, container, false);
//        ListView lvContactos = vista.findViewById(R.id.listViewContactos);
//        ArrayList<String> contactos = ContactoViewModel.getNombresList();
//        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contactos);
//        lvContactos.setAdapter(adaptador);
//        return vista;
//    }

}