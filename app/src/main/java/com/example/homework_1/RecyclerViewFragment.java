package com.example.homework_1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RecyclerViewFragment extends Fragment {
    private static final String BIG_NUMBER_FRAGMENT_TAG = "BIG_NUMBER_FRAGMENT_TAG";
    private static final String FIRST_NUMBER = "FIRST_NUMBER";
    private static final String LAST_NUMBER = "LAST_NUMBER";

    public int mFirstNumber;
    public int mLastNumber;

    @NotNull
    public static RecyclerViewFragment newInstance(int firstNumber, int lastNumber) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        fragment.mFirstNumber = firstNumber;
        fragment.mLastNumber = lastNumber;

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mFirstNumber = savedInstanceState.getInt(FIRST_NUMBER);
            mLastNumber = savedInstanceState.getInt(LAST_NUMBER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_layout, container, false);

        NumbersRangeDataSource numbersRangeDataSource = new NumbersRangeDataSource(mFirstNumber, mLastNumber);

        final NumbersAdapter adapter = new NumbersAdapter(numbersRangeDataSource,
                ResourcesCompat.getColor(getResources(), R.color.numberRed, null),
                ResourcesCompat.getColor(getResources(), R.color.numberBlue, null));

        RecyclerView recyclerView = view.findViewById(R.id.recycler_numbers);
        recyclerView.setAdapter(adapter);

        Button addElementButton = view.findViewById(R.id.addElementButton);
        addElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addElement();
                mLastNumber = adapter.lastNumber();
            }
        });

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(FIRST_NUMBER, mFirstNumber);
        outState.putInt(LAST_NUMBER, mLastNumber);
    }

    private void openBigNumberFragment(int number, @ColorInt int color) {
        BigNumberFragment bigNumberFragment = BigNumberFragment.newInstance(number, color);

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.main_fragment, bigNumberFragment, BIG_NUMBER_FRAGMENT_TAG)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }
    }

    public class NumbersAdapter extends RecyclerView.Adapter<NumberViewHolder> {
        private NumbersRangeDataSource mNumbersRangeDataSource;
        private final @ColorInt int mEvenNumbersColor;
        private final @ColorInt int mOddNumbersColor;

        public NumbersAdapter(NumbersRangeDataSource numbersRangeDataSource,
                              @ColorInt int evenNumbersColor,
                              @ColorInt int oddNumbersColor) {
            mEvenNumbersColor = evenNumbersColor;
            mOddNumbersColor = oddNumbersColor;
            mNumbersRangeDataSource = numbersRangeDataSource;
        }

        public int lastNumber() {
            return mNumbersRangeDataSource.lastNumber();
        }

        public void addElement() {
            mNumbersRangeDataSource.increaseRangeToRight();

            notifyItemInserted(mNumbersRangeDataSource.size());
        }

        @NonNull
        @Override
        public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_layout, parent, false);
            return new NumberViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final NumberViewHolder holder, int position) {

            final Integer numberToSet = mNumbersRangeDataSource.getNumbers().get(position);
            holder.mNumberView.setText(String.valueOf(numberToSet));

            if (numberToSet % 2 == 0) {
                holder.mNumberView.setTextColor(mEvenNumbersColor);
            } else {
                holder.mNumberView.setTextColor(mOddNumbersColor);
            }

            holder.mNumberView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int number = Integer.parseInt(holder.mNumberView.getText().toString());
                    @ColorInt int color = holder.mNumberView.getCurrentTextColor();

                    RecyclerViewFragment.this.openBigNumberFragment(number, color);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mNumbersRangeDataSource.getNumbers().size();
        }


    }

    public static class NumberViewHolder extends RecyclerView.ViewHolder {

        public final TextView mNumberView;

        public NumberViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mNumberView = itemView.findViewById(R.id.number);
        }
    }
}
