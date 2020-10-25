package com.example.homework_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

public class BigNumberFragment extends Fragment {
    private static final String COLOR = "COLOR";
    private static final String NUMBER = "NUMBER";
    public int mNumber;
    public @ColorInt int mColor;

    @NotNull
    public static BigNumberFragment newInstance(int number, @ColorInt int color) {
        BigNumberFragment fragment = new BigNumberFragment();
        fragment.mNumber = number;
        fragment.mColor = color;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mColor = savedInstanceState.getInt(COLOR);
            mNumber = savedInstanceState.getInt(NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.big_number_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView numberView = view.findViewById(R.id.big_number);
        numberView.setTextColor(mColor);
        numberView.setText(String.valueOf(mNumber));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(NUMBER, mNumber);
        outState.putInt(COLOR, mColor);
    }
}
