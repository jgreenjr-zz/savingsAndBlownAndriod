package org.nwgreens.savings.savingsandblown;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AddTransaction extends AppCompatActivity {

    EditText etDate;

    EditText etPayee;

    EditText etAmount;

    Switch swTipNeeded;

    String currentBank = "";

    Spinner sr;

    Transaction currentTransaction = new Transaction();

    String[] status = {"Pending", "Cleared"};
    String[] type = {"widthdrawl", "deposit"};
    private Spinner spType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Intent currentIntent = getIntent();


        setTitle("Add Transaction");

        sr = (Spinner) findViewById(R.id.Status);
        spType = (Spinner) findViewById(R.id.Type);
        etDate = (EditText) findViewById(R.id.Date);
        etAmount = (EditText) findViewById(R.id.Amount);
        etPayee = (EditText) findViewById(R.id.Payee);
        swTipNeeded = (Switch) findViewById(R.id.Flag);


        ArrayAdapter spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,status);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sr.setAdapter(spinnerAdapter);

        ArrayAdapter spinnerAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,type);
        spinnerAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spType.setAdapter(spinnerAdapter2);

        currentBank = currentIntent.getStringExtra("currentBank").replace("\n", "");
        if(currentIntent.hasExtra("editedGson")){
            String gson = currentIntent.getStringExtra("editedGson");
            currentTransaction = new Gson().fromJson(gson, Transaction.class);

            sr.setSelection(Arrays.asList(status).indexOf(currentTransaction.getStatus()));
            spType.setSelection(Arrays.asList(type).indexOf(currentTransaction.getType()));
            etDate.setText(currentTransaction.getDate());
            etAmount.setText(String.format("%.2f%n", currentTransaction.getAmount()));
            etPayee.setText(currentTransaction.getPayee());
            swTipNeeded.setChecked(currentTransaction.isTipNeeded());
        }


        Button btnAdd = (Button) findViewById(R.id.Save);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySingleton  singleton = MySingleton.getInstance(getApplicationContext());

                StringRequest transactionGsonRequest = new StringRequest(Request.Method.PUT,
                        String.format("%sbanks/%s", getApplicationContext().getResources().getText(R.string.url), currentBank), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast t = Toast.makeText(getApplicationContext(), String.format("%s added!", etPayee.getText()), Toast.LENGTH_LONG);
                                t.show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("currentBank", currentBank);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast t = Toast.makeText(getApplicationContext(), String.format("%s failed!", etPayee.getText()), Toast.LENGTH_LONG);
                                        t.show();
                            }
                        }
                ) {
                    @Override
                    public byte[] getBody() throws AuthFailureError {

                        currentTransaction.setAmount(Double.parseDouble(etAmount.getText().toString()));
                        currentTransaction.setDate(etDate.getText().toString());
                        currentTransaction.setPayee(etPayee.getText().toString());
                        currentTransaction.setStatus(status[sr.getSelectedItemPosition()]);
                        currentTransaction.setType(type[spType.getSelectedItemPosition()]);
                        currentTransaction.setTipNeeded(swTipNeeded.isChecked());
                        currentTransaction.setBalance(null);
                        Gson g = new Gson();
                        String s = g.toJson(currentTransaction);
                        return s.getBytes();
                    }
                    @Override
                    public String getBodyContentType() {
                        return "application/json";
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError
                    {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json");
                        headers.put("accepts", "application/json");
                        return headers;
                    }
                };
                transactionGsonRequest.setRetryPolicy(new DefaultRetryPolicy(
                        20000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                singleton.addToRequestQueue(transactionGsonRequest);
            }
        });
    }

}
