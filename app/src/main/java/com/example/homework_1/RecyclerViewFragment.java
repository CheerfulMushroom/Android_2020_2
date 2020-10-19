package com.example.homework_1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_layout, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_numbers);
        // TODO: check context
        final NumbersAdapter adapter = new NumbersAdapter(getContext());
        recyclerView.setAdapter(adapter);

        Button addElementButton = view.findViewById(R.id.addElementButton);
        addElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addElement();
            }
        });

        return view;
    }

    public class NumbersAdapter extends RecyclerView.Adapter<NumberViewHolder> {
        private final Context mContext;
        private List<Integer> mNumbers;

        public NumbersAdapter(Context context) {
            mContext = context;
            mNumbers = new ArrayList<Integer>();

            for (int i = 0; i < 100; i++) {
                mNumbers.add(i);
            }
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

            final Integer numberToSet = mNumbers.get(position);
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
            return mNumbers.size();
        }

        public void addElement() {
            final int lastNumber = mNumbers.get(mNumbers.size() - 1);
            mNumbers.add(lastNumber + 1);
            notifyItemInserted(mNumbers.size());
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
