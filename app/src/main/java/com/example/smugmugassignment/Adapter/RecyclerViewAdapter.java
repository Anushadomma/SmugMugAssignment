package com.example.smugmugassignment.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smugmugassignment.R;
import com.example.smugmugassignment.model.DataModel;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {

    ImageLoader imageLoader;
    DisplayImageOptions options;

    // creating a variable for our array list and context.
    private ArrayList<DataModel> dataModelArrayList;
    private Context mcontext;

    // creating a constructor class.
    public RecyclerViewAdapter(ArrayList<DataModel> recyclerDataArrayList, Context mcontext) {

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mcontext).build();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(config);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

        this.dataModelArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        // Set the data to textview from  modal class.
        DataModel modal = dataModelArrayList.get(position);
        holder.trackName.setText(modal.getTrackName());
        holder.releaseDate.setText(modal.getReleaseDate());
        holder.shortDescription.setText(modal.getShortDescription());

        ImageLoadingListener listener = new ImageLoadingListener() {


            @Override
            public void onLoadingStarted(String arg0, View arg1) {

            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {

            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
            }

            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {

            }

        };
          // display image from end point url
        imageLoader.displayImage(dataModelArrayList.get(position).getArtworkUrl100(), holder.artworkUrl100, options, listener);



    }

    @Override
    public int getItemCount() {
        //  returns the size of recyclerview
        return dataModelArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView trackName, releaseDate, shortDescription;
        private ImageView artworkUrl100;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            trackName = itemView.findViewById(R.id.idtrackName);
            releaseDate = itemView.findViewById(R.id.idreleaseDate);
            shortDescription = itemView.findViewById(R.id.idDescription);
            artworkUrl100 = itemView.findViewById(R.id.idimageView);
        }
    }
}