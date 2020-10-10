package com.example.lesson1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        int counterValue = getIntent().getIntExtra(Constants.COUNTER_VALUE_KEY, 0);

        TextView bigCounter = findViewById(R.id.big_counter);
        bigCounter.setText(String.valueOf(counterValue));
    }
}
