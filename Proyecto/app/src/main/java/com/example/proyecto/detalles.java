package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detalles extends AppCompatActivity {
TextView tv1,tv2,tv3,tv4;
int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        tv1=findViewById(R.id.tvid);
        tv2=findViewById(R.id.tvnombre);
        tv3=findViewById(R.id.tvedad);
        tv4=findViewById(R.id.tvdescripcion);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("position");

        tv1.setText("IdPersonaje    " + home.personajeArrayList.get(position).getIdPersonaje());
        tv2.setText("Nombre     " + home.personajeArrayList.get(position).getNombre());
        tv3.setText("Edad   " + home.personajeArrayList.get(position).getEdad());
        tv4.setText("Descripcion  " + home.personajeArrayList.get(position).getDescripcion());
    }
}