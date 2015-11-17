package org.nwgreens.savings.savingsandblown;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;

public class BanksOverview extends AppCompatActivity {

    public CookieManager mCookieManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks_overview);

       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();

        mCookieManager = new CookieManager();

        CookieHandler.setDefault(mCookieManager);

        URI uri = URI.create("http://10.0.0.13/");
        mCookieManager.getCookieStore().add(uri, new HttpCookie("sessionid", i.getStringExtra("sessionId")));

        TextView loggedInUser = (TextView) findViewById(R.id.LoggedInUsername);
        loggedInUser.setText(i.getStringExtra("username"));



    }

}
