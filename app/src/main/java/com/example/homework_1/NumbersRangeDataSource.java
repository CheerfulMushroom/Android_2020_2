package com.example.homework_1;

import java.util.ArrayList;
import java.util.List;

public class NumbersRangeDataSource {
    private List<Integer> mNumbers;

    public NumbersRangeDataSource(int start, int stop) {

        mNumbers = new ArrayList<>();
        for (int i = start; i <= stop; i++) {
            mNumbers.add(i);
        }
    }

    public List<Integer> getNumbers() {
        return mNumbers;
    }

    public void addNumber(int number) {
        mNumbers.add(number);
    }

    public int size() {
        return mNumbers.size();
    }

    public int lastNumber() {
        return mNumbers.get(mNumbers.size() - 1);
    }
}
