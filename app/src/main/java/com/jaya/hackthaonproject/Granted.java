package com.jaya.hackthaonproject;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class Granted extends AppCompatActivity {


    ListView listView;
    String location_id;
    String url = "http://www.techdrona.net/tech/Hack/GrantedResource.php";
    GrantedResAdapter f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_granted);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Granted Demands");

        listView = (ListView) findViewById(R.id.listview_granted);
        f = new GrantedResAdapter(getApplicationContext(), R.layout.granted_row);
        listView.setAdapter(f);
        location_id = LoginActivity.loc_id;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Granted.this," In volley",Toast.LENGTH_LONG).show();
                parse(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Granted.this, "Error in volley", Toast.LENGTH_LONG).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("loc_id", location_id);
                return params;
            }

        };

        VolleySingleton.getInstance(getApplicationContext()).addToReqQueue(stringRequest);
        //  ShowGranted showGranted = new ShowGranted();
        //  showGranted.execute(location_id);
    }


    //getting data
 /* private class ShowGranted extends AsyncTask<String, String, String> {
        StringBuffer buffer = new StringBuffer();

        @Override
        protected String doInBackground(String... params) {
            try {
                String first = params[0];
                URL url = new URL("http://www.techdrona.net/tech/Hack/GrantedResource.php");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String data = URLEncoder.encode("loc_id", "UTF-8") + "=" + URLEncoder.encode(first, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // reading from the server

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";

                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer.toString().trim();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            parse(s);
        }
    }

    //parsing JSON
    void parse(String j) {
        JSONObject jsonObject;
        JSONArray jsonArray;


        try {
            jsonObject = new JSONObject(j);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String Resource_Type;
            String No_Of_Resources;
            String Demand_Id;
            String Date_Of_Demand;

            while (count < jsonArray.length()) {

                JSONObject jo = jsonArray.getJSONObject(count);
                Resource_Type = jo.getString("Resource_Type");
                No_Of_Resources = jo.getString("No_Of_Resources");
                Demand_Id = jo.getString("Demand_Id");
                Date_Of_Demand = jo.getString("Date_Of_Demand");
                GrantedRes c = new GrantedRes(Demand_Id, Resource_Type, No_Of_Resources, Date_Of_Demand);
                f.add(c);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    */

    void parse(String j) {
        JSONObject jsonObject;
        JSONArray jsonArray;


        try {
            jsonObject = new JSONObject(j);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String Resource_Type;
            String No_Of_Resources;
            String Demand_Id;
            String Date_Of_Demand;

            while (count < jsonArray.length()) {

                JSONObject jo = jsonArray.getJSONObject(count);
                Resource_Type = jo.getString("Resource_Type");
                No_Of_Resources = jo.getString("No_Of_Resources");
                Demand_Id = jo.getString("Demand_Id");
                Date_Of_Demand = jo.getString("Date_Of_Demand");
                GrantedRes c = new GrantedRes(Demand_Id, Resource_Type, No_Of_Resources, Date_Of_Demand);
                f.add(c);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}


