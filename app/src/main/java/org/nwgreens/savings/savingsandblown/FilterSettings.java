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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;

public class FilterSettings extends AppCompatActivity {


    RadioGroup stateFilter;
    RadioGroup dateFilter;
    RadioGroup balanceDisplay;
    SharedPreferences myPrefFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_settings);

        myPrefFile= getSharedPreferences("MyPrefFile", 0);

        stateFilter = (RadioGroup) findViewById(R.id.filterState);

        stateFilter.check(myPrefFile.getInt("StateFilter", R.id.AllState));

        stateFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("StateFilter", rb.getId());
                edit.apply();
            }
        });

        dateFilter = (RadioGroup) findViewById(R.id.DateFilter);
        dateFilter.check(myPrefFile.getInt("DateFilter", R.id.allTransactions));
        dateFilter.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("DateFilter", rb.getId());
                edit.apply();
            }
        });

        balanceDisplay = (RadioGroup) findViewById(R.id.balanceDisplay);
        balanceDisplay.check(myPrefFile.getInt("DisplayMode", R.id.actualBalance));
        balanceDisplay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                SharedPreferences.Editor edit = myPrefFile.edit();
                edit.putInt("DisplayMode", rb.getId());
                edit.apply();
            }
        });


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

    }
}
