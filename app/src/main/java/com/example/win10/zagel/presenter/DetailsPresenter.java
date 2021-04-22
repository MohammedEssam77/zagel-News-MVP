package com.example.win10.zagel.presenter;

import com.example.win10.zagel.interfaces.DetailsContract;
import com.example.win10.zagel.interfaces.MyCallback;
import com.example.win10.zagel.models.DetailsModel;
import com.example.win10.zagel.models.NewsModel;


public class DetailsPresenter implements DetailsContract.DetailsPresenter {
    private DetailsContract.DetailsView view;
    private DetailsContract.Model model;

    public DetailsPresenter(DetailsContract.DetailsView view, DetailsContract.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onDestroy() {
        view = null;

    }

    @Override
    public void requestData(NewsModel newsModel) {
        model.getDetailsList(newsModel, new MyCallback<DetailsModel>() {
            @Override
            public void onSuccess(DetailsModel response) {
                view.dataResponse(response);

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




