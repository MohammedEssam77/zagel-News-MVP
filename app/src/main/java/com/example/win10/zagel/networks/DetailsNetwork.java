package com.example.win10.zagel.networks;

import com.example.win10.zagel.interfaces.DetailsContract;
import com.example.win10.zagel.interfaces.MyCallback;
import com.example.win10.zagel.models.DetailsModel;
import com.example.win10.zagel.models.NewsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsNetwork implements DetailsContract.Model {
    @Override
    public void getDetailsList(NewsModel newsModel, final MyCallback<DetailsModel> detailsMyCallback) {
        ApiInterface apiInterface =
                ApiNetwork.getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getDetails(newsModel.getID()).enqueue(new Callback<DetailsModel>() {

            @Override
            public void onResponse(Call<DetailsModel> call, Response<DetailsModel> response) {
                DetailsModel detailsModel = response.body();
                detailsMyCallback.onSuccess(detailsModel);

            }

            @Override
            public void onFailure(Call<DetailsModel> call, Throwable t) {
                detailsMyCallback.onFailure(t);


            }
        });

    }

}

