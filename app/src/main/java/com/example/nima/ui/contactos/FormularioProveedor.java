package com.example.nima.ui.contactos;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nima.data.model.Proveedor;
import com.example.nima.databinding.FragmentFormularioProveedorBinding;

import javax.annotation.Nullable;


public class FormularioProveedor extends Fragment {

    private ContactoViewModel mViewModel;

    public static FormularioProveedor newInstance() {
        return new FormularioProveedor();
    }

    private FragmentFormularioProveedorBinding binding;
    // esta variable se utilizara para comprobar si se ha editado el nombre
    String nombreOriginal = "";

    ////Este metodo muestra la pantalla del fragmento formulario
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFormularioProveedorBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
        // observador que cargara los datos del proveedor si es que se ha seleccionado uno
        mViewModel.getContacto().observe(getViewLifecycleOwner(), contacto -> {
            // la comprobacion es necesaria porque en ocasiones al volver atras dese otro formulario el contacto era un Cliente
            if (contacto instanceof Proveedor) {
                Proveedor proveedor = (Proveedor) contacto;
                binding.idNombre.setText(proveedor.getNombre());
                binding.idEmail.setText(proveedor.getEmail());
                binding.idTelefono.setText(proveedor.getTelefono());
                binding.idDireccion.setText(proveedor.getDireccion());
                binding.idProducto.setText(proveedor.getProducto());
                nombreOriginal = proveedor.getNombre();
                binding.btnBorrar.setVisibility(View.VISIBLE);
            }
        });
        // funcion del boton de guardar
        binding.btnGuardar.setOnClickListener(v -> {
            Proveedor proveedor = new Proveedor();
            proveedor.setNombre(binding.idNombre.getText().toString());
            proveedor.setEmail(binding.idEmail.getText().toString());
            proveedor.setTelefono(binding.idTelefono.getText().toString());
            proveedor.setDireccion(binding.idDireccion.getText().toString());
            proveedor.setProducto(binding.idProducto.getText().toString());
            ContactoViewModel.addUpdateContacto(proveedor);
            // si el nombre ha sido editado se elimina el contacto con el nombre original
            if (!nombreOriginal.equals(proveedor.getNombre())) {
                ContactoViewModel.deleteContacto(nombreOriginal);
            }
            getActivity().onBackPressed();
        });
        // funcion del boton de borrar
        binding.btnBorrar.setOnClickListener(v -> {
            ContactoViewModel.deleteContacto(nombreOriginal);
            getActivity().onBackPressed();
        });
        // funcion de boton de cancelar
        binding.btnCancelar.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
        return vista;
    }
}