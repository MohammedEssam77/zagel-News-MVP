package com.example.win10.zagel.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.win10.zagel.R;
import com.example.win10.zagel.models.SourcesModel;
import com.example.win10.zagel.networks.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SourceAdapter extends RecyclerView.Adapter<SourceAdapter.MyViewHolder> {
    private Context context;
    private List<SourcesModel> getSources;

    public SourceAdapter(Context context) {
        this.context = context;
        getSources = new ArrayList<>();
    }

    public List<SourcesModel> getSources() {
        return getSources;
    }

    public void setSourcesAdapter(List<SourcesModel> getSources) {
        this.getSources = getSources;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_source, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(Uri.parse(Constants.mediaURL + getSources.get(position).getImgURl()))
                .into(holder.sourceImage);
    }

    @Override
    public int getItemCount() {
        return getSources.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView sourceImage;

        MyViewHolder(View view) {
            super(view);
            sourceImage = view.findViewById(R.id.sourceImage);
        }
    }
}