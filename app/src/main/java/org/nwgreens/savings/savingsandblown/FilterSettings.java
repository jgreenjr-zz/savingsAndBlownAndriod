package org.nwgreens.savings.savingsandblown;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

public class FilterSettings extends AppCompatActivity {

    TableRow onlyCurrent;
    TableRow nextSevenDays;
    TableRow allTransactions;

    ImageView onlyCurrentImg;
    ImageView nextSevenDaysImg;
    ImageView allTransactionsImg;

    TableRow currentBalance;
    TableRow actualBalance;


    ImageView currentBalanceImg;
    ImageView actualBalanceImg;


    int selectedView;
    int unselectedView;
    SharedPreferences myPrefFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_settings);

        final int selectedView = getResources().getIdentifier("@android:drawable/radiobutton_on_background", null, null);
        final int unselectedView = getResources().getIdentifier("@android:drawable/radiobutton_off_background", null, null);

        myPrefFile= getSharedPreferences("MyPrefFile", 0);

        onlyCurrent = (TableRow) findViewById(R.id.currentOnly);
        onlyCurrentImg = (ImageView) onlyCurrent.findViewById(R.id.img);
        onlyCurrent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlyCurrentImg.setImageResource(unselectedView);
                nextSevenDaysImg.setImageResource(unselectedView);
                allTransactionsImg.setImageResource(unselectedView);

                ImageView currentImage = (ImageView) v.findViewById(R.id.img);
                currentImage.setImageResource(selectedView);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("DateFilter", v.getId());
                edit.apply();
                edit.commit();
            }
        });


        nextSevenDays = (TableRow) findViewById(R.id.NextSeven);
        nextSevenDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlyCurrentImg.setImageResource(unselectedView);
                nextSevenDaysImg.setImageResource(unselectedView);
                allTransactionsImg.setImageResource(unselectedView);

                ImageView currentImage = (ImageView) v.findViewById(R.id.img);
                currentImage.setImageResource(selectedView);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("DateFilter", v.getId());
                edit.apply();
                edit.commit();
            }
        });
        nextSevenDaysImg = (ImageView) nextSevenDays.findViewById(R.id.img);

        allTransactions = (TableRow) findViewById(R.id.allTransactions);
        allTransactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlyCurrentImg.setImageResource(unselectedView);
                nextSevenDaysImg.setImageResource(unselectedView);
                allTransactionsImg.setImageResource(unselectedView);

                ImageView currentImage = (ImageView) v.findViewById(R.id.img);
                currentImage.setImageResource(selectedView);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("DateFilter", v.getId());
                edit.apply();
                edit.commit();
            }
        });
        allTransactionsImg = (ImageView) allTransactions.findViewById(R.id.img);

        currentBalance = (TableRow) findViewById(R.id.currentBalance);
        currentBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBalanceImg.setImageResource(unselectedView);
                actualBalanceImg.setImageResource(unselectedView);

                ImageView currentImage = (ImageView) v.findViewById(R.id.img);
                currentImage.setImageResource(selectedView);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("DisplayMode", v.getId());
                edit.apply();
                edit.commit();
            }
        });
        currentBalanceImg = (ImageView) currentBalance.findViewById(R.id.img);

        actualBalance = (TableRow) findViewById(R.id.actualBalance);
        actualBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentBalanceImg.setImageResource(unselectedView);
                actualBalanceImg.setImageResource(unselectedView);

                ImageView currentImage = (ImageView) v.findViewById(R.id.img);
                currentImage.setImageResource(selectedView);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("DisplayMode", v.getId());
                edit.apply();
                edit.commit();
            }
        });
        actualBalanceImg = (ImageView) actualBalance.findViewById(R.id.img);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        int i = myPrefFile.getInt("DateFilter", R.id.currentOnly);

        TableRow tr = (TableRow) findViewById(i);
        ImageView iView = (ImageView) tr.findViewById(R.id.img);


        iView.setImageResource(selectedView);

        int i2 = myPrefFile.getInt("DisplayMode", R.id.actualBalance);

        TableRow tr2 = (TableRow) findViewById(i2);
        ImageView iView2 = (ImageView) tr2.findViewById(R.id.img);
        iView2.setImageResource(selectedView);
    }
}
