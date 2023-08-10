package com.example.individual_assig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class equalBreakdown extends AppCompatActivity {

    private EditText totalBill;
    private EditText numofPeople;
    private TextView breakdownList;
    private Button breakdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equal_breakdown);

        totalBill = findViewById(R.id.totalBill);
        numofPeople = findViewById(R.id.numOfPeople);
        breakdownList = findViewById(R.id.breakdownList);
        breakdown = findViewById(R.id.breakdown);

        breakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Total = totalBill.getText().toString().trim();
                String Number = numofPeople.getText().toString().trim();

                if(Total.isEmpty() || Number.isEmpty())
                {
                    Toast.makeText(equalBreakdown.this, "Please input total bill amount and number of people"
                            , Toast.LENGTH_SHORT).show();
                    return;

                }

                int peopleQuantity = Integer.parseInt(Number);
                double totalAmount = Double.parseDouble(Total);

                double breakTotal = totalAmount / peopleQuantity;

                String result = "Final Equal Breakdown: RM" + breakTotal + "per person";
                breakdownList.setText(result);
            }
        });


    }
}