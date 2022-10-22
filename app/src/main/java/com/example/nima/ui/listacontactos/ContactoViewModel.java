package com.example.nima.ui.listacontactos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Cliente;
import com.example.nima.data.model.Contacto;
import com.example.nima.data.model.Proveedor;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ContactoViewModel extends ViewModel {

    private static CollectionReference contactosRef = FirebaseFirestore.getInstance().collection("contactos");
    private static MutableLiveData<ArrayList<String>> nombres = new MutableLiveData<>();
    private static MutableLiveData<Contacto> contacto = new MutableLiveData<>();
    private static MutableLiveData<Boolean> cliente = new MutableLiveData<>();

    LiveData<ArrayList<String>> getNombres() {
        return nombres;
    }

    LiveData<Contacto> getContacto() {
        return contacto;
    }

    LiveData<Boolean> isCliente() {
        return cliente;
    }

    /**
     * Obtiene la lista de nombres de los clientes
     */
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
                cliente.setValue(true);
            }
        });
    }

    /**
     * Obtiene la lista de nombres de los proveedores
     */
    public static void getNombresProveedores() {
        // con el whereIn estamos cogiendo solo proveedores
        contactosRef.orderBy("producto").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<String> listaNombres = new ArrayList<>();
                for (Proveedor c : task.getResult().toObjects(Proveedor.class)) {
                    listaNombres.add(c.getNombre());
                }
                nombres.setValue(listaNombres);
                cliente.setValue(false);
            }
        });
    }

    /**
     * Este método se encarga de recoger la informacion de un contacto de la base de datos
     *
     * @param nombre nombre del contacto
     */
    public static void getContacto(String nombre) {
        contactosRef.document(nombre).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                if (doc.getBoolean("vip") != null) {
                    contacto.setValue(doc.toObject(Cliente.class));
                } else {
                    contacto.setValue(doc.toObject(Proveedor.class));
                }
            }
        });
    }

    /**
     * Este método se encarga de vaciar el contacto de la cache
     */
    public static void vaciarContacto() {
        contacto.setValue(null);
    }

    /**
     * Este método se encarga de añadir un contacto a la base de datos o actualizarlo en caso de que ya exista
     *
     * @param contacto
     */
    public static void addUpdateContacto(Contacto contacto) {
        contactosRef.document(contacto.getNombre()).set(contacto);
    }
}