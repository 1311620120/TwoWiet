package com.example.yuekao0428.view.interfaces;

public interface IMainView<T> {
     void  onSuccess(T t);
     void  onFail(String err);
}
