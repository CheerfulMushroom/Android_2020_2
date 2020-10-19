package com.example.homework_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final String recyclerFragmentTag = "RECYCLER_FRAGMENT_TAG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (fragmentManager.findFragmentByTag(recyclerFragmentTag) == null) {
            transaction.replace(R.id.main_fragment, new RecyclerViewFragment(), recyclerFragmentTag);
        }

        transaction.commitAllowingStateLoss();
    }


}