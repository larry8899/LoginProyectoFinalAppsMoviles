package com.example.proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Personaje> {

    Context context;
    List<Personaje> arrayPersonaje;
    public Adapter(@NonNull Context context, List<Personaje> arrayPersonaje) {
        super(context, R.layout.list_personaje,arrayPersonaje);
        this.context = context;
        this.arrayPersonaje = arrayPersonaje;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_personaje,null,true);

        TextView txtID = view.findViewById(R.id.txtid);
        TextView name = view.findViewById(R.id.txtName);


        name.setText(arrayPersonaje.get(position).getNombre());
        txtID.setText(arrayPersonaje.get(position).getIdPersonaje());
        return view;
    }
}
