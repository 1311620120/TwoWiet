package com.example.yuekao0428.model;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;


public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DiskCacheConfig cacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .setBaseDirectoryName("sss")
                .setMaxCacheSize(100)
                .setMaxCacheSizeOnLowDiskSpace(20 * ByteConstants.MB)
                .setMaxCacheSizeOnVeryLowDiskSpace(60 * ByteConstants.MB)
                .build();
        ImagePipelineConfig pipelineConfig = ImagePipelineConfig.newBuilder(this).setMainDiskCacheConfig(cacheConfig).build();
        Fresco.initialize(this,pipelineConfig);
    }

}
