package org.nwgreens.savings.savingsandblown;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 11/20/15.
 */
public class BankItemAdapter extends ArrayAdapter<Transaction> {
    int displayMode;

    public BankItemAdapter(Context context, ArrayList<Transaction> transactions, int displayMode) {
        super(context, 0, transactions);
        this.displayMode = displayMode;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Transaction t = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.bank_row, parent, false);
        }
        TextView tvPayee = (TextView) convertView.findViewById(R.id.Payee);
        tvPayee.setText(t.getPayeeView());

        TextView tvAmount = (TextView) convertView.findViewById(R.id.Amount);
        if(t.getType().equals( "deposit")) {
            tvAmount.setText(String.format("$%.2f%n", t.getAmount()));
        }
        else {
            tvAmount.setText(String.format("-$%.2f%n", t.getAmount()));
        }
        TextView tvBalance = (TextView) convertView.findViewById(R.id.Balance);

        tvBalance.setText(String.format("$%.2f%n", t.getBalance().getBalance(displayMode)));
        TextView tvDate = (TextView) convertView.findViewById(R.id.Date);
        tvDate.setText(t.getDate());

        TextView tvStatus = (TextView) convertView.findViewById(R.id.Status);
        tvStatus.setText(t.getStatus());

        return convertView;
    }
}
