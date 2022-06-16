package com.example.proyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    public static ArrayList<Personaje>personajeArrayList = new ArrayList<>();

    String url="http://192.168.3.2/login_/mostrar.php";
    Personaje personaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView=findViewById(R.id.listMostrar);
        adapter = new Adapter(this,personajeArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem = {"ver datos","editar datos","eliminar datos"};
                builder.setTitle(personajeArrayList.get(position).getNombre());
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                           startActivity(new Intent(getApplicationContext(),detalles.class).putExtra("position",
                                   position));
                                break;
                            case 1:
                                startActivity(new Intent(getApplicationContext(),editar.class).putExtra("position",
                                        position));
                                break;
                            case 2:
                                EliminarDatos(personajeArrayList.get(position).getIdPersonaje());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ListarDatos();
    }

    private void EliminarDatos(final String idPersonaje){
        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.3.2/login_/eliminar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equalsIgnoreCase("datos eliminados")){
                        Toast.makeText(home.this, "eliminado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), home.class));
                    }else{
                        Toast.makeText(home.this, "no se puede eliminar", Toast.LENGTH_SHORT).show();
                         }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(home.this, "error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("idPersonaje",idPersonaje);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void ListarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                personajeArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String exito = jsonObject.getString("exito");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (exito.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String idPersonaje = object.getString("idPersonaje");
                            String nombre = object.getString("nombre");
                            String edad = object.getString("edad");
                            String descripcion = object.getString("descripcion");

                            personaje = new Personaje(idPersonaje, nombre, edad, descripcion);
                            personajeArrayList.add(personaje);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(home.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    public void insertar(View view){
        startActivity(new Intent(getApplicationContext(),agregar.class));
    }
}