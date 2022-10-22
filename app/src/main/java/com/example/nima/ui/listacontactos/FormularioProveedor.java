package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nima.R;
import com.example.nima.databinding.FragmentFormularioClienteBinding;
import com.example.nima.databinding.FragmentFormularioProveedorBinding;

import javax.annotation.Nullable;


public class FormularioProveedor extends Fragment {

    private ContactoViewModel mViewModel;

    public static FormularioProveedor newInstance() {
        return new FormularioProveedor();
    }

    private FragmentFormularioProveedorBinding binding;

    ////Este metodo muestra la pantalla del fragmento formulario
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_proveedor, container, false);
    }
}