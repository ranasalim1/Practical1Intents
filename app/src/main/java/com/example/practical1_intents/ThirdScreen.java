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


public class ThirdScreen extends AppCompatActivity {
    private String furniture;
    private int quantity;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_third_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        furniture = getIntent().getStringExtra("furniture");
        quantity = Integer.parseInt(getIntent().getStringExtra("quantity"));

        ((TextView)findViewById(R.id.tvPrompt)).setText("Calculating total for: " + quantity + " units of " + furniture);

    }

    public void onReturnResultClick(View view) {
        double pricePerItem = 0;

        switch (furniture) {
            case "Dining Set": pricePerItem = 1000.50; break;
            case "Nightstand": pricePerItem = 500.99; break;
            case "Chair": pricePerItem = 899.79; break;
            case "Desk": pricePerItem = 1200.00; break;
            case "Dresser": pricePerItem = 1000.90; break;
            case "Lamp": pricePerItem = 565.00; break;
            case "Bed": pricePerItem = 800.89; break;
            case "Carpet": pricePerItem = 1299.99; break;
            default: pricePerItem = 0; break;
        }

        double total = pricePerItem * quantity;


        Intent intent = new Intent(getApplicationContext(), SecondScreen.class);
        intent.putExtra("RESULT_DATA",total);
        startActivity(intent);

    }
}