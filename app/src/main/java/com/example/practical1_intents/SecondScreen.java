package com.example.practical1_intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondScreen extends AppCompatActivity {

    String furniture;
    String quantity;
    private TextView tvFurniture, tvQuantity, tvResult;

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

        // Get data from first screen
        furniture = getIntent().getStringExtra("furniture");
        quantity = getIntent().getStringExtra("quantity");

        tvFurniture.setText("Item:  " + furniture);
        tvQuantity.setText("Units:  " + quantity);

        // Check if total was passed from ThirdScreen
        Intent intent = getIntent();
        if (intent.hasExtra("RESULT_DATA")) {
            double total = intent.getDoubleExtra("RESULT_DATA", 0);
            tvResult.setText("Total Price: $" + String.format("%.2f", total));
        }

    }

    public void onCalculateTotalClick(View view) {
        Intent intent = new Intent(SecondScreen.this, ThirdScreen.class);
        intent.putExtra("furniture", furniture);
        intent.putExtra("quantity", quantity);
        startActivity(intent);
    }




}