package com.example.yuekao0428.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yuekao0428.R;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class MyFragment extends Fragment {

    private View view;
    private ImageView img;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.myfragment, null);
        initView();


        return view;
    }

    private void initView() {
        img = view.findViewById(R.id.img);
         img.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
               Intent  intent = new Intent();
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 intent.addCategory(Intent.CATEGORY_OPENABLE);
                 intent.setType("image/*");
                 startActivityForResult(intent, 111);
             }
         });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        File file = null;
        Uri uri = data.getData();
        img.setImageURI(uri);
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
            String path = Environment.getExternalStorageDirectory() + "/fmk11";
            File file1 = new File(path);
            if (!file1.exists()) {
                file1.mkdir();
            }
            file = new File(file, "555.png");
            BufferedOutputStream buf = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, buf);
            buf.flush();
            buf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
    }

}