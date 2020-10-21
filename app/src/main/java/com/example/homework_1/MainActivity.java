package com.example.homework_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String RECYCLER_FRAGMENT_TAG = "RECYCLER_FRAGMENT_TAG";
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.findFragmentByTag(RECYCLER_FRAGMENT_TAG) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, RecyclerViewFragment.newInstance(FIRST_NUMBER, LAST_NUMBER), RECYCLER_FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}