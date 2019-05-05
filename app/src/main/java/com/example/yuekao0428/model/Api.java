package com.example.yuekao0428.model;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

        public interface Api {
          @GET("small/commodity/v1/findCommodityByKeyword")
            Observable <SelectBean> Show(@Query("keyword")String keywork,@Query("page") int page,@Query("count") int count);
        @Multipart
         @POST("small/user/verify/v1/modifyHeadPic")
          Observable <ChuanBean> Chuan(@Header("userId") int userId, @Header("sessionId") String sessionId, @Part MultipartBody.Part file);
        @GET("http://172.17.8.100/ks/product/getCarts?uid=51")
            Observable <GowWuBean> GowWu();

        }
