package com.example.homework_1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class BigNumberFragment extends Fragment {
    private static final String COLOR = "COLOR";
    private static final String NUMBER = "NUMBER";

    public static BigNumberFragment newInstance(int number, @ColorInt int color) {
        BigNumberFragment fragment = new BigNumberFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(COLOR, color);
        bundle.putInt(NUMBER, number);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.big_number_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        assert getArguments() != null;

        int color = getArguments().getInt(COLOR);
        int number = getArguments().getInt(NUMBER);

        TextView numberView = view.findViewById(R.id.big_number);
        numberView.setTextColor(color);
        numberView.setText(String.valueOf(number));
    }
}
