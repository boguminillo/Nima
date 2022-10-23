package com.example.nima;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.example.nima.databinding.ActivityMainBinding;
import com.example.nima.ui.SettingsFragment;
import com.example.nima.ui.contactos.ContactoViewModel;
import com.example.nima.ui.eventos.EventoViewModel;
import com.example.nima.ui.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_calendario, R.id.nav_gallery, R.id.nav_lista_de_eventos, R.id.nav_formulario_evento, R.id.nav_lista_de_contactos, R.id.nav_formulario_cliente, R.id.nav_formulario_proveedor, R.id.nav_mapa)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //botones flotantes
        binding.appBarMain.btnEven.setOnClickListener(view -> {
            EventoViewModel.flushEvento();
            navController.navigate(R.id.nav_formulario_evento);
            binding.appBarMain.btnMenu.collapse();
        });
        binding.appBarMain.btnCliente.setOnClickListener(view -> {
            ContactoViewModel.flushContacto();
            navController.navigate(R.id.nav_formulario_cliente);
            binding.appBarMain.btnMenu.collapse();
        });
        binding.appBarMain.btnProveedor.setOnClickListener(view -> {
            ContactoViewModel.flushContacto();
            navController.navigate(R.id.nav_formulario_proveedor);
            binding.appBarMain.btnMenu.collapse();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, new SettingsFragment())
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.action_logout:
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
                preferences.edit().putBoolean(getResources().getString(R.string.auto_login), false).apply();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}