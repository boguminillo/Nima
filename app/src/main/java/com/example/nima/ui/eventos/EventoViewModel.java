package com.example.nima.ui.eventos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Cliente;
import com.example.nima.data.model.Evento;
import com.example.nima.data.model.Proveedor;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class EventoViewModel extends ViewModel {

    private static CollectionReference eventosRef = FirebaseFirestore.getInstance().collection("eventos");
    private static MutableLiveData<ArrayList<String>> nombres = new MutableLiveData<>();
    private static MutableLiveData<Evento> evento = new MutableLiveData<>();

    LiveData<ArrayList<String>> getNombres() {
        return nombres;
    }

    LiveData<Evento> getEvento() {
        return evento;
    }

    /**
     * Obtiene la lista de nombres de los eventos
     */
    public static void getNombresEventos() {
        // ordenando por un atributo que solo tienen los clientes conseguimos que solo nos devuelva los clientes
        eventosRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<String> listaNombres = new ArrayList<>();
                for (Evento e : task.getResult().toObjects(Evento.class)) {
                    listaNombres.add(e.getNombre());
                }
                nombres.setValue(listaNombres);
            }
        });
    }

    /**
     * Este método se encarga de recoger la informacion de un evento de la base de datos
     *
     * @param nombre nombre del evento
     */
    public static void getEvento(String nombre) {
        eventosRef.document(nombre).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                evento.setValue(doc.toObject(Evento.class));
            }
        });
    }

    /**
     * Este método se encarga de añadir un evento a la base de datos o actualizarlo en caso de que ya exista
     *
     * @param evento
     */
    public static void addUpdateEvento(Evento evento) {
        eventosRef.document(evento.getNombre()).set(evento);
    }

    /**
     * Este método se encarga de eliminar un evento de la base de datos
     *
     * @param nombre
     */
    public static void deleteEvento(String nombre) {
        eventosRef.document(nombre).delete();
    }

    /**
     * Este método se encarga de eliminar el evento del viewModel
     */
    public static void flushEvento() {
        evento.setValue(null);
    }
}