package com.example.nima.ui.listacontactos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nima.R;
import com.example.nima.data.model.Contacto;

import java.util.List;

public class AdapterContacto extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Contacto> contactos;

    public AdapterContacto(Context context, int layout, List<Contacto> contactos){
        this.context = context;
        this.layout = layout;
        this.contactos = contactos;
    }

    @Override
    public int getCount() {
        return contactos.size();
    }

    @Override
    public Object getItem(int position) {
        return contactos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return contactos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v = layoutInflater.inflate(R.layout.fragment_lista_contactos, null);

        String nombre = contactos.get(position).getNombre();
        TextView tvNombre = v.findViewById(R.id.tvNombreListaContactos);
        tvNombre.setText(nombre);

        return v;
    }
}

