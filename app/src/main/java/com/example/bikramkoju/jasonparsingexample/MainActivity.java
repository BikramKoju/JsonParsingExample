package com.example.bikramkoju.jasonparsingexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView result;
    String url= "https://raw.githubusercontent.com/ianbar20/JSON-Volley-Tutorial/master/Example-JSON-Files/Example-Array.JSON";
    RequestQueue requestQueue;
    String mydata=" ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result=(TextView)findViewById(R.id.display);

        requestQueue= Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest stringRequest=new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject colorobject = response.getJSONObject(0);
                    JSONArray colorarray = colorobject.getJSONArray("colorArray");
                    for (int i = 0; i < colorarray.length(); i++) {
                        JSONObject dataobj = colorarray.getJSONObject(i);
                        String color = dataobj.getString("colorName");
                        String hex = dataobj.getString("hexValue");
                        mydata = mydata + " colorname= " + color + "    " + "hex= " + hex + "\n\n";
                         }
                         result.setText(mydata);

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this,"No Internet Connection",Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(stringRequest);





    }
}
