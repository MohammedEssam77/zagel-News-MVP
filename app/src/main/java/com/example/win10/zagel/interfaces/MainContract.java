package com.example.win10.zagel.interfaces;

import com.example.win10.zagel.models.NewsModel;
import com.example.win10.zagel.models.RequestID;
import com.example.win10.zagel.models.SourcesModel;

import java.util.List;

public interface MainContract {
    interface Model {
        void getHomeList(MyCallback<List<NewsModel>> newsMyCallback);

        void getSourcesList(MyCallback<List<SourcesModel>> sourcesMyCallback);

        void getFavoriteNews(RequestID requestID, MyCallback<Boolean> setFavoriteNews);

        void getReadLater(RequestID requestID, MyCallback<Boolean> setReadLater);
    }

    interface presenter {
        void onDestroy();             //when the view is destroyed

        void requestDataFromServer(); //requesting the data from the server

        void requestSources();

        void requestFavoriteNews(RequestID requestID);

        void requestReadLater(RequestID requestID);

    }

    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<NewsModel> getNews);

        void setSources(List<SourcesModel> getSources);

        void onResponseFailure(Throwable throwable);

        void setFavoriteNews(Boolean aBoolean);

        void setReadLater(Boolean aBoolean);

    }
}