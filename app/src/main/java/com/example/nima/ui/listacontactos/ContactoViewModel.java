package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Cliente;
import com.example.nima.data.model.Contacto;
import com.example.nima.data.model.Proveedor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContactoViewModel extends ViewModel {

    private static CollectionReference contactosRef = FirebaseFirestore.getInstance().collection("contactos");
    private static MutableLiveData<Map<String, Object>> lista = new MutableLiveData<>();

    LiveData<Map<String, Object>> getLista() {
        return lista;
    }

    public static void getNombresList() {
        // con el whereIn estamos cogiendo solo clientes
        contactosRef.whereIn("vip", Arrays.asList(true, false)).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Map<String, Object> mapaContactos = new HashMap<>();
                //TODO: revisar lo de la clase
                for (Cliente c : task.getResult().toObjects(Cliente.class)) {
                    mapaContactos.put(c.getNombre(), c);
                }
                lista.setValue(mapaContactos);
            } else {
                String parar;
            }
        });
    }

//    public static ArrayList<String> getNombresList() {
//        Contacto contacto = new Cliente("Cliente1", "Calle 1", "123456789", "cliente1@cliente.com", false);
//        Contacto contacto2 = new Cliente("Cliente2", "Calle 2", "987654321", "cliente2@cliente.com", true);
//        Contacto contacto3 = new Proveedor("Proveedor1", "Calle 3", "123456789", "proveedor1@proveedor.com:", "www.proveedor1.com");
//        Contacto contacto4 = new Proveedor("Proveedor2", "Calle 4", "987654321", "proveedor2@proveedor.com:", "www.proveedor2.com");
//        addContacto(contacto);
//        addContacto(contacto2);
//        addContacto(contacto3);
//        addContacto(contacto4);
//        ArrayList<String> nombres = new ArrayList<>(Arrays.asList(contacto.getNombre(), contacto2.getNombre(), contacto3.getNombre(), contacto4.getNombre()));
//        return nombres;
//    }

    public static void addContacto(Contacto contacto) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("contactos").document(contacto.getNombre()).set(contacto);
//        contactosRef.document(contacto.getNombre()).set(contacto);
    }
    // TODO: Implement the ViewModel
}