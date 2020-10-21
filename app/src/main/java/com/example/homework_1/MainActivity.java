package com.example.homework_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String RECYCLER_FRAGMENT_KEY = "RECYCLER_FRAGMENT_KEY";
    private static final String RECYCLER_FRAGMENT_TAG = "RECYCLER_FRAGMENT_TAG";
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "onSaveInstanceState start");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();


        if (fragmentManager.findFragmentByTag(RECYCLER_FRAGMENT_TAG) == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, RecyclerViewFragment.newInstance(FIRST_NUMBER, LAST_NUMBER), RECYCLER_FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

        Log.i("MainActivity", "onSaveInstanceState end");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        Log.i("MainActivity", "onSaveInstanceState start");
        super.onSaveInstanceState(outState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(RECYCLER_FRAGMENT_TAG);

        if (fragment != null) {
            fragmentManager.putFragment(outState, RECYCLER_FRAGMENT_KEY, fragment);
        }

        Log.i("MainActivity", "onSaveInstanceState end");
    }

    @Override
    protected void onDestroy() {
        Log.i("MainActivity", "onDestroy start");
        super.onDestroy();
        Log.i("MainActivity", "onDestroy end");
    }
}