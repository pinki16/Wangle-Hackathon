package com.jaya.hackthaonproject;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Allocated extends AppCompatActivity {
    String result;
    TextView tx;
    AllocatedResAdapter ca;
    ListView listView;
    String url="http://www.techdrona.net/tech/Hack/allocated.php";
    @Override
       protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allocated);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Allocated Resources");
        ca = new AllocatedResAdapter(this, R.layout.allocated_row);
        listView = (ListView) findViewById(R.id.list_view_alocated);
        listView.setAdapter(ca);
       // ShowviewssDatas show = new ShowviewssDatas(this);
       // show.execute();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Allocated.this," In volley",Toast.LENGTH_LONG).show();
                parse(response);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Allocated.this,"Error in volley",Toast.LENGTH_LONG).show();

            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addToReqQueue(stringRequest);
    }

    //commenting out async class
    /*
    class ShowviewssDatas extends AsyncTask<String, String, String> {
        String json_string;
        String json_url;
        Context ctx;

        ShowviewssDatas(Context ctx) {
            this.ctx = ctx;


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            json_url = "http://www.techdrona.net/tech/Hack/allocated.php";

        }

        @Override
        protected String doInBackground(String... params) {

            try {

                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                StringBuffer buffer = new StringBuffer();
                while ((line = bufferedReader.readLine()) != null) {
                    buffer.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return buffer.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

        }

        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            result = s;


            parse(ctx);


        }


        void parse(Context ctx) {
            JSONObject jsonObject;
            JSONArray jsonArray;


            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;
                String Resource_Type;
                String No_Of_Resources;

                while (count < jsonArray.length()) {

                    JSONObject jo = jsonArray.getJSONObject(count);
                    Resource_Type=jo.getString("Resource_Type");
                    No_Of_Resources = jo.getString("No_Of_Resources");

                    AllocatedRes c = new AllocatedRes( Resource_Type, No_Of_Resources);
                    ca.add(c);
                    count++;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/

    //json parse for volley's json
    void parse(String result) {
        JSONObject jsonObject;
        JSONArray jsonArray;


        try {
            jsonObject = new JSONObject(result);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            String Resource_Type;
            String No_Of_Resources;

            while (count < jsonArray.length()) {

                JSONObject jo = jsonArray.getJSONObject(count);
                Resource_Type=jo.getString("Resource_Type");
                No_Of_Resources = jo.getString("No_Of_Resources");

                AllocatedRes c = new AllocatedRes( Resource_Type, No_Of_Resources);
                ca.add(c);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
