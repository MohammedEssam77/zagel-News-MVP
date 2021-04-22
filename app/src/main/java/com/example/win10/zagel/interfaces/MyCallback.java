package com.example.win10.zagel.interfaces;

public interface MyCallback<T> {
    void onSuccess(T response);

    void onFailure(Throwable t);
}
