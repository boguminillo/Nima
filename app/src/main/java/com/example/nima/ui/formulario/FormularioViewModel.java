package com.example.nima.ui.formulario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;




public class FormularioViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> Formulario;

    public FormularioViewModel() {
        Formulario = new MutableLiveData<>();
        Formulario.setValue("This is Formulario fragment");
    }

    public LiveData<String> getText() {
        return Formulario;
    }
}








