package com.example.win10.zagel.networks;

import com.example.win10.zagel.interfaces.MainContract;
import com.example.win10.zagel.interfaces.MyCallback;
import com.example.win10.zagel.models.NewsModel;
import com.example.win10.zagel.models.RequestID;
import com.example.win10.zagel.models.SourcesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeNetwork implements MainContract.Model {
    @Override
    public void getHomeList(final MyCallback<List<NewsModel>> listMyCallback) {
        ApiInterface apiInterface = ApiNetwork.getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getNews().enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                listMyCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                listMyCallback.onFailure(t);
            }
        });

    }

    @Override
    public void getSourcesList(final MyCallback<List<SourcesModel>> listMyCallback) {
        ApiInterface apiInterface = ApiNetwork.getRetrofitInstance().create(ApiInterface.class);
        apiInterface.getSources().enqueue(new Callback<List<SourcesModel>>() {
            @Override
            public void onResponse(Call<List<SourcesModel>> call, Response<List<SourcesModel>> response) {
                listMyCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<SourcesModel>> call, Throwable t) {
                listMyCallback.onFailure(t);
            }
        });
    }

    @Override
    public void getFavoriteNews(RequestID requestID, final MyCallback<Boolean> setFavoriteNews) {
        ApiInterface apiInterface = ApiNetwork.getRetrofitInstance().create(ApiInterface.class);
        Call<Boolean> call = apiInterface.setFavoriteNews(requestID);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                setFavoriteNews.onSuccess(response.body());

            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                setFavoriteNews.onFailure(t);

            }
        });
    }

    @Override
    public void getReadLater(RequestID requestID, final MyCallback<Boolean> setReadLater) {
        ApiInterface apiInterface = ApiNetwork.getRetrofitInstance().create(ApiInterface.class);
        Call<Boolean> call = apiInterface.setReadLater(requestID);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                setReadLater.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                setReadLater.onFailure(t);

            }
        });


    }
}
