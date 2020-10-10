package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int textColor = ResourcesCompat.getColor(getResources(), R.color.myColor, getTheme());
        int backgroundColor = ResourcesCompat.getColor(getResources(), R.color.myBackgroundColor, getTheme());

        final TextView textView = findViewById(R.id.counter);
        textView.setTextColor(textColor);
        textView.setBackgroundColor(backgroundColor);

        Button incrementor = findViewById(R.id.increment_counter);
        incrementor.setOnClickListener(new CounterIncrementor(textView, true));

        Button decrementor = findViewById(R.id.decrement_counter);
        decrementor.setOnClickListener(new CounterIncrementor(textView, false));

        Button nextActivityButton = findViewById(R.id.next_activity_button);
        nextActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra(Constants.COUNTER_VALUE_KEY, Integer.parseInt(textView.getText().toString()));
                startActivity(intent);
            }
        });
    }

    private class CounterIncrementor implements View.OnClickListener {

        private TextView mTextView;
        private boolean mIsIncrementor;

        public CounterIncrementor(TextView textView, boolean isIncrementor) {
            this.mTextView = textView;
            this.mIsIncrementor = isIncrementor;
        }

        @Override
        public void onClick(View v) {
            if (mIsIncrementor) {
                incrementCounter(mTextView);
            } else {
                decrementCounter(mTextView);
            }
        }
    }

    private void incrementCounter(TextView viewToIncrement) {
        String currentText = viewToIncrement.getText().toString();
        int currentNumber = Integer.parseInt(currentText);

        viewToIncrement.setText(String.valueOf(currentNumber + 1));
    }

    private void decrementCounter(TextView viewToIncrement) {
        String currentText = viewToIncrement.getText().toString();
        int currentNumber = Integer.parseInt(currentText);

        viewToIncrement.setText(String.valueOf(currentNumber - 1));
    }
}