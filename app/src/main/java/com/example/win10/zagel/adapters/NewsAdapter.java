package com.example.win10.zagel.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.win10.zagel.R;
import com.example.win10.zagel.interfaces.RecyclerClickListener;
import com.example.win10.zagel.models.NewsModel;
import com.example.win10.zagel.networks.Constants;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int Card_one = 0;
    private static final int Card_two = 1;
    private static final int Card_three = 2;
    private static final int Card_deleted = 404;
    private Context context;
    private List<NewsModel> newsModels;
    private RecyclerClickListener recyclerClickListener;

    public NewsAdapter(Context context, RecyclerClickListener recyclerClickListener) {
        this.context = context;
        newsModels = new ArrayList<>();
        this.recyclerClickListener = recyclerClickListener;
    }

    public List<NewsModel> getNewsModels() {
        return newsModels;
    }

    public void setNewsModels(List<NewsModel> newsModels) {
        this.newsModels = newsModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case Card_two:
                View v1 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_1, parent, false);
                return new ViewHolder2(v1);
            case Card_three:
                View v2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_2, parent, false);
                return new ViewHolder3(v2);
            case Card_one:
            default:
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_news, parent, false);
                return new MyViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case Card_one:
                NewsModel newsModel = newsModels.get(position);
                MyViewHolder viewHolder1 = (MyViewHolder) holder;
                viewHolder1.titleTxt.setText(newsModel.getTitle());
                viewHolder1.favoriteImg.setImageResource(R.drawable.ic_fav_artical);
                viewHolder1.readLaterImg.setImageResource(R.drawable.read_later_artical);
                viewHolder1.TxtSourceTitle.setText(newsModel.getSourceTitle());
                viewHolder1.TxtFormatedDate.setText(newsModel.getFormatedDate());
                viewHolder1.btnSectionTitle.setText(newsModel.getSectionTitle());
                setImageHolder(viewHolder1.coverImg, newsModel);
                Picasso.with(context)
                        .load(Uri.parse(Constants.loadSourceImg(newsModel.getSourceID())))
                        .into(viewHolder1.sourceImg);
                if (newsModel.isIsSelectedReadLater())
                    viewHolder1.readLaterImg.setImageResource(R.drawable.fav);
                else {
                    viewHolder1.readLaterImg.setImageResource(R.drawable.read_later_artical);

                }
                if (newsModel.isIsSelectedFavorite())
                    viewHolder1.favoriteImg.setImageResource(R.drawable.fav);
                else {
                    viewHolder1.favoriteImg.setImageResource(R.drawable.ic_fav_artical);
                }
                break;
            case Card_two:
                NewsModel newsModelTwo = newsModels.get(position);
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
                viewHolder2.titleTxt.setText(newsModelTwo.getTitle());
                viewHolder2.favoriteImg.setImageResource(R.drawable.ic_fav_artical);
                viewHolder2.readLaterImg.setImageResource(R.drawable.read_later_artical);
                viewHolder2.copyImg.setImageResource(R.drawable.ic_copy_link);
                viewHolder2.TxtSourceTitle.setText(newsModelTwo.getSourceTitle());
                viewHolder2.TxtFormatedDate.setText(newsModelTwo.getFormatedDate());
                viewHolder2.btnSectionTitle.setText(newsModelTwo.getSectionTitle());
                setImageHolder(viewHolder2.coverImg, newsModelTwo);
                Picasso.with(context)
                        .load(Uri.parse(Constants.loadSourceImg(newsModelTwo.getSourceID())))
                        .into(viewHolder2.sourceImg);
                if (newsModelTwo.isIsSelectedReadLater())
                    viewHolder2.readLaterImg.setImageResource(R.drawable.fav);
                else {
                    viewHolder2.readLaterImg.setImageResource(R.drawable.read_later_artical);

                }
                if (newsModelTwo.isIsSelectedFavorite())
                    viewHolder2.favoriteImg.setImageResource(R.drawable.fav);
                else {
                    viewHolder2.favoriteImg.setImageResource(R.drawable.ic_fav_artical);
                }
                break;
            case Card_three:
                NewsModel newsModelThree = newsModels.get(position);
                ViewHolder3 viewHolder3 = (ViewHolder3) holder;
                viewHolder3.titleTxt.setText(newsModelThree.getTitle());
                viewHolder3.favoriteImg.setImageResource(R.drawable.ic_fav_artical);
                viewHolder3.readLaterImg.setImageResource(R.drawable.read_later_artical);
                viewHolder3.copyImg.setImageResource(R.drawable.ic_copy_link);
                viewHolder3.TxtSourceTitle.setText(newsModelThree.getSourceTitle());
                viewHolder3.TxtFormatedDate.setText(newsModelThree.getFormatedDate());
                viewHolder3.btnSectionTitle.setText(newsModelThree.getSectionTitle());
                setImageHolder(viewHolder3.coverImg, newsModelThree);
                Picasso.with(context)
                        .load(Uri.parse(Constants.loadSourceImg(newsModelThree.getSourceID())))
                        .into(viewHolder3.sourceImg);
                if (newsModelThree.isIsSelectedReadLater())
                    viewHolder3.readLaterImg.setImageResource(R.drawable.fav);
                else {
                    viewHolder3.readLaterImg.setImageResource(R.drawable.read_later_artical);

                }
                if (newsModelThree.isIsSelectedFavorite())
                    viewHolder3.favoriteImg.setImageResource(R.drawable.fav);
                else {
                    viewHolder3.favoriteImg.setImageResource(R.drawable.ic_fav_artical);
                }
                break;
            case Card_deleted:
                holder.itemView.getLayoutParams().height = 0;
                break;
        }
    }

    private void setImageHolder(ImageView image, NewsModel newsModel) {
        if (newsModel.getRatio() == null || newsModel.getRatio().equals("") ||
                newsModel.getRatio().equals("1.55") || newsModel.getRatio().equals("0")) {
            image.setVisibility(View.GONE);
        } else {
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = (int) (width / Double.parseDouble(newsModel.getRatio()));
            image.setVisibility(View.VISIBLE);
            image.getLayoutParams().width = width;
            image.getLayoutParams().height = height;
            Picasso.with(context)
                    .load(Uri.parse(Constants.mediaURL + newsModel.getImg()))
                    .placeholder(R.drawable.placeholder_img)
                    .into(image);
        }
    }

    @Override
    public int getItemCount() {
        return newsModels.size();
    }

    @Override
    public int getItemViewType(int position) {
        NewsModel newsModel = newsModels.get(position);
        if (newsModel.getType() == 1)
            return Card_one;
        else if (newsModel.getType() == 16)
            return Card_two;
        else if (newsModel.getType() == 17)
            return Card_three;
        else
            return Card_deleted;
    }

    class MyViewHolder extends init {

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ViewHolder3 extends init {

        ViewHolder3(@NonNull View itemView) {
            super(itemView);
        }
    }

    class ViewHolder2 extends init {

        ViewHolder2(@NonNull View itemView) {
            super(itemView);
        }
    }

    class init extends RecyclerView.ViewHolder {
        ImageView coverImg;
        ImageView copyImg;
        ImageView sourceImg;
        ImageView favoriteImg;
        ImageView readLaterImg;
        TextView TxtSourceTitle;
        TextView TxtFormatedDate;
        TextView titleTxt;
        TextView btnSectionTitle;

        init(@NonNull final View itemView) {
            super(itemView);
            coverImg = itemView.findViewById(R.id.coverImage);
            copyImg = itemView.findViewById(R.id.copy);
            sourceImg = itemView.findViewById(R.id.sourceImg);
            favoriteImg = itemView.findViewById(R.id.favImg);
            readLaterImg = itemView.findViewById(R.id.saveImg);
            TxtSourceTitle = itemView.findViewById(R.id.sourceTxt);
            TxtFormatedDate = itemView.findViewById(R.id.dateTxt);
            btnSectionTitle = itemView.findViewById(R.id.btnSectionTitle);
            titleTxt = itemView.findViewById(R.id.textTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1)
                        recyclerClickListener.recyclerViewListClicked(getAdapterPosition());
                }
            });

            favoriteImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1) {
                        NewsModel newsModel = newsModels.get(getAdapterPosition());
                        recyclerClickListener.setFav(getAdapterPosition(), newsModel.getID(), !newsModel.isIsSelectedFavorite());
                    }
                }
            });

            readLaterImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getAdapterPosition() != -1) {
                        NewsModel newsModel = newsModels.get(getAdapterPosition());
                        recyclerClickListener.setReadLater(getAdapterPosition(), newsModel.getID(), !newsModel.isIsSelectedReadLater());
                    }
                }
            });
        }
    }
}