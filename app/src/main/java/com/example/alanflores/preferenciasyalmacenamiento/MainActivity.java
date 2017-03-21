package com.example.alanflores.preferenciasyalmacenamiento;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alanflores.preferenciasyalmacenamiento.DB.SQLiteActivity;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        String[] opciones = {
                "Shared preferences",
                "Shared preferences activity",
                "Almacenamienro de archivos",
                "Bases de datos"
        };

        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, opciones));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        Intent intent = new Intent(getApplicationContext(),SharedPreferencesActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent1 = new Intent(getApplicationContext(),SharedPreferencesScreenActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(getApplicationContext(),AlmacenamientoInternoExterno.class);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(getApplicationContext(), SQLiteActivity.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }
}
