package com.example.nima.ui.eventos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.nima.data.model.Evento;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class EventoViewModel extends ViewModel {

    private static final CollectionReference eventosRef = FirebaseFirestore.getInstance().collection("eventos");
    private static final MutableLiveData<ArrayList<Evento>> listaEventos = new MutableLiveData<>();
    private static final MutableLiveData<Evento> evento = new MutableLiveData<>();
    private static final MutableLiveData<String> resultado = new MutableLiveData<>();
    private static final MutableLiveData<LatLng> posicion = new MutableLiveData<>();

    LiveData<ArrayList<Evento>> getLista() {
        return listaEventos;
    }

    LiveData<Evento> getEvento() {
        return evento;
    }

    public LiveData<String> getResultado() {
        return resultado;
    }

    LiveData<LatLng> getPosicion() {
        return posicion;
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
     * Obtiene la lista de los eventos en una fecha concreta
     */
    public static void getEventos(Date date) {
        // ordenando por un atributo que solo tienen los clientes conseguimos que solo nos devuelva los clientes
        eventosRef.whereEqualTo("fecha", date).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<Evento> eventos = new ArrayList<>(task.getResult().toObjects(Evento.class));
                eventos.sort(Evento::compareTo);
                listaEventos.setValue(eventos);
            }
        });
    }

    /**
     * Este método se encarga de añadir un evento a la base de datos
     *
     * @param evento evento a añadir
     */
    public static void addEvento(Evento evento) {
        eventosRef.document(evento.getNombre()).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot doc = task.getResult();
                // si el contacto ya existe no se añade
                if (doc.exists()) {
                    resultado.setValue("El evento ya existe");
                    return;
                }
                eventosRef.document(evento.getNombre()).set(evento);
                resultado.setValue("Evento añadido");
            }
        });
    }

    /**
     * Este método se encarga de actualizar un evento de la base de datos
     *
     * @param evento     el evento a actualizar
     * @param cambioNombre si se ha cambiado el nombre del evento
     */
    public static void updateEvento(Evento evento, boolean cambioNombre) {
        if (cambioNombre) {
            eventosRef.document(evento.getNombre()).get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    // si el contacto con el nombre nuevo ya existe no se añade
                    if (doc.exists()) {
                        resultado.setValue("Ya existe un evento con ese nombre");
                    } else {
                        eventosRef.document(evento.getNombre()).set(evento);
                        resultado.setValue("Evento actualizado");
                    }
                }
            });
        } else {
            eventosRef.document(evento.getNombre()).set(evento);
            resultado.setValue("Evento actualizado");
        }
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
     * Este método se encarga de guardar la informacion del evento seleccionado
     *
     * @param e el evento seleccionado
     */
    public static void setEvento(Evento e) {
        evento.setValue(e);
    }

    /**
     * Metodo para guardar la posicion del mapa
     *
     * @param latLng posicion del mapa
     */
    public static void setPosicion(LatLng latLng) {
        posicion.setValue(latLng);
    }

    /**
     * Metodo para guardar la direccion del mapa
     *
     * @param dir direccion del mapa
     */
    public static void setDireccion(String dir) {
        Objects.requireNonNull(evento.getValue()).setDireccion(dir);
    }

    /**
     * Este método se encarga de eliminar el evento del viewModel
     */
    public static void flushEvento() {
        evento.setValue(null);
    }

    /**
     * Este método se encarga de eliminar la lista de eventos del viewModel
     */
    public static void flushListaEventos() {
        listaEventos.setValue(new ArrayList<>());
    }

    /**
     * Este metodo se encarga de eliminar el resultado del viewModel
     */
    public static void flushResultado() {
        resultado.setValue(null);
    }

    /**
     * Este metodo se encarga de eliminar la posicion del viewModel
     */
    public static void flushPosicion() {
        posicion.setValue(null);
    }
}