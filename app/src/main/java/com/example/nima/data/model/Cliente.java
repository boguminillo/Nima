package com.example.nima.data.model;

public class Cliente extends Contacto {
    private boolean vip;

    public Cliente() {}

    public Cliente(String nombre, String direccion, String telefono, String email,boolean vip) {
        super(nombre, direccion, telefono, email);
        this.vip=vip;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
