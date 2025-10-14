package com.example.practical1_intents;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    private Spinner spFurniture;
    private EditText etQuantity;
    private Button btOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void onOrderClick(View view) {
        String furniture = ((Spinner)findViewById(R.id.spFurniture)).getSelectedItem().toString();
        String quantity = ((EditText)findViewById(R.id.etQuantity)).getText().toString();

        if (quantity.isEmpty()) {
            Toast.makeText(this, "Please enter a quantity!", Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = Integer.parseInt(quantity);

        Intent intent = new Intent(MainActivity.this, SecondScreen.class);
        intent.putExtra("furniture", furniture);
        intent.putExtra("quantity", quantity);
        startActivity(intent);
    }
}