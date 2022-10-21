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
    private static MutableLiveData<ArrayList<String>> nombres = new MutableLiveData<>();
    private static MutableLiveData<Contacto> contacto = new MutableLiveData<>();

    LiveData<ArrayList<String>> getNombres() {
        return nombres;
    }
    LiveData<Contacto> getContacto() {
        return contacto;
    }

    public static void getNombresClientes() {
        // con el whereIn estamos cogiendo solo clientes
//        contactosRef.whereIn("vip", Arrays.asList(true, false)).get().addOnCompleteListener(task -> {
            contactosRef.orderBy("vip").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<String> listaNombres = new ArrayList<>();
                for (Cliente c : task.getResult().toObjects(Cliente.class)) {
                    listaNombres.add(c.getNombre());
                }
                nombres.setValue(listaNombres);
            }
        });
    }

    public static void getNombresProveedores() {
        // con el whereIn estamos cogiendo solo proveedores
        contactosRef.orderBy("producto").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<String> listaNombres = new ArrayList<>();
                for (Proveedor c : task.getResult().toObjects(Proveedor.class)) {
                    listaNombres.add(c.getNombre());
                }
                nombres.setValue(listaNombres);
            }
        });
    }

    public static void getContacto(String nombre) {
        contactosRef.whereEqualTo("nombre", nombre).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    if (document.get("vip") != null) {
                        contacto.setValue(document.toObject(Cliente.class));
                    } else {
                        contacto.setValue(document.toObject(Proveedor.class));
                    }
                }
            }
        });
    }

    public static void addContacto(Contacto contacto) {
        contactosRef.document(contacto.getNombre()).set(contacto);
    }
    // TODO: Implement the ViewModel
}