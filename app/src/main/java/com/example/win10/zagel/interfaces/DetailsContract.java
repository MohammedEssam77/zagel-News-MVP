package com.example.win10.zagel.interfaces;


import com.example.win10.zagel.models.DetailsModel;
import com.example.win10.zagel.models.NewsModel;


public interface DetailsContract {
    interface Model {

        void getDetailsList(NewsModel newsModel, MyCallback<DetailsModel> detailsMyCallback);

    }

    interface DetailsPresenter {

        void onDestroy();

        void requestData(NewsModel newsModel);

    }

    interface DetailsView {
        void dataResponse(DetailsModel detailsModel);

        void onResponseFailure(Throwable throwable);

    }
}
