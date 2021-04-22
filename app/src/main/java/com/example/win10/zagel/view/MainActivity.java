package com.example.win10.zagel.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.win10.zagel.R;
import com.example.win10.zagel.adapters.NewsAdapter;
import com.example.win10.zagel.adapters.SourceAdapter;
import com.example.win10.zagel.interfaces.MainContract;
import com.example.win10.zagel.interfaces.RecyclerClickListener;
import com.example.win10.zagel.models.NewsModel;
import com.example.win10.zagel.models.RequestID;
import com.example.win10.zagel.models.SourcesModel;
import com.example.win10.zagel.networks.HomeNetwork;
import com.example.win10.zagel.presenter.MainPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerClickListener, MainContract.MainView {
    private RecyclerView sources_rec;
    private RecyclerView news_rec;
    private MainContract.presenter presenter;
    private ProgressBar progressBar;
    private List<NewsModel> getNews;
    private List<SourcesModel> getSources;
    private NewsAdapter newsAdapter;
    private SourceAdapter sourceAdapter;
    private boolean loading = true;
    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        presenter.requestDataFromServer();
        presenter.requestSources();
    }

    private void init() {

        presenter = new MainPresenter(this, new HomeNetwork());
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.addView(progressBar);
        sources_rec = findViewById(R.id.sources_rec);
        news_rec = findViewById(R.id.news_rec);
        news_rec.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(this, this);
        news_rec.setAdapter(newsAdapter);
        sources_rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        sourceAdapter = new SourceAdapter(this);
        sources_rec.setAdapter(sourceAdapter);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        news_rec.setLayoutManager(layoutManager);
        if(layoutManager.findFirstCompletelyVisibleItemPosition()==0){
        }

// To check if at the bottom of recycler view
//      if(layoutManager.findLastCompletelyVisibleItemPosition()==getNews.size()-1){
            // Its at bottom



    }

    @Override
    public void recyclerViewListClicked(int position) {
        Intent intent = new Intent(this, DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("myNewsObj", getNews.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void setFav(int pos, int newsID, boolean isSelect) {
        RequestID requestID = new RequestID();
        getNews.get(pos).setIsSelectedFavorite(isSelect);
        presenter.requestFavoriteNews(requestID);

    }

    @Override
    public void setReadLater(int pos, int newsID, boolean isSelect) {
        RequestID requestID = new RequestID();
        getNews.get(pos).setIsSelectedReadLater(isSelect);
        presenter.requestReadLater(requestID);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }


    @Override
    public void setDataToRecyclerView(List<NewsModel> getNews) {
        this.getNews = getNews;
        newsAdapter.setNewsModels(getNews);
    }

    @Override
    public void setSources(List<SourcesModel> getSources) {
        this.getSources = getSources;
        sourceAdapter.setSourcesAdapter(getSources);

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(MainActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void setFavoriteNews(Boolean aBoolean) {
        newsAdapter.setNewsModels(getNews);
    }

    @Override
    public void setReadLater(Boolean aBoolean) {
        newsAdapter.setNewsModels(getNews);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
