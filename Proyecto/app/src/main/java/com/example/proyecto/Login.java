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

public class Login extends AppCompatActivity {
EditText t_user,t_passw;
String str_user,str_passw;
String url= "http://192.168.3.2/login_/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t_user = findViewById(R.id.txtUser);
        t_passw = findViewById(R.id.txtpassw);

    }
    public void login(View view){
        if(t_user.getText().toString().equals("")){
            Toast.makeText(this, "Ingresar nombre de usuario", Toast.LENGTH_SHORT).show();
        }else if(t_passw.getText().toString().equals("")){
            Toast.makeText(this, "Ingresar password de usuario", Toast.LENGTH_SHORT).show();
        }else{
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("por favor espere");
            progressDialog.show();

            str_user = t_user.getText().toString().trim();
            str_passw = t_passw.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    if (response.equalsIgnoreCase("ingreso correctamente")) {
                        t_user.setText("");
                        t_passw.setText("");
                        startActivity(new Intent(getApplicationContext(), home.class));
                    } else {
                        Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Login.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
                    params.put("userName",str_user);
                    params.put("passw",str_passw);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
            requestQueue.add(request);
        }
    }

    public void registro(View view){
        startActivity(new Intent(getApplicationContext(),registro.class));
        finish();
    }
}