package com.example.nima.ui.listacontactos;

import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Cliente;
import com.example.nima.data.model.Contacto;

import java.util.ArrayList;

public class ListaContactosViewModel extends ViewModel {

    public static ArrayList<Contacto> getContactos() {
        ArrayList<Contacto> contactos = new ArrayList<Contacto>();
        contactos.add(new Cliente("nombre1", "direccion1", "telefono1", "email1", true));
        contactos.add(new Cliente("nombre2", "direccion2", "telefono2", "email2", true));
        contactos.add(new Cliente("nombre3", "direccion3", "telefono3", "email3", true));
        contactos.add(new Cliente("nombre4", "direccion4", "telefono4", "email4", true));
        return contactos;
    }
    // TODO: Implement the ViewModel
}