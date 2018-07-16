package com.example.trupti.volley_with_networkimage;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String,String>> iteamlist;
    RecyclerView recyclerView;
    HashMap<String,String>map;
    ProgressDialog progressDialog;
    String url,Name,Mobile,Education,Photo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerdemo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        iteamlist=new ArrayList<HashMap<String, String>>();
        iteamlist=new ArrayList<>();
        LoadUrl();
    }

    private void LoadUrl() {
        url="http://24variyasamaj.com/includes/loader_functions.php?Action=getEducationCommittee";

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject=new JSONObject(response);

                   // Log.d("data",response);
                    JSONArray jsonArray=jsonObject.getJSONArray("data");
                    for (int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObjecta=jsonArray.getJSONObject(i);


                        map=new HashMap<>();

                        Name=jsonObjecta.getString("Name");
                        map.put("Name",Name);

                        Mobile=jsonObjecta.getString("Mobile");
                        map.put("Mobile",Mobile);

                        Education=jsonObjecta.getString("Education");
                        map.put("Education",Education);

                        Photo=jsonObjecta.getString("Photo");
                        map.put("Photo",Photo);

                        iteamlist.add(map);


                     /*   HashMap<String,String> param=new HashMap<>();
                        param.put("Name",jsonObjecta.getString("Name"));
                        param.put("Mobile",jsonObjecta.getString("Mobile"));
                        param.put("Education",jsonObjecta.getString("Education"));
                        param.put("Photo",jsonObjecta.getString("Photo"));
*/

                    }

                    MyAdpatre myAdpatre= new MyAdpatre(iteamlist,getApplicationContext());
                    recyclerView.setAdapter(myAdpatre);

                    progressDialog.dismiss();


                } catch (JSONException e) {
                    progressDialog.dismiss();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressDialog.dismiss();
            }
        });


        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        progressDialog=progressDialog.show(MainActivity.this," please wait for the time",null,true,true);
        progressDialog.setMessage(" fetching data");
        progressDialog.setCancelable(false);
    }
}
