package com.example.individual_assig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomCombineBreakdown extends AppCompatActivity {

    private EditText billTotal;
    private EditText NumOfPeople;
    private EditText custom;
    private CheckBox combine;
    private RadioButton percentage;
    private RadioButton amount;
    private Button calculateResult;
    private TextView breakdown;

    private databaseBill database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_combine_breakdown);

        billTotal = findViewById(R.id.billTotal);
        NumOfPeople = findViewById(R.id.NumOfPeople);
        custom = findViewById(R.id.custom);
        percentage = findViewById(R.id.percentage);
        amount = findViewById(R.id.amount);
        calculateResult = findViewById(R.id.calculateResult);
        breakdown = findViewById(R.id.breakdown);
        combine = findViewById(R.id.combine);

        database = new databaseBill(this);

        calculateResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String BillTotal = billTotal.getText().toString();
                String numOfPeople = NumOfPeople.getText().toString();
                String Custom = custom.getText().toString();



                if (BillTotal.isEmpty()) {
                    billTotal.setError("Please insert the total bill amount");
                    return;

                }
                if (numOfPeople.isEmpty()) {
                    NumOfPeople.setError("Please enter the total number of people");
                    return;
                }

                int NumberOfPeople;
                double TotalAmountBill;
                List<Double> SharesList = new ArrayList<>();

                try {
                    TotalAmountBill = Double.parseDouble(BillTotal);
                    NumberOfPeople = Integer.parseInt(numOfPeople);

                    if (percentage.isChecked()) {
                        if (Custom.isEmpty()) {
                            custom.setError("Please enter custom share amount");
                            return;

                        }
                        String[] ShareListArray = Custom.split(",");
                        for (String CustomShare : ShareListArray) {
                            double share = Double.parseDouble(CustomShare.trim());
                            SharesList.add(share);
                        }
                    } else if (amount.isChecked()) {
                        if (Custom.isEmpty()) {
                            custom.setError("Please enter custom share amount");
                            return;
                        }

                        double amountCustomShare = Double.parseDouble(Custom);
                        double finalAmount = TotalAmountBill / NumberOfPeople;

                        if (amountCustomShare != TotalAmountBill) {
                            custom.setError("Total custom shares need to match the total bill amount (RM"
                                    + TotalAmountBill + ")");
                        }

                        for (int a = 0; a < NumberOfPeople; a++) {
                            SharesList.add(finalAmount);
                        }
                    }
                } catch (NumberFormatException i) {
                    billTotal.setError("Invalid Bill!");
                    NumOfPeople.setError("Invalid Number of People!");
                    custom.setError("Invalid Custom!");
                    return;
                }
                double[] toPay = new double[NumberOfPeople];


                if (combine.isChecked()) {
                    double FixedAmount = 0;
                    for (int x = 0; x < SharesList.size(); x++) {
                        if (amount.isChecked()) {
                            FixedAmount += SharesList.get(x);
                        }
                    }
                    double remaining = TotalAmountBill - FixedAmount;

                    for (int x = 0; x < SharesList.size(); x++) {
                        if (percentage.isChecked()) {
                            double cusShare = SharesList.get(x) / 100;
                            toPay[x] = cusShare * remaining;
                        } else {
                            toPay[x] = SharesList.get(x);
                        }
                    }
                } else {
                    for (int x = 0; x < SharesList.size(); x++) {
                        if (percentage.isChecked()) {
                            double cusShare = SharesList.get(x) / 100;
                            toPay[x] = TotalAmountBill * cusShare;
                        } else {
                            toPay[x] = SharesList.get(x);
                        }
                    }
                    {

                    }
                }


                StringBuilder resultBuild = new StringBuilder();
                for (int q = 0; q < NumberOfPeople; q++) {
                    resultBuild.append("Person ").append(q + 1).append(": RM")
                            .append(toPay[q]).append("\n");
                }
                breakdown.setText(resultBuild.toString());

            }
        });

    }
}