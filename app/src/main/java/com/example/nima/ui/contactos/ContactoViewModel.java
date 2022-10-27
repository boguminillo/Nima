package com.example.nima.ui.contactos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Cliente;
import com.example.nima.data.model.Contacto;
import com.example.nima.data.model.Proveedor;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ContactoViewModel extends ViewModel {

    private static final CollectionReference contactosRef = FirebaseFirestore.getInstance().collection("contactos");
    private static final MutableLiveData<ArrayList<String>> nombres = new MutableLiveData<>();
    private static final MutableLiveData<Contacto> contacto = new MutableLiveData<>();
    private static final MutableLiveData<Boolean> cliente = new MutableLiveData<>();
    private static final MutableLiveData<String> resultado = new MutableLiveData<>();

    LiveData<ArrayList<String>> getNombres() {
        return nombres;
    }

    LiveData<Contacto> getContacto() {
        return contacto;
    }

    LiveData<Boolean> isCliente() {
        return cliente;
    }

    public LiveData<String> getResultado() {
        return resultado;
    }

    /**
     * Obtiene la lista de nombres de los clientes
     */
    public static void getNombresClientes() {
        // ordenando por un atributo que solo tienen los clientes conseguimos que solo nos devuelva los clientes
        contactosRef.orderBy("vip").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<String> listaNombres = new ArrayList<>();
                for (Cliente c : task.getResult().toObjects(Cliente.class)) {
                    listaNombres.add(c.getNombre());
                }
                listaNombres.sort(String::compareTo);
                nombres.setValue(listaNombres);
                cliente.setValue(true);
            }
        });
    }

    /**
     * Obtiene la lista de nombres de los proveedores
     */
    public static void getNombresProveedores() {
        // ordenando por un atributo que solo tienen los proveedores conseguimos que solo nos devuelva los proveedores
        contactosRef.orderBy("producto").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<String> listaNombres = new ArrayList<>();
                for (Proveedor c : task.getResult().toObjects(Proveedor.class)) {
                    listaNombres.add(c.getNombre());
                }
                listaNombres.sort(String::compareTo);
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
     * Este método se encarga de añadir un contacto a la base de datos
     *
     * @param contacto el contacto a añadir
     */
    public static void addContacto(Contacto contacto) {
        contactosRef.document(contacto.getNombre()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                // si el contacto ya existe no se añade
                if (doc.exists()) {
                    resultado.setValue("El contacto ya existe");
                    return;
                }
                contactosRef.document(contacto.getNombre()).set(contacto);
                resultado.setValue("Contacto añadido");
            }
        });
    }

    /**
     * Este método se encarga de actualizar un contacto de la base de datos
     *
     * @param contacto     el contacto a actualizar
     * @param cambioNombre si se ha cambiado el nombre del contacto
     */
    public static void updateContacto(Contacto contacto, boolean cambioNombre) {
        if (cambioNombre) {
            contactosRef.document(contacto.getNombre()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    // si el contacto con el nombre nuevo ya existe no se añade
                    if (doc.exists()) {
                        resultado.setValue("Ya existe un contacto con ese nombre");
                    } else {
                        contactosRef.document(contacto.getNombre()).set(contacto);
                        resultado.setValue("Contacto actualizado");
                    }
                }
            });
        } else {
            contactosRef.document(contacto.getNombre()).set(contacto);
            resultado.setValue("Contacto actualizado");
        }
    }

    /**
     * Este método se encarga de eliminar un contacto de la base de datos
     *
     * @param nombre nombre del contacto a eliminar
     */
    public static void deleteContacto(String nombre) {
        contactosRef.document(nombre).delete();
    }

    /**
     * Este método se encarga de eliminar el contacto del viewModel
     */
    public static void flushContacto() {
        contacto.setValue(null);
    }

    /**
     * Este método se encarga de eliminar el resultado del viewModel
     */
    public static void flushResultado() {
        resultado.setValue(null);
    }
}