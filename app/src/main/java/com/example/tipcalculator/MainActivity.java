package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText bilLTotalWithTax;
    private TextView tipAmount;
    private TextView totalWithTip;
    private EditText NumPeople;
    private TextView totalPerPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: This is in onCreate!");
        bilLTotalWithTax = findViewById(R.id.billTotalNumberView);
        tipAmount = findViewById(R.id.tipAmountNumber);
        totalWithTip = findViewById(R.id.totalwithTipView);
        NumPeople = findViewById(R.id.NumPeopleNumber);
        totalPerPerson = findViewById(R.id.totalTextView);
    }

    public void doCalculate(View v){
        Log.d(TAG, "doCalculate: ");
        String billTotalWithTaxValue = bilLTotalWithTax.getText().toString();
        String tipAmountValue =  tipAmount.getText().toString();
        String totalWithTipValue = totalWithTip.getText().toString();
        String NumPeopleValue = NumPeople.getText().toString();
        String totalPerPersonValue = totalPerPerson.getText().toString();
        int result = 0;
        //include scenario
        //if the bill total is empty, selecting a tip percentage should do nothing
        //and the selected tip percentage radio button should then be automatically un-checked
        if (billTotalWithTaxValue.isEmpty()){
            return;
        }
        Log.d(TAG, "billTotalWithTaxValue: " +billTotalWithTaxValue);
        //Log.d(TAG, "tipAmountValue: " +tipAmountValue);
        //Log.d(TAG, "totalWithTipValue: " +totalWithTipValue);
        Log.d(TAG, "NumPeopleValue: " +NumPeopleValue);
        //Log.d(TAG, "totalPerPersonValue: "+totalPerPersonValue);


        //Log.d(TAG, "doClear: "+ String.format("$%.2f", 100.15591));
    }

    public void doClear(View v){
        Log.d(TAG, "doClear: ");
        bilLTotalWithTax.setText("");
        NumPeople.setText("");
    }
}