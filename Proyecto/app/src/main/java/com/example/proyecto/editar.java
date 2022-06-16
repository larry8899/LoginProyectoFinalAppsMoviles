package com.example.proyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class editar extends AppCompatActivity {

    EditText edID,edNombre,edEdad,edDescripcion;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        
        edID = findViewById(R.id.textId);
        edNombre = findViewById(R.id.textnombre);
        edEdad = findViewById(R.id.textEdad);
        edDescripcion = findViewById(R.id.textDescripcion);

        Intent intent = getIntent();
        position=intent.getExtras().getInt("position");

        edID.setText(home.personajeArrayList.get(position).getIdPersonaje());
        edNombre.setText(home.personajeArrayList.get(position).getNombre());
        edEdad.setText(home.personajeArrayList.get(position).getEdad());
        edDescripcion.setText(home.personajeArrayList.get(position).getDescripcion());

    }
    public void actualizar(View view){
        final String idPersonaje = edID.getText().toString().trim();
        final String nombre = edNombre.getText().toString().trim();
        final String edad = edEdad.getText().toString().trim();
        final String descripcion = edDescripcion.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizado");
        progressDialog.dismiss();

        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.3.2/login_/actualizar.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(editar.this, response, Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), home.class));
                        finish();
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(editar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String,String> params = new HashMap<>();
               params.put("idPersonaje",idPersonaje);
                params.put("nombre",nombre);
                params.put("edad",edad);
                params.put("descripcion",descripcion);

                return params;
            }
        };
        RequestQueue requestQueue =  Volley.newRequestQueue(editar.this);
        requestQueue.add(request);
    }
}