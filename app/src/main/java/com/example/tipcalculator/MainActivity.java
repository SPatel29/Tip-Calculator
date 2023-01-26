package com.example.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private EditText bilLTotalWithTax;
    private TextView tipAmount;
    private TextView totalWithTip;
    private EditText NumPeople;
    private TextView totalPerPerson;
    private RadioGroup tipGroup;
    private double tip = 0;

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
        tipGroup = findViewById(R.id.tipRadioGroup);
    }

    public void doCalculate(View v){
        Log.d(TAG, "doCalculate: ");
        String billTotalWithTaxValue = bilLTotalWithTax.getText().toString();
        if (billTotalWithTaxValue.isEmpty()){
            return;
        }
        String NumPeopleValue = NumPeople.getText().toString();
        //include scenario
        //if the bill total is empty, selecting a tip percentage should do nothing
        //and the selected tip percentage radio button should then be automatically un-checked

        double tipAmountValue = Double.parseDouble(billTotalWithTaxValue) * tip;
        double totalWithTipValue = tipAmountValue + Double.parseDouble(billTotalWithTaxValue);
        int numberOfPeople = Integer.parseInt(NumPeopleValue);
        Log.d(TAG, "tipAmountValue: " + tipAmountValue);
        tipAmount.setText(String.format("$%.2f", tipAmountValue));
        totalWithTip.setText(String.format("$%.2f", totalWithTipValue));
        totalPerPerson.setText(String.format("$%.2f", totalWithTipValue/numberOfPeople));
        Log.d(TAG, "totalPerPerson: "+ totalWithTipValue/numberOfPeople);
        Log.d(TAG, "totalWithTip "+ totalWithTipValue);
        Log.d(TAG, "billTotalWithTaxValue: " +billTotalWithTaxValue);
        Log.d(TAG, "NumPeopleValue: " +NumPeopleValue);
    }

    public void getTipValue(View v){
        if (v.getId() == R.id.tweleveRadio){
            tip = 0.12;
        }else if(v.getId() == R.id.fifteenRadio){
            tip = 0.15;
        }else if(v.getId() == R.id.eighteenRadio){
            tip = 0.18;
        }else if(v.getId() == R.id.twentyRadio){
            tip = 0.20;
        }else{
            Log.d(TAG, "getTipValue: something went wrong");
        }
        Log.d(TAG, "tip value is: "+ tip);
    }

    public void doClear(View v){
        Log.d(TAG, "doClear: ");
        bilLTotalWithTax.setText("");
        NumPeople.setText("");
        tipAmount.setText("");
        totalWithTip.setText("");
        totalPerPerson.setText("");
        tipGroup.clearCheck();
    }
}