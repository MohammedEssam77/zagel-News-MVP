package com.example.win10.zagel.presenter;

import com.example.win10.zagel.interfaces.MainContract;
import com.example.win10.zagel.interfaces.MyCallback;
import com.example.win10.zagel.models.NewsModel;
import com.example.win10.zagel.models.RequestID;
import com.example.win10.zagel.models.SourcesModel;

import java.util.List;

public class MainPresenter implements MainContract.presenter {
    private MainContract.MainView view;
    private MainContract.Model model;

    public MainPresenter(MainContract.MainView view, MainContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void requestDataFromServer() {
        view.showProgress();
        model.getHomeList(new MyCallback<List<NewsModel>>() {
            @Override
            public void onSuccess(List<NewsModel> getNews) {
                if (view != null) {
                    view.setDataToRecyclerView(getNews);
                    view.hideProgress();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (view != null) {
                    view.onResponseFailure(t);
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void requestSources() {
        view.showProgress();
        model.getSourcesList(new MyCallback<List<SourcesModel>>() {
            @Override
            public void onSuccess(List<SourcesModel> getSources) {
                if (view != null) {
                    view.setSources(getSources);
                    view.hideProgress();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (view != null) {
                    view.onResponseFailure(t);
                    view.hideProgress();
                }
            }
        });
    }

    @Override
    public void requestFavoriteNews(RequestID requestID) {
        model.getFavoriteNews(requestID, new MyCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean response) {
                view.setFavoriteNews(response);

            }

            @Override
            public void onFailure(Throwable t) {

                if (view != null) {
                    view.onResponseFailure(t);
                }
            }
        });
    }

    @Override
    public void requestReadLater(RequestID requestID) {
            model.getReadLater(requestID, new MyCallback<Boolean>() {

                @Override
                public void onSuccess(Boolean response) {
                    view.setReadLater(response);

                }

                @Override
                public void onFailure(Throwable t) {
                    if (view != null) {
                        view.onResponseFailure(t);
                    }
                }
            });

    }
}
