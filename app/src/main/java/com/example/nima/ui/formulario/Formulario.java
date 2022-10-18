package com.example.nima.ui.formulario;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import android.widget.Button;
import android.widget.EditText;
import com.example.nima.databinding.FragmentFormularioBinding;


public class Formulario extends Fragment {

   private FormularioViewModel mViewModel;

    public static Formulario newInstance() {
        return new Formulario();
    }

   private FragmentFormularioBinding binding;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = FragmentFormularioBinding.inflate(getLayoutInflater());

        mViewModel = new ViewModelProvider(this).get(FormularioViewModel.class);

        final EditText etNombre = binding.idNombre;
        final EditText etDireccion = binding.idDireccion;
        final EditText etTel = binding.idTelefono;
        final EditText etEmail = binding.idEmail;
        final Button btnSiguiente = binding.idBoton;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}