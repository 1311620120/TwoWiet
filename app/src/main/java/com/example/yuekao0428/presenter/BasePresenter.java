package com.example.yuekao0428.presenter;


public class BasePresenter<V> {
     public  V view;

    public void setView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }
}
