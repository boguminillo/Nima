package com.example.nima.ui.contactos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.nima.data.model.Cliente;
import com.example.nima.databinding.FragmentFormularioClienteBinding;

import javax.annotation.Nullable;


public class FormularioCliente extends Fragment {

    private FragmentFormularioClienteBinding binding;
    // esta variable se utilizara para comprobar si se ha editado el nombre
    String nombreOriginal = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFormularioClienteBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        ContactoViewModel mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
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
            // creamos el cliente si se esta creando uno nuevo o lo actualizamos si se esta editando
            if (nombreOriginal.equals("")) {
                ContactoViewModel.addContacto(cliente);
            } else {
                ContactoViewModel.updateContacto(cliente, !nombreOriginal.equals(cliente.getNombre()));
            }
            requireActivity().onBackPressed();
        });
        // funcion del boton de borrar
        binding.btnBorrar.setOnClickListener(v -> {
            ContactoViewModel.deleteContacto(nombreOriginal);
            requireActivity().onBackPressed();
        });
        // funcion del boton de cancelar
        binding.btnCancelar.setOnClickListener(v -> requireActivity().onBackPressed());
        return vista;
    }
}
