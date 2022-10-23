package com.example.nima.ui.eventos;

import android.content.ContentProvider;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.preference.PreferenceManager;

import com.example.nima.R;
import com.example.nima.data.model.Evento;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.protobuf.SourceContext;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class EventoViewModel extends ViewModel {

    private static final CollectionReference eventosRef = FirebaseFirestore.getInstance().collection("eventos");
    private static final MutableLiveData<ArrayList<Evento>> listaEventos = new MutableLiveData<>();
    private static final MutableLiveData<Evento> evento = new MutableLiveData<>();

    LiveData<ArrayList<Evento>> getLista() {
        return listaEventos;
    }

    LiveData<Evento> getEvento() {
        return evento;
    }

    /**
     * Obtiene la lista de los eventos
     */
    public static void getEventos() {
        // ordenando por un atributo que solo tienen los clientes conseguimos que solo nos devuelva los clientes
        eventosRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<Evento> eventos = new ArrayList<>(task.getResult().toObjects(Evento.class));
                eventos.sort(Evento::compareTo);
                listaEventos.setValue(eventos);
            }
        });
    }

    /**
     * Este método se encarga de guardar la informacion del evento seleccionado
     *
     * @param e el evento seleccionado
     */
    public static void setEvento(Evento e) {
        evento.setValue(e);
    }

    /**
     * Este método se encarga de añadir un evento a la base de datos o actualizarlo en caso de que ya exista
     *
     * @param evento evento a añadir
     */
    public static void addUpdateEvento(Evento evento) {
        eventosRef.document(evento.getNombre()).set(evento);
    }

    /**
     * Este método se encarga de eliminar un evento de la base de datos
     *
     * @param nombre nombre del evento a eliminar
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