package com.example.alanflores.preferenciasyalmacenamiento.DB;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alanflores.preferenciasyalmacenamiento.R;

import java.util.List;

public class SQLiteActivity extends AppCompatActivity {

    private EditText editNombre, editApellido, editedad;
    private Button buttonGuardar;
    private ListView listView;
    private PersonaDataSource personaDataSource;
    private ArrayAdapter<Persona> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        editNombre = (EditText) findViewById(R.id.edit_nombre);
        editApellido = (EditText) findViewById(R.id.edit_apellido);
        editedad = (EditText) findViewById(R.id.edit_edad);
        buttonGuardar = (Button) findViewById(R.id.button_guardar);

        listView = (ListView)findViewById(android.R.id.list);

        crearBaseDatos();

        buttonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarRegistro();
                adapter.notifyDataSetChanged();
            }
        });

        adapter = new ArrayAdapter<Persona>(getApplicationContext(),android.R.layout.simple_list_item_1,obtenerRegistros());
        listView.setAdapter(adapter);
    }

    protected void crearBaseDatos(){
        personaDataSource = new PersonaDataSource(this);
    }

    protected void insertarRegistro(){
        personaDataSource.open();
        personaDataSource.insertarRegistro(editNombre.getText().toString(),editApellido.getText().toString(), Integer.valueOf(editedad.getText().toString()));
        personaDataSource.close();
        editApellido.setText("");
        editNombre.setText("");
        editedad.setText("");
    }

    protected List<Persona> obtenerRegistros(){
        personaDataSource.open();
        List<Persona> personas = personaDataSource.obtenerRegistro();
        personaDataSource.close();
        return personas;
    }
}
