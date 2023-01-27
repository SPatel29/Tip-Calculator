package com.example.tipcalculator;

import androidx.annotation.NonNull;
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

    private double totalWithTipValue = 0;
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
            tipGroup.clearCheck();
            return;
        }else if (v.getId() == R.id.tweleveRadio){
            tip = 0.12;
        }else if (v.getId() == R.id.fifteenRadio){
            tip = 0.15;
        }else if (v.getId() == R.id.eighteenRadio){
            tip  = 0.18;
        }else if (v.getId() == R.id.twentyRadio){
            tip = 0.20;
        }else{
            Log.d(TAG, "doCalculate: ERROR HAPPENED");
            return;
        }
        //include scenario
        //if the bill total is empty, selecting a tip percentage should do nothing
        //and the selected tip percentage radio button should then be automatically un-checked

        double tipAmountValue = Double.parseDouble(String.format("%.2f",Double.parseDouble(billTotalWithTaxValue) * tip));
        totalWithTipValue = tipAmountValue + Double.parseDouble(billTotalWithTaxValue);
        tipAmount.setText(String.format("$%.2f", tipAmountValue));
        totalWithTip.setText(String.format("$%.2f", totalWithTipValue));
        Log.d(TAG, "tipAmountValue: " + tipAmountValue);
        Log.d(TAG, "totalWithTip "+ totalWithTipValue);
        //Log.d(TAG, "billTotalWithTaxValue: " +billTotalWithTaxValue);
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

    public void calculateTotalPerPerson(View v){
        String NumPeopleValue = NumPeople.getText().toString();
        int numberOfPeople = Integer.parseInt(NumPeopleValue);
        double totalPerPersonValue = Double.parseDouble(String.format("%.2f",totalWithTipValue/numberOfPeople)); //this value needs to be rounded to two places
        if (totalPerPersonValue * numberOfPeople < totalWithTipValue){
            Log.d(TAG, "test "+ totalWithTipValue);
            Log.d(TAG, "test" + totalPerPersonValue * numberOfPeople);
            double difference = Double.parseDouble(String.format("%.2f",totalWithTipValue - (totalPerPersonValue * numberOfPeople)));
            Log.d(TAG, "DIFFERENCE: "+ difference);
            totalPerPersonValue = Double.parseDouble(String.format("%.2f",totalPerPersonValue + difference));
            Log.d(TAG, "AFTER CALCUlAIONS: "+ totalPerPersonValue);
        }
        totalPerPerson.setText(String.format("$%.2f", totalPerPersonValue));
        Log.d(TAG, "totalPerPerson: "+ totalWithTipValue/numberOfPeople);
        Log.d(TAG, "NumPeopleValue: " +NumPeopleValue);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        //outState.putString("NUM_PEOPLE",NumPeople.getText().toString());
        outState.putDouble("TIP",tip);
        outState.putString("TOTAL_PER_PERSON", totalPerPerson.getText().toString());
        outState.putString("TIP_AMOUNT", tipAmount.getText().toString());
        outState.putString("TOTAL_WITH_TIP", totalWithTip.getText().toString());
        outState.putDouble("TOTAL_WITH_TIP_VALUE", totalWithTipValue);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState); //take things out and put them back out
        tip = savedInstanceState.getDouble("TIP");
        totalPerPerson.setText(savedInstanceState.getString("TOTAL_PER_PERSON"));
        tipAmount.setText(savedInstanceState.getString("TIP_AMOUNT"));
        totalWithTip.setText(savedInstanceState.getString("TOTAL_WITH_TIP"));
        totalWithTipValue = savedInstanceState.getDouble("TOTAL_WITH_TIP_VALUE");
        //totalPerPerson.setText(savedInstanceState.getString("TIP"));
    }
}