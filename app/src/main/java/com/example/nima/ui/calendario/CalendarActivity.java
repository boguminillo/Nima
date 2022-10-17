package com.example.nima.ui.calendario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nima.R;


public class CalendarActivity extends AppCompatActivity {
    EditText Titulo;
    EditText Ubicacion;
    EditText Descripcion;
    Button añadirEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Titulo = findViewById(R.id.etTitulo);
        Ubicacion = findViewById(R.id.etUbicacion);
        Descripcion = findViewById(R.id.etDescripcion);
        añadirEvento = findViewById(R.id.btnAñadir);

        añadirEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
