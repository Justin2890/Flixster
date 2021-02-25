package com.example.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.DetailActivity3;
import com.example.flixster.Models.Movie;
import com.example.flixster.R;

import org.parceler.Parcels;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    //Usually involves inflating a layout from CML and returning the Holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d( "movieAdapter","onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d( "movieAdapter","onBindViewHolder" + position);
        // get the movie at the passed in position
        Movie movie = movies.get(position);
        //bind the movie data into the VH
        holder.bind(movie);
        
    }

    //returns the total count of items in the List
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout container;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(Movie movie)
        {
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        Glide.with(context).load(movie.getPosterPath()).into(ivPoster);

        //1. Register click listener on the whole row
        container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2. Navigate to a new activity on tap
                //Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, DetailActivity3.class);
                i.putExtra("movie", Parcels.wrap(movie));
                context.startActivity(i);
            }
        });

        }
        }
    }

