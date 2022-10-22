package com.example.nima.ui.listacontactos;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.nima.R;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Nullable;


public class ListaContactos extends Fragment {

    public static ListaContactos newInstance() {
        return new ListaContactos();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ContactoViewModel.getNombresClientes();
        View vista = inflater.inflate(R.layout.fragment_lista_contactos, container, false);
        ListView lvContactos = vista.findViewById(R.id.listViewContactos);
        ContactoViewModel mViewModel = new ViewModelProvider(this).get(ContactoViewModel.class);
        // este booleano lo guardamos para saber si la lista es de clientes o proveedores
        AtomicBoolean esCliente = new AtomicBoolean();
        // funcion que al hacer click en un contacto abrira el formulario para editarlo
        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String contacto = (String) parent.getItemAtPosition(position);
                NavController navController = Navigation.findNavController(view);
                if (esCliente.get()) {
                    navController.navigate(R.id.nav_formulario_cliente);
                } else {
                    navController.navigate(R.id.nav_formulario_proveedor);
                }
                ContactoViewModel.getContacto(contacto);
            }
        });
        // este observer se ejecuta cuando se actualiza la lista de nombres
        mViewModel.getNombres().observe(getViewLifecycleOwner(), contactos -> {
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, contactos);
            lvContactos.setAdapter(adaptador);
        });
        // este observer se ejecuta cuando se cambia de cliente a proveedor o viceversa
        mViewModel.isCliente().observe(getViewLifecycleOwner(), isCliente -> {
            if (isCliente) esCliente.set(true);
            else esCliente.set(false);
        });
        Button btnClienes = vista.findViewById(R.id.bntClientes);
        btnClienes.setOnClickListener(v -> ContactoViewModel.getNombresClientes());
        Button btnProveedores = vista.findViewById(R.id.btnProveedores);
        btnProveedores.setOnClickListener(v -> ContactoViewModel.getNombresProveedores());
        return vista;
    }

}