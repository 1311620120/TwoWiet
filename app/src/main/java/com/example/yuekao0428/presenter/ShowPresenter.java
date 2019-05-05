package com.example.yuekao0428.presenter;

import com.example.yuekao0428.model.HttpUtils;
import com.example.yuekao0428.model.SelectBean;
import com.example.yuekao0428.view.fragment.HomeFragment;
import com.example.yuekao0428.view.interfaces.IMainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ShowPresenter extends  BasePresenter<IMainView<SelectBean>> {

    private final HttpUtils intants;

    public  ShowPresenter(HomeFragment homeFragment){
        intants = HttpUtils.getIntants();
    }

     public  void ShowData(String keywork,int page,int count){
         Observable<SelectBean> show = intants.api.Show(keywork, page, count);
         show.subscribeOn(Schedulers.io())
                 .observeOn(AndroidSchedulers.mainThread())
                 .subscribe(new Consumer<SelectBean>() {
                     @Override
                     public void accept(SelectBean selectBean) throws Exception {
                         getView().onSuccess(selectBean);
                     }
                 });
     }
}
