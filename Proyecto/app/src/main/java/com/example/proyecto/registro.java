package com.example.proyecto;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
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

public class registro extends AppCompatActivity {
EditText t_user, t_passw;
Button b_insertar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        t_user= findViewById(R.id.txtuser);
        t_passw = findViewById(R.id.txtpassword);
        b_insertar = findViewById(R.id.btnRegistro);

        b_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDatos();
            }
        });
    }
    private void insertarDatos(){
        final String userName = t_user.getText().toString().trim();
        final String password = t_passw.getText().toString().trim();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando");

        if(userName.isEmpty()){
            t_user.setError("Complete los campos");
            return;
        }else if(password.isEmpty()){
            t_passw.setError("Complete los campos");
        } else{
            progressDialog.show();
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.3.2/login_/insertar.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("se registro correctamente")) {
                        Toast.makeText(registro.this, "datos insertados", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Intent intent = new Intent(registro.this, Login.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(registro.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(registro.this, "No se pudo insertar", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(registro.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String>params= new HashMap<>();
                    params.put("userName",userName);
                    params.put("passw",password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(registro.this);
            requestQueue.add(request);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void login(View view){
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }
}