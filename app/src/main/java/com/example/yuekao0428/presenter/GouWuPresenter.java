package com.example.yuekao0428.presenter;

import com.example.yuekao0428.model.GowWuBean;
import com.example.yuekao0428.model.HttpUtils;
import com.example.yuekao0428.view.fragment.GouFragment;
import com.example.yuekao0428.view.interfaces.IMainView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GouWuPresenter extends BasePresenter<IMainView<GowWuBean>> {

    private final HttpUtils intants;

    public GouWuPresenter(GouFragment gouFragment){
        intants = HttpUtils.getIntants();
     }
      public  void  GowWuData(){
          Observable<GowWuBean> gowWu = intants.api.GowWu();
          gowWu.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new Consumer<GowWuBean>() {
                      @Override
                      public void accept(GowWuBean gowWuBean) throws Exception {
                          getView().onSuccess(gowWuBean);
                      }
                  });
      }
}
