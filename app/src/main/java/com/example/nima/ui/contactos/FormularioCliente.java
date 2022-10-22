package com.example.nima.ui.contactos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nima.data.model.Cliente;
import com.example.nima.databinding.FragmentFormularioClienteBinding;

import javax.annotation.Nullable;


public class FormularioCliente extends Fragment {

    private ContactoViewModel mViewModel;

    public static FormularioCliente newInstance() {
        return new FormularioCliente();
    }

    private FragmentFormularioClienteBinding binding;
    // esta variable se utilizara para comprobar si se ha editado el nombre
    String nombreOriginal = "";

    ////Este metodo muestra la pantalla del fragmento formulario
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFormularioClienteBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
        // observador que cargara los datos del cliente si es que se ha seleccionado uno
        mViewModel.getContacto().observe(getViewLifecycleOwner(), contacto -> {
            // la comprobacion es necesaria porque en ocasiones al volver atras dese otro formulario el contacto era un proveedor
            if (contacto instanceof Cliente) {
                Cliente cliente = (Cliente) contacto;
                binding.idNombre.setText(cliente.getNombre());
                binding.idEmail.setText(cliente.getEmail());
                binding.idTelefono.setText(cliente.getTelefono());
                binding.idDireccion.setText(cliente.getDireccion());
                binding.chkVip.setChecked(cliente.isVip());
                nombreOriginal = cliente.getNombre();
                binding.btnBorrar.setVisibility(View.VISIBLE);
            }
        });
        // funcion del boton de guardar
        binding.btnGuardar.setOnClickListener(v -> {
            Cliente cliente = new Cliente();
            cliente.setNombre(binding.idNombre.getText().toString());
            cliente.setEmail(binding.idEmail.getText().toString());
            cliente.setTelefono(binding.idTelefono.getText().toString());
            cliente.setDireccion(binding.idDireccion.getText().toString());
            cliente.setVip(binding.chkVip.isChecked());
            ContactoViewModel.addUpdateContacto(cliente);
            // si se ha editado el nombre se elimina el contacto con el nombre original
            if (!nombreOriginal.equals(cliente.getNombre())) {
                ContactoViewModel.deleteContacto(nombreOriginal);
            }
            getActivity().onBackPressed();
        });
        // funcion del boton de borrar
        binding.btnBorrar.setOnClickListener(v -> {
            ContactoViewModel.deleteContacto(nombreOriginal);
            getActivity().onBackPressed();
        });
        // funcion del boton de cancelar
        binding.btnCancelar.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        return vista;
    }
}
