package com.example.homework_1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BigNumberFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.big_number_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView numberView = view.findViewById(R.id.big_number);

        int color = 0;
        int number = 0;

        if (savedInstanceState != null) {
            // TODO: remove hardcode
            color = savedInstanceState.getInt("color", 0);
            number = savedInstanceState.getInt("number", 0);

        } else if (getArguments() != null) {
            // TODO: use color from colors
            color = getArguments().getInt("color", 0);
            number = getArguments().getInt("number", 0);
        }

        numberView.setTextColor(color);
        numberView.setText(String.valueOf(number));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
