package com.example.nima.data.model;

public class Proveedor extends Contacto {
    private String producto;

    public Proveedor(String nombre, String direccion, String telefono, String email,String producto) {
        super(nombre, direccion, telefono, email);
        this.producto=producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
}
