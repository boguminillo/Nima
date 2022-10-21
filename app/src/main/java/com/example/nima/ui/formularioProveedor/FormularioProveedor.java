package com.example.nima.ui.formularioProveedor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nima.R;
import com.example.nima.ui.calendario.CalendarActivity;

import javax.annotation.Nullable;

public class FormularioProveedor extends AppCompatActivity {
    EditText Nombre;
    EditText Direccion;
    EditText Telefono;
    EditText Email;
    Button AñadirProveedor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_proveedor);


        Nombre = findViewById(R.id.idNombre);
        Direccion = findViewById(R.id.idDireccion);
        Telefono = findViewById(R.id.idTelefono);
        Email = findViewById(R.id.idEmail);

        AñadirProveedor = findViewById(R.id.idBoton);

        AñadirProveedor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });


        }
    }
