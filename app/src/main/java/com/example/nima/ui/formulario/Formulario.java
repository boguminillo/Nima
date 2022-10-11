package com.example.nima.ui.formulario;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nima.R;

public class Formulario extends Fragment {

    private FormularioViewModel mViewModel;
    public static Formulario newInstance() {
        return new Formulario();
    }




    public Formulario(){}


    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario, container, false);




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FormularioViewModel.class);
        // TODO: Use the ViewModel
    }

}