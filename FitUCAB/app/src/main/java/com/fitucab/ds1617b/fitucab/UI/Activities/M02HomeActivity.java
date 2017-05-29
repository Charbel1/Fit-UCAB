package com.fitucab.ds1617b.fitucab.UI.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.fitucab.ds1617b.fitucab.Helper.IpStringConnection;
import com.fitucab.ds1617b.fitucab.R;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01HomeFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M02.M02AccountFragment;
import com.fitucab.ds1617b.fitucab.UI.Fragments.M02.M02HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

public class M02HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView _tv_m02_nombre;
    private TextView _tv_m02_peso;
    private String TAG= "FitUCAB";
    private IpStringConnection ip= new IpStringConnection();
    private FragmentManager FM = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m02_home);
        initcomponents();
    }

    private void initcomponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        _tv_m02_peso = (TextView) header.findViewById(R.id.tv_m02_peso);
        _tv_m02_nombre = (TextView) header.findViewById(R.id.tv_m02_nombre);

        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();
        fragmentToSwap = new M02HomeFragment();
        fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();
        toAskWebService();
    }

    private void toAskWebService() {
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        int id= preferences.getInt("idUser", user.get_idUser());
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String webUrl= ip.getIp()+"M02Users/1";
        Log.i(TAG, "toAskWebService: "+webUrl);
        JsonObjectRequest jsonrequest= new  JsonObjectRequest(Request.Method.GET, webUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i(TAG, "onResponse: "+response.toString());
                setJsonView(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, " ERROR"+ error.toString());
            }
        });
        requestQueue.add(jsonrequest);

    }

    private void setJsonView(JSONObject response) {
        try {

            String username= response.getString("user");
            //String height= response.getString("height");
            int weight= response.getInt("weight");
            Log.i(TAG, "setJsonView: "+ username+ " , "+weight);
            _tv_m02_nombre.setText(username);
            _tv_m02_peso.setText( "Peso: "+weight+" Kg");



        } catch (JSONException e) {

            e.printStackTrace();
        } catch (NullPointerException e){

            e.printStackTrace();
        }catch (Exception e){

            e.printStackTrace();
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragmentToSwap = null;
        FragmentTransaction fragmentTransaction = FM.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.nav_m02_home) {
            fragmentToSwap = new M02HomeFragment();
            fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();
            // Handle the camera action
        } else if (id == R.id.nav_m02_calories) {

        } else if (id == R.id.nav_m02_training) {

        } else if (id == R.id.nav_m02_logout) {
            Intent myintent = new Intent(M02HomeActivity.this, M01LoginActivity.class);
            startActivity(myintent);

        }
        else if (id == R.id.nav_m02_account) {
            fragmentToSwap = new M02AccountFragment();
            fragmentTransaction.replace(R.id.flContent_m02_home, fragmentToSwap).commit();

        }else if (id == R.id.nav_m02_activitys) {

        }
        else if (id == R.id.nav_m02_challenges) {

        }
        else if (id == R.id.nav_m02_friends) {

        }
        else if (id == R.id.nav_m02_gamification) {

        }
        else if (id == R.id.nav_m02_hydration) {
//            Intent myintent = new Intent(M02HomeActivity.this, M10WaterGlassActivity.class);
//            startActivity(myintent);

        }
        else if (id == R.id.nav_m02_planing_activitys) {

        } else if (id == R.id.nav_m02_notifications) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
