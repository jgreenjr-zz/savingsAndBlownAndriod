package org.nwgreens.savings.savingsandblown;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public CookieManager mCookieManager;
    public NavigationView navigationView;
    public BankNameTask bankNameTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        SetupNavigationTray();




    }

    private void SetupNavigationTray() {
       navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        bankNameTask = new BankNameTask(getIntent());
        bankNameTask.execute((Void)null);
    }

    public class BankNameTask extends AsyncTask<Void, Void, Void>
    {
        Intent currentIntent;
        String[] banks = null;

        public BankNameTask(Intent i){
            currentIntent = i;
        }

        @Override
        protected Void doInBackground(Void... params) {
            RequestQueue rq = MySingleton.getInstance(getApplicationContext()).getRequestQueue();

            StringRequest sr = new StringRequest(Request.Method.GET,  String.format("%sbanks", getApplicationContext().getResources().getText(R.string.url)),
                     new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        Gson gson = new Gson();
                        BanksResponse banksResponse = gson.fromJson(response, BanksResponse.class);

                        banks = banksResponse.GetBanks();
                    }
                    catch(Exception ex){
                        banks = new String[0];
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    banks = new String[0];
                }
            }

            );
            rq.add(sr);
            return null;
        }

        @Override
        protected void onPostExecute(Void a)
        {
            Menu m = navigationView.getMenu();
            SubMenu subMenu = m.addSubMenu(Menu.NONE, Menu.NONE,1, "Banks");
            for(int i = 0; i < banks.length; i++){
                subMenu.add(banks[i]);
            }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      /*  getMenuInflater().inflate(R.menu.main, menu);
        String[] banks = new String[]{"bank1", "bank2"};

        MenuItem item = (MenuItem) menu.findItem(R.id.BankListSpinner);
        Spinner spinner = new Spinner(this);
        spinner.setBackgroundResource(R.color.colorPrimaryDark);
        spinner.set
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, banks);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        item.setActionView(spinner);
        */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
