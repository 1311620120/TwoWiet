package com.example.yuekao0428.presenter;

import android.provider.Telephony;

import com.example.yuekao0428.model.ChuanBean;
import com.example.yuekao0428.model.HttpUtils;
import com.example.yuekao0428.view.interfaces.IMainView;

import java.io.File;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ChuanPresenter extends BasePresenter <IMainView<ChuanBean>>{

    private final HttpUtils intants;

    public ChuanPresenter(){
        intants = HttpUtils.getIntants();

      }

    public void ChuanData(int userId, String sessionId, File file) {
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part filePart=MultipartBody.Part.createFormData("image",file.getName(),requestFile);
        Observable<ChuanBean> chuan = intants.api.Chuan(userId, sessionId, filePart);
        chuan.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChuanBean>() {
                    @Override
                    public void accept(ChuanBean chuanBean) throws Exception {
                        getView().onSuccess(chuanBean);
                    }
                });
    }

}
