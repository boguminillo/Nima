package com.example.nima.ui.formulario;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.nima.R;
import com.example.nima.databinding.FragmentFormularioBinding;

import javax.annotation.Nullable;


public class Formulario extends Fragment {

   private FormularioViewModel mViewModel;

    public static Formulario newInstance() {
        return new Formulario();
    }

   private FragmentFormularioBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FormularioViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


  //  @Override
//   public void onCreate( Bundle savedInstanceState) {
        //       super.onCreate(savedInstanceState);

        //    binding = FragmentFormularioBinding.inflate(getLayoutInflater());

        //    mViewModel = new ViewModelProvider(this).get(FormularioViewModel.class);

        //    final EditText etNombre = binding.idNombre;
        //     final EditText etDireccion = binding.idDireccion;
        //     final EditText etTel = binding.idTelefono;
        //     final EditText etEmail = binding.idEmail;
   //     final Button btnSiguiente = binding.idBoton;

  //  }
