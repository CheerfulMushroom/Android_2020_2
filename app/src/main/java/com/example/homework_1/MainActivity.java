package com.example.homework_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String RECYCLER_FRAGMENT_KEY = "RECYCLER_FRAGMENT_KEY";
    private static final String RECYCLER_FRAGMENT_TAG = "RECYCLER_FRAGMENT_TAG";
    private static final int FIRST_NUMBER = 1;
    private static final int LAST_NUMBER = 100;

    RecyclerViewFragment mRecyclerViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        if (savedInstanceState != null) {
            mRecyclerViewFragment = (RecyclerViewFragment) fragmentManager.getFragment(savedInstanceState, RECYCLER_FRAGMENT_KEY);
        }


        if (mRecyclerViewFragment == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            mRecyclerViewFragment = RecyclerViewFragment.newInstance(FIRST_NUMBER, LAST_NUMBER);

            transaction.replace(R.id.main_fragment, mRecyclerViewFragment, RECYCLER_FRAGMENT_TAG)
                    .commitAllowingStateLoss();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        getSupportFragmentManager().putFragment(outState, RECYCLER_FRAGMENT_KEY, mRecyclerViewFragment);
    }
}