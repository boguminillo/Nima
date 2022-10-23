package com.example.nima.data.model;

import com.google.firebase.firestore.DocumentId;
import java.util.Date;

public class Evento {

    @DocumentId
    private String nombre;
    private String direccion;
    private String descripcion;
    private Date fecha;

    public Evento() { }

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int compareTo(Evento e) {
        return this.getFecha().compareTo(e.getFecha());
    }

}
