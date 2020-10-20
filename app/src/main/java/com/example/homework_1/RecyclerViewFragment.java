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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewFragment extends Fragment {
    private static final String FIRST_NUMBER = "FIRST_NUMBER";
    private static final String LAST_NUMBER = "LAST_NUMBER";

    public static RecyclerViewFragment newInstance(int firstNumber, int lastNumber) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(FIRST_NUMBER, firstNumber);
        bundle.putInt(LAST_NUMBER, lastNumber);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_layout, container, false);

        final Bundle bundle = getArguments();
        assert bundle != null;

        int firstNumber = bundle.getInt(FIRST_NUMBER);
        int lastNumber = bundle.getInt(LAST_NUMBER);
        NumbersRangeDataSource numbersRangeDataSource = new NumbersRangeDataSource(firstNumber, lastNumber);

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
                bundle.putInt(LAST_NUMBER, adapter.lastNumber());
            }
        });

        return view;
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
            List<Integer> numbers = mNumbersRangeDataSource.getNumbers();
            final int lastNumber = numbers.get(numbers.size() - 1);

            mNumbersRangeDataSource.addNumber(lastNumber + 1);

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

                    Fragment bigNumberFragment = BigNumberFragment.newInstance(number, color);

                    FragmentManager fragmentManager = RecyclerViewFragment.this.getFragmentManager();
                    if (fragmentManager != null) {
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_fragment, bigNumberFragment);
                        transaction.addToBackStack(null);
                        transaction.commitAllowingStateLoss();
                    }

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
