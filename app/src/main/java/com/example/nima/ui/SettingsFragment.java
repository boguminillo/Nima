package com.example.nima.ui;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import com.example.nima.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
        SwitchPreferenceCompat temaPorDefecto = findPreference(getString(R.string.tema_defecto));
        SwitchPreferenceCompat temaOscuro = findPreference(getString(R.string.tema_oscuro));
        assert temaPorDefecto != null;
        assert temaOscuro != null;
        // Si el tema por defecto está activado, desactivamos la opcion de tema oscuro
        if (temaPorDefecto.isChecked())
            temaOscuro.setEnabled(false);
        // listener para activar o desactivar la opcion de tema oscuro al cambiar el estado de tema por defecto
        temaPorDefecto.setOnPreferenceChangeListener((preference, newValue) -> {
            temaOscuro.setEnabled(!newValue.equals(true));
            return true;
        });
    }

}