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
import com.example.nima.SplashScreen;
import com.example.nima.ui.login.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;


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
                if (!Titulo.getText().toString().isEmpty() && !Ubicacion.getText().toString().isEmpty() && !Descripcion.getText().toString().isEmpty()) {
                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, Titulo.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, Ubicacion.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, Descripcion.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, "true");
                    intent.putExtra(Intent.EXTRA_EMAIL, "endiuri6@gmail.com, uriagerekaendika@gmail.com");

                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(CalendarActivity.this, "No hay aplicacion capaz de soportar esta accion", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CalendarActivity.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
