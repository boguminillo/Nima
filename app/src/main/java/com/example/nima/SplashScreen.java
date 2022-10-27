package com.example.nima;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import com.example.nima.ui.login.LoginActivity;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getResources().getString(R.string.tema_oscuro), false)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_splash_screen);

        ImageView imagen = findViewById(R.id.imageView);
        imagen.setOnClickListener(view -> {
            boolean inicioAutomatico = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getBoolean(getResources().getString(R.string.auto_login), false);
            Intent intent;
            if (inicioAutomatico) {
                intent = new Intent(SplashScreen.this, MainActivity.class);
            } else {
                intent = new Intent(SplashScreen.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
        });
    }
}