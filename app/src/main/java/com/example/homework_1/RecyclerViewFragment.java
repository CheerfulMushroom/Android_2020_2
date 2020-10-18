package com.example.homework_1;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
        recyclerView.setAdapter(new NumbersAdapter(getContext()));

        return view;
    }

    public static class NumbersAdapter extends RecyclerView.Adapter<NumberViewHolder> {
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
        public void onBindViewHolder(@NonNull NumberViewHolder holder, int position) {

            final Integer numberToSet = mNumbers.get(position);
            holder.mNumberView.setText(String.valueOf(numberToSet));

            if (numberToSet % 2 == 0) {
                holder.mNumberView.setTextColor(mContext.getColor(R.color.numberRed));
            } else {
                holder.mNumberView.setTextColor(mContext.getColor(R.color.numberBlue));
            }

            holder.mNumberView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("test", numberToSet.toString());

                }
            });
        }

        @Override
        public int getItemCount() {
            return mNumbers.size();
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
