package com.example.nima.data.model;

import com.google.firebase.firestore.DocumentId;
import com.google.type.DateTime;

public class Evento {

    @DocumentId
    private String nombre;
    private String direccion;
    private DateTime fecha;

    public Evento() { }

    public Evento(String nombre, String direccion, DateTime fecha) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public void setFecha(DateTime fecha) {
        this.fecha = fecha;
    }

}
