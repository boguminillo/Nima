package com.example.nima.ui.listacontactos;

import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Cliente;
import com.example.nima.data.model.Contacto;

import java.util.ArrayList;

public class ListaContactosViewModel extends ViewModel {

    public static ArrayList<String> getContactos() {
        ArrayList<String> contactos = new ArrayList<String>();
        contactos.add("nombre1");
        contactos.add("nombre2");
        contactos.add("nombre3");
        contactos.add("nombre4");
        return contactos;
    }
    // TODO: Implement the ViewModel
}