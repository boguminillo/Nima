package com.example.nima.ui.listacontactos;

import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Cliente;
import com.example.nima.data.model.Contacto;
import com.example.nima.data.model.Proveedor;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ContactoViewModel extends ViewModel {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();

    public static ArrayList<String> getNombresList() {
        Contacto contacto = new Cliente("Cliente1", "Calle 1", "123456789", "cliente1@cliente.com", false);
        Contacto contacto2 = new Cliente("Cliente2", "Calle 2", "987654321", "cliente2@cliente.com", true);
        Contacto contacto3 = new Proveedor("Proveedor1", "Calle 3", "123456789", "proveedor1@proveedor.com:", "www.proveedor1.com");
        Contacto contacto4 = new Proveedor("Proveedor2", "Calle 4", "987654321", "proveedor2@proveedor.com:", "www.proveedor2.com");
        contacto.setId(0);
        db.collection("contactos").add(contacto);
        addContacto(contacto2);
        addContacto(contacto3);
        addContacto(contacto4);
        ArrayList<String> nombres = new ArrayList<>();
        for (Contacto c : db.collection("contactos").get().getResult().toObjects(Contacto.class)) {
            nombres.add(c.getNombre());
        }
        return nombres;
    }

    public static boolean addContacto(Contacto contacto) {
        contacto.setId(db.collection("contactos").get().getResult().size());
        db.collection("contactos").add(contacto);
        return true;
    }
    // TODO: Implement the ViewModel
}