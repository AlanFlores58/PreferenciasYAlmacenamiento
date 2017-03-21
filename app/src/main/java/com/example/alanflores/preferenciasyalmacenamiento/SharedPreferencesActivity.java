package com.example.alanflores.preferenciasyalmacenamiento;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Toast;

public class SharedPreferencesActivity extends AppCompatActivity {

    private EditText editCorreo, editTelefono;
    private CheckBox checkBoxNoticias;
    private Button buttonGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        editCorreo = (EditText) findViewById(R.id.edit_correo);
        editTelefono = (EditText) findViewById(R.id.edit_telefono);
        checkBoxNoticias = (CheckBox) findViewById(R.id.checkbox_noticias);
        buttonGuardar = (Button) findViewById(R.id.button_guardar);

        SharedPreferences preferences = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        String correo = preferences.getString("correo","");
        int telefono = preferences.getInt("telefono",0);
        boolean noticias = preferences.getBoolean("noticias",false);

        editCorreo.setText(correo);
        editTelefono.setText(Integer.toString(telefono));
        checkBoxNoticias.setChecked(noticias);

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("preferencias",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("correo",editCorreo.getText().toString());
                editor.putInt("telefono",Integer.valueOf(editTelefono.getText().toString()));
                editor.putBoolean("noticias",checkBoxNoticias.isChecked());
                editor.apply();
                Toast.makeText(SharedPreferencesActivity.this,"Datos guardados",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
