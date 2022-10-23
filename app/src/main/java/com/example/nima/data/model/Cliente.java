package com.example.nima.data.model;

public class Cliente extends Contacto {
    private boolean vip;

    public Cliente() {}

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
