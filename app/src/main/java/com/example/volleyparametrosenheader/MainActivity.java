package com.example.volleyparametrosenheader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.widget.TextView;
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
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebService.WebService;

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Nombre: Edison Ronaldo Untuña Mosquera
        //Curso: 9no Telematica
        final TextView textView = (TextView) findViewById(R.id.text);

        // ...

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.uealecpeterson.net/public/productos/search";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Mostramos los resultados.
                        textView.setText(response);
                        //Con este comando agregamos movimiento para ver todos los campos
                        textView.setMovementMethod(new ScrollingMovementMethod());



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("No conecta con la API!");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                Map<String, String> param = new HashMap<>();
                //Envio de parametros en el header
                param.put("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpZHVzciI6OSwiZW1haWwiOiJjemFtYnJhbm9AdXRlcS5lZHUuZWMiLCJpYXQiOjE2NzA4OTU5NTYsImV4cCI6MTY3MTI1NTk1Nn0.QLxDwcfu4t9fhxGeC3xyqvuDFJZSRw7LQvtU1X1VbF4");
                return param;
            }
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                //Enviamos el parametro fuente que es necesario para el funcionamiento de la API
                params.put("fuente","1");
                return params;
            }

        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }
}