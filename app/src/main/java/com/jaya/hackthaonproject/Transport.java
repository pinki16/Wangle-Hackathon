package com.jaya.hackthaonproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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

public class Transport extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
   // String result;
    ListView listView;
    TransportResAdapter ca;
    String des_id,demand_id, res_type,no, expno;
    TextView type_tx, id_tx,no_tx;
    String url= "http://www.techdrona.net/tech/Hack/transporter.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_transport);

        setContentView(R.layout.activity_transporter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_admin);
        setSupportActionBar(toolbar);
        ////////////////////////////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);
///////////////////////////////////////////////////////////////



        ca = new TransportResAdapter(this, R.layout.transport_row);
        listView = (ListView) findViewById(R.id.listview_transport);
        listView.setAdapter(ca);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String type,dem_id;
                type_tx= (TextView) view.findViewById(R.id.res_type_transport);
                id_tx= (TextView) view.findViewById(R.id.demand_id_transport);
                no_tx= (TextView) view.findViewById(R.id.res_no_transport);
                dem_id= id_tx.getText().toString();
                type= type_tx.getText().toString();
                expno=no_tx.getText().toString();
                transport(dem_id,type);


            }
        });
       // TrackResources show = new TrackResources(this);
        //show.execute();
        StringRequest stringRequest= new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Allocated.this," In volley",Toast.LENGTH_LONG).show();
                parse(response);

            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Transport.this,"Error in volley",Toast.LENGTH_LONG).show();

            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addToReqQueue(stringRequest);


    }
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_admin) {
            Intent i = new Intent(this, LoginActivity.class);
            i.putExtra("error"," ");
            finish();
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();




         if (id == R.id.allocated_pc) {

            Intent i=new Intent(this,Allocated.class);
            startActivity(i);

        }
        else if (id == R.id.track) {
            Intent i=new Intent(this,InTransient.class);
            startActivity(i);


        }else if (id == R.id.transported_resources_pc) {
            Intent i=new Intent(this,Track.class);
            startActivity(i);

        } else if (id == R.id.allocate_demand_pc) {
            Intent i=new Intent(this,ViewDemands.class);
            startActivity(i);

        } else if (id == R.id.nav_create_sm_pc) {
            Intent i = new Intent(this, RegisterSM.class);
            startActivity(i);
        } else if (id == R.id.view_sm_database_pc) {
            Intent i = new Intent(this, ShowSM.class);
            startActivity(i);

        } else if (id == R.id.nav_guidelines) {

        }else if (id == R.id.preempt_pc) {
            Intent i=new Intent(this,Manual.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_admin);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    void transport(final String dem_id, final String type){
        AlertDialog.Builder builder = new AlertDialog.Builder(Transport.this);
        builder.setTitle("Confirmation").setMessage("Proceed to process Demand ID : "+dem_id);
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(Transport.this,ResLoc.class);
                intent.putExtra("type",type);
                intent.putExtra("no",expno);
                intent.putExtra("demand_id",dem_id);
                startActivity(intent);

            }
        });
        builder.setNegativeButton("Cancel",null);

        AlertDialog dialog= builder.create();
        dialog.show();

    }


    /*class TrackResources extends AsyncTask<String, String, String> {
        String json_string;
        String json_url;
        Context ctx;

        TrackResources(Context ctx) {
            this.ctx = ctx;


        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            json_url = "http://www.techdrona.net/tech/Hack/transporter.php";

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


        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            parse(s);


        }


        void parse(String result) {
            JSONObject jsonObject;
            JSONArray jsonArray;


            try {
                jsonObject = new JSONObject(result);
                jsonArray = jsonObject.getJSONArray("server_response");
                int count = 0;
                while (count < jsonArray.length()) {

                    JSONObject jo = jsonArray.getJSONObject(count);
                    demand_id = jo.getString("Demand_Id");
                    des_id = jo.getString("Destination_Id");
                    no = jo.getString("No_Of_Resources");
                    res_type = jo.getString("Resource_Type");


                    TransportRes c = new TransportRes(demand_id, res_type, no, des_id);
                    ca.add(c);
                    count++;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }*/

    void parse(String result) {
        JSONObject jsonObject;
        JSONArray jsonArray;


        try {
            jsonObject = new JSONObject(result);
            jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0;
            while (count < jsonArray.length()) {

                JSONObject jo = jsonArray.getJSONObject(count);
                demand_id = jo.getString("Demand_Id");
                des_id = jo.getString("Destination_Id");
                no = jo.getString("No_Of_Resources");
                res_type = jo.getString("Resource_Type");


                TransportRes c = new TransportRes(demand_id, res_type, no, des_id);
                ca.add(c);
                count++;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
