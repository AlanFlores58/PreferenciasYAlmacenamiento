package com.example.alanflores.preferenciasyalmacenamiento;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AlmacenamientoInternoExterno extends AppCompatActivity {

    private EditText ediTextoInterna, editTextExterna;
    private Button buttonGuardarInterna, buttonGuardarExterna, buttonLeerInterna, buttonLeerExterna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_almacenamiento_interno_externo);
        ediTextoInterna = (EditText) findViewById(R.id.edit_texto_archivo_uno);
        editTextExterna = (EditText) findViewById(R.id.edit_texto_archivo_dos);
        buttonGuardarInterna = (Button) findViewById(R.id.button_guardar_interna);
        buttonGuardarExterna = (Button) findViewById(R.id.button_guardar_externa);
        buttonLeerInterna = (Button) findViewById(R.id.button_recuperar_interna);
        buttonLeerExterna = (Button) findViewById(R.id.button_recuperar_externa);

        buttonGuardarInterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarArchivoMemInterna(ediTextoInterna.getText().toString());
            }
        });

        buttonLeerInterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),leerArchivoMemInterna(),Toast.LENGTH_SHORT).show();
            }
        });

        buttonGuardarExterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardarArchivoMemExterna(editTextExterna.getText().toString());
            }
        });

        buttonLeerExterna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),leerArchivoMemExterna(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void guardarArchivoMemInterna(String textoArchivo){
        String nombreArchivo = "archivoAndoid.txt";

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(nombreArchivo, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if(fos != null){
                fos.write(textoArchivo.getBytes());
                fos.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String leerArchivoMemInterna(){
        String nombreArchivo = "archivoAndoid.txt";
        String contenidoArchivo = "";

        try {
            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(openFileInput(nombreArchivo)));
            contenidoArchivo = bufferedReader.readLine();
            bufferedReader.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenidoArchivo;
    }

    private void guardarArchivoMemExterna(String textoArchivo){
        String nombreArchivo = "archivoAndoid.txt";
        String rutaSDCARD = "";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            rutaSDCARD = getExternalFilesDir(null).getAbsolutePath();

        File file = new File(rutaSDCARD + "/" + nombreArchivo);
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream= new FileOutputStream(file);
            if(fileOutputStream != null){

                fileOutputStream.write(textoArchivo.getBytes());
                fileOutputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String leerArchivoMemExterna(){
        String nombreArchivo = "archivoAndoid.txt";
        String rutaSDCARD = "";
        String contenidoArchivo = "";
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            rutaSDCARD = getExternalFilesDir(null).getAbsolutePath();

        try {
            File file = new File(rutaSDCARD + "/" + nombreArchivo);
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            contenidoArchivo = bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contenidoArchivo;

    }
}
