package com.example.nima.ui.listacontactos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nima.R;
import com.example.nima.databinding.FragmentListaContactosBinding;

public class ListaContactos extends Fragment {

    private ListaContactosViewModel mViewModel;
    private FragmentListaContactosBinding binding;

    public static ListaContactos newInstance() {
        return new ListaContactos();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListaContactosViewModel.class);

        AdapterContacto adapterContacto = new AdapterContacto(getContext(), R.layout.fragment_lista_contactos, ListaContactosViewModel.getContactos());

    }


}