package com.example.homework_1;

import java.util.ArrayList;
import java.util.List;

public class NumbersDataSource {
    private List<Integer> mNumbers;

    public NumbersDataSource(int size) {

        mNumbers = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
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
}
