package com.example.alanflores.preferenciasyalmacenamiento.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

/**
 * Created by alan.flores on 12/14/16.
 */

public class HelperPersona extends SQLiteOpenHelper {

    private static HelperPersona helperPersona = null;

    private static final String NOMBRE_BD = "BD";
    private static final int VERSION_DB = 1;

    public static class TablaPersona{
        public static String TABLA = "tablaPersona";
        public static String COLUMNA_ID = "id";
        public static String COLUMNA_NOMBRE = "nombre";
        public static String COLUMNA_APELLIDO = "apellido";
        public static String COLUMNA_EDAD = "edad";
    }

    private static final String CONSULTA_CREAR_TABLA = "create table "
            + TablaPersona.TABLA + "("
            + TablaPersona.COLUMNA_ID + " integer primary key autoincrement, "
            + TablaPersona.COLUMNA_NOMBRE + " VARCHAR, "
            + TablaPersona.COLUMNA_APELLIDO + " VARCHAR, "
            + TablaPersona.COLUMNA_EDAD + " INTEGER); ";

    private static final String CONSULTA_ELIMINAR_TABLA = "delete table if exists" + TablaPersona.TABLA;

    public static HelperPersona getINstance(Context context){
        if(helperPersona == null){
            helperPersona = new HelperPersona(context.getApplicationContext(),NOMBRE_BD,null,VERSION_DB);
        }
        return helperPersona;
    }


    HelperPersona(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CONSULTA_CREAR_TABLA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CONSULTA_ELIMINAR_TABLA);
        onCreate(sqLiteDatabase);
    }
}
