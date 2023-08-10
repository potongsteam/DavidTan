package com.example.individual_assig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class input_Bill extends AppCompatActivity {

    private Button addItem;
    private Button submit;
    private EditText itemCost;
    private EditText totalBill;
    private EditText itemName;
    private EditText parName;

    private StringBuilder details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_bill);

        totalBill = findViewById(R.id.totalBill);
        parName = findViewById(R.id.parName);
        itemName = findViewById(R.id.itemName);
        itemCost = findViewById(R.id.itemCost);
        addItem = findViewById(R.id.addItem);
        submit = findViewById(R.id.submit);

        details = new StringBuilder();

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Name = parName.getText().toString().trim();
                String Item = itemName.getText().toString().trim();
                String Cost = itemCost.getText().toString().trim();

                if(Name.isEmpty() || Item.isEmpty() || Cost.isEmpty())
                {
                    Toast.makeText(input_Bill.this, "Please fill in your particulars",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                double costItem = Double.parseDouble(Cost);
                details.append("Name: ").append(Name)
                        .append("Item: ").append(Item)
                        .append("Cost: RM").append(Cost)
                        .append("\n");

                parName.setText("");
                itemName.setText("");
                itemCost.setText("");

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String total = totalBill.getText().toString().trim();

                if(total.isEmpty() || details.length() == 0)
                {
                    Toast.makeText(input_Bill.this, "Please input total bill amount and add items",
                            Toast.LENGTH_SHORT).show();
                    return;


                }

                double totalAmount = Double.parseDouble(total);
                details.append("Total Bill: RM").append(totalAmount);
            }
        });




    }
}