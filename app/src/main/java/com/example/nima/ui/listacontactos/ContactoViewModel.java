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
import java.util.Map;

public class ContactoViewModel extends ViewModel {

    private static FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static CollectionReference contactosRef = db.collection("contactos");
    private static MutableLiveData<Map<String, Object>> lista = new MutableLiveData<>();

    LiveData<Map<String, Object>> getLista() {
        return lista;
    }

    public static void getNombresList() {
        Task<QuerySnapshot> tareaGet = contactosRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        lista.setValue(document.getData());
                    }
                }
            }
        });
    }

//    public static ArrayList<String> getNombresList() {
//        Contacto contacto = new Cliente("Cliente1", "Calle 1", "123456789", "cliente1@cliente.com", false);
//        Contacto contacto2 = new Cliente("Cliente2", "Calle 2", "987654321", "cliente2@cliente.com", true);
//        Contacto contacto3 = new Proveedor("Proveedor1", "Calle 3", "123456789", "proveedor1@proveedor.com:", "www.proveedor1.com");
//        Contacto contacto4 = new Proveedor("Proveedor2", "Calle 4", "987654321", "proveedor2@proveedor.com:", "www.proveedor2.com");
//        contacto.setId(0);
//        db.collection("contactos").add(contacto);
//        addContacto(contacto2);
//        addContacto(contacto3);
//        addContacto(contacto4);
//        ArrayList<String> nombres = new ArrayList<>();
//        for (Contacto c : db.collection("contactos").get().getResult().toObjects(Contacto.class)) {
//            nombres.add(c.getNombre());
//        }
//        return nombres;
//    }

    public static void addContacto(Contacto contacto) {
        contactosRef.document(contacto.getNombre()).set(contacto);
    }
    // TODO: Implement the ViewModel
}