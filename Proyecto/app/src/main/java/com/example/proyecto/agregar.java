package com.example.proyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class agregar extends AppCompatActivity {
EditText t_nombre,t_edad,t_descripcion;
Button b_agregar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        t_nombre = findViewById(R.id.txtNombre);
        t_edad = findViewById(R.id.txtEdad);
        t_descripcion = findViewById(R.id.txtDescripcion);
        b_agregar = findViewById(R.id.btnAgregar);
        b_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                agregarDatos();
            }
        });
    }

    private void agregarDatos() {
        final String nombre = t_nombre.getText().toString().trim();
        final String edad = t_edad.getText().toString().trim();
        final String descripcion = t_descripcion.getText().toString().trim();

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando");

        if(nombre.isEmpty()){
            Toast.makeText(this, "ingrese nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(edad.isEmpty()){
            Toast.makeText(this, "ingrese edad", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(descripcion.isEmpty()){
            Toast.makeText(this, "ingrese descripcion", Toast.LENGTH_SHORT).show();
            return;
        } else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.3.2/login_/agregar.php", new
                    Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.equalsIgnoreCase("datos insertados")) {
                                Toast.makeText(agregar.this, "Datos insertados", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(getApplicationContext(), home.class));
                                finish();
                            } else {
                                Toast.makeText(agregar.this, response, Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(agregar.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("nombre",nombre);
                    params.put("edad",edad);
                    params.put("descripcion",descripcion);

                    return params;
                }
            };
            RequestQueue requestQueue= Volley.newRequestQueue(agregar.this);
            requestQueue.add(request);
        }
    }
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }
}