package com.example.practical1_intents;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class SecondScreen extends AppCompatActivity {

    String furniture;
    String quantity;
    private TextView tvFurniture, tvQuantity, tvResult, tvSelectedDate;
    private Button btnCalculateTotal, btnSelectDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvFurniture = findViewById(R.id.tvFurniture);
        tvQuantity = findViewById(R.id.tvQuantity);
        tvResult = findViewById(R.id.tvResult);
        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        btnCalculateTotal = findViewById(R.id.btnCalculateTotal);
        btnSelectDate = findViewById(R.id.btnSelectDate);

        furniture = getIntent().getStringExtra("furniture");
        quantity = getIntent().getStringExtra("quantity");

        tvFurniture.setText("Item:  " + furniture);
        tvQuantity.setText("Units:  " + quantity);

        btnCalculateTotal.setOnClickListener(v -> {
            Intent intent = new Intent(SecondScreen.this, ThirdScreen.class);
            intent.putExtra("quantity", quantity);
            intent.putExtra("furniture", furniture);
            startActivityForResult(intent, 1);
        });

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            double total = data.getDoubleExtra("total", 0);
            tvResult.setText("Total Cost: $" + total);
        }
    }


    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view,
                                          int year, int month, int dayOfMonth) {
                        String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                        tvSelectedDate.setText("Selected Delivery Date: " + date);
                    }
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

}