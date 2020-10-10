package com.example.app2;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataSource {
    private final List<NewsModel> list;
    private final String[] mTitles = new String[] {
            "title1",
            "title2",
            "title3",
            "title4",
            "title5",
            "title6",
            "title7",
    };

    private final int[] mColors = new int[] {
            Color.RED,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.BLACK,
    };

    public DataSource() {
        list = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String title = mTitles[random.nextInt(mTitles.length)];
            String date = new Date(random.nextInt()).toString();
            int color = mColors[random.nextInt(mColors.length)];

            list.add(new NewsModel(title, date, color));
        }
    }

    public List<NewsModel> getData() {
        return list;
    }

    public static class NewsModel {
        private String mTitle;
        private String mDate;
        private int mColor;

        public NewsModel(String mTitle, String mDate, int mColor) {
            this.mTitle = mTitle;
            this.mDate = mDate;
            this.mColor = mColor;
        }

        public String getTitle() {
            return mTitle;
        }

        public String getDate() {
            return mDate;
        }

        public int getColor() {
            return mColor;
        }
    }
}
