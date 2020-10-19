package com.example.homework_1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewFragment extends Fragment {
    private RecyclerViewFragment(){};
    private static final String ITEMS_AMOUNT = "INITIAL_ELEMENTS_AMOUNT";

    public static RecyclerViewFragment newInstance(int elementsAmount) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(ITEMS_AMOUNT, elementsAmount);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_layout, container, false);

        int itemsAmount = getArguments().getInt(ITEMS_AMOUNT, 100);
        NumbersDataSource numbersDataSource = new NumbersDataSource(itemsAmount);

        // TODO: check context
        final NumbersAdapter adapter = new NumbersAdapter(getContext(), numbersDataSource);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_numbers);
        recyclerView.setAdapter(adapter);

        Button addElementButton = view.findViewById(R.id.addElementButton);
        addElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addElement();
                getArguments().putInt(ITEMS_AMOUNT, adapter.size());
            }
        });

        return view;
    }


    public class NumbersAdapter extends RecyclerView.Adapter<NumberViewHolder> {
        private final Context mContext;
        private NumbersDataSource mNumbersDataSource;

        public NumbersAdapter(Context context, NumbersDataSource numbersDataSource) {
            mContext = context;
            mNumbersDataSource = numbersDataSource;
        }

        public int size() {
            return mNumbersDataSource.size();
        }

        public void addElement() {
            List<Integer> numbers = mNumbersDataSource.getNumbers();
            final int lastNumber = numbers.get(numbers.size() - 1);

            mNumbersDataSource.addNumber(lastNumber + 1);

            notifyItemInserted(mNumbersDataSource.size());
        }

        @NonNull
        @Override
        public NumberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // TODO: check context
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_layout, parent, false);
            return new NumberViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final NumberViewHolder holder, int position) {

            final Integer numberToSet = mNumbersDataSource.getNumbers().get(position);
            holder.mNumberView.setText(String.valueOf(numberToSet));

            if (numberToSet % 2 == 0) {
                holder.mNumberView.setTextColor(mContext.getColor(R.color.numberRed));
            } else {
                holder.mNumberView.setTextColor(mContext.getColor(R.color.numberBlue));
            }

            holder.mNumberView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("color", holder.mNumberView.getCurrentTextColor());
                    bundle.putInt("number", Integer.parseInt(holder.mNumberView.getText().toString()));

                    Fragment bigNumberFragment = new BigNumberFragment();
                    bigNumberFragment.setArguments(bundle);

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
            return mNumbersDataSource.getNumbers().size();
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
