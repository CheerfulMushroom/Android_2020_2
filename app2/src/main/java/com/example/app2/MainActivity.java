package com.example.app2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DataSource dataSource = new DataSource();
        List<DataSource.NewsModel> news = dataSource.getData();
        recyclerView.setAdapter(new MyAdapter(news));
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<DataSource.NewsModel> mNews;

        public MyAdapter(List<DataSource.NewsModel> news) {
            this.mNews = news;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Log.d("MyAdapter", "onCreateViewHolder");

            View view = LayoutInflater.from(parent.getContext().getApplicationContext()).inflate(R.layout.news_layout, parent, false);

            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            Log.d("MyAdapter", "onBindViewHolder " + position);

            DataSource.NewsModel currentNews = mNews.get(position);
            holder.mTitle.setText(currentNews.getTitle());
            holder.mDate.setText(currentNews.getDate());
            holder.mImage.setBackgroundColor(currentNews.getColor());
            holder.itemView.setOnClickListener(view -> Toast.makeText(view.getContext(), "Clicked on " + position, Toast.LENGTH_SHORT).show());

        }

        @Override
        public int getItemCount() {
            return mNews.size();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mImage;
        public final TextView mTitle;
        public final TextView mDate;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mTitle = itemView.findViewById(R.id.title);
            mDate = itemView.findViewById(R.id.date);
        }
    }
}
