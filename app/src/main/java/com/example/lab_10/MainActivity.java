package com.example.lab_10;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textViewQuote;
    TextView textViewAuthor;

    Button button;

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

        textViewQuote = findViewById(R.id.textViewQuote);
        //textViewAuthor = findViewById(R.id.textViewAuthor);
        //button = findViewById(R.id.button);

        ApiThread apiThread = new ApiThread(this);
        apiThread.start();
//        button.setOnClickListener(v -> {
//            ApiThread thread = new ApiThread(this);
//            thread.start();
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApiThread thread = new ApiThread(this);
        thread.start();
    }
}
