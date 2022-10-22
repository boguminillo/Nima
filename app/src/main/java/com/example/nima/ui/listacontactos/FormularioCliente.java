package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nima.R;
import com.example.nima.data.model.Cliente;
import com.example.nima.databinding.FragmentFormularioClienteBinding;

import javax.annotation.Nullable;


public class FormularioCliente extends Fragment {

    private ContactoViewModel mViewModel;

    public static FormularioCliente newInstance() {
        return new FormularioCliente();
    }

    private FragmentFormularioClienteBinding binding;

    ////Este metodo muestra la pantalla del fragmento formulario
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFormularioClienteBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
        mViewModel.getContacto().observe(getViewLifecycleOwner(), contacto -> {
            Cliente cliente = (Cliente) contacto;
            binding.idNombre.setText(cliente.getNombre());
            binding.idEmail.setText(cliente.getEmail());
            binding.idTelefono.setText(cliente.getTelefono());
            binding.idDireccion.setText(cliente.getDireccion());
            binding.chkVip.setChecked(cliente.isVip());
        });
        return vista;
    }
}
