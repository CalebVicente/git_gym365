package com.example.gym365;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import androidx.appcompat.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hasta API nivel 11 se hacía inflando aquí el XML
        // addPreferencesFromResource(R.xml.settings_layout);
        // desde API nivel 11 es obligatorio hacerlo con fragmentos

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }

    public static class MyPreferenceFragment extends PreferenceFragment
    {
        @Override
        public void onCreate(final Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_layout);
        }
    }
}
