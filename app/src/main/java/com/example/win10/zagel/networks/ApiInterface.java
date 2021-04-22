package com.example.win10.zagel.networks;

import com.example.win10.zagel.models.DetailsModel;
import com.example.win10.zagel.models.RequestID;
import com.example.win10.zagel.models.NewsModel;
import com.example.win10.zagel.models.SourcesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers({
            "Content-type: application/json",
            "DeviceToken: 29A2506C-7C1C-40B9-8640-9ECA36659401",
            "AuthToken: dVMFDDAcZ1"
    })
    @GET("Api/News/GetAllSource/209361/0")
    Call<List<SourcesModel>> getSources();

    @Headers({
            "Content-type: application/json",
            "DeviceToken: 29A2506C-7C1C-40B9-8640-9ECA36659401",
            "AuthToken: dVMFDDAcZ1"
    })
    @GET("Api/News/GetNewsHome/209361/0/0/20/0")
    Call<List<NewsModel>> getNews();

    @Headers({
            "Content-type: application/json",
            "DeviceToken: 29A2506C-7C1C-40B9-8640-9ECA36659401",
            "AuthToken: dVMFDDAcZ1"
    })
    @GET("Api/News/Details/209361/{NewsID}")
    Call<DetailsModel> getDetails(@Query("NewsID") int NewsID);

    @Headers({
            "Content-type: application/json",
            "DeviceToken: 29A2506C-7C1C-40B9-8640-9ECA36659401",
            "AuthToken: dVMFDDAcZ1"
    })
    @POST("Api/News/SetFavoriteNewsByUser")
    Call<Boolean> setFavoriteNews(@Body RequestID requestID);

    @Headers({
            "Content-type: application/json",
            "DeviceToken: 29A2506C-7C1C-40B9-8640-9ECA36659401",
            "AuthToken: dVMFDDAcZ1"
    })
    @POST("Api/News/SetReadLaterNewsByUser")
    Call<Boolean> setReadLater(@Body RequestID requestID);
}