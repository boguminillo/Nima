package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;

import com.example.nima.R;
import com.example.nima.databinding.FragmentListaContactosBinding;

import javax.annotation.Nullable;

public class ListaContactos extends Fragment {

    private ListaContactosViewModel mViewModel;
    private FragmentListaContactosBinding binding;

    public static ListaContactos newInstance() {
        return new ListaContactos();
    }
//Este metodo muestra la pantalla del fragmento lista contactos
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_contactos, container, false);
    }



    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      mViewModel = new ViewModelProvider(this).get(ListaContactosViewModel.class);

        AdapterContacto adapterContacto = new AdapterContacto(getContext(), R.layout.fragment_lista_contactos, ListaContactosViewModel.getContactos());

    }


}