package com.example.yuekao0428.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuekao0428.R;
import com.example.yuekao0428.model.SelectBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class SelectAdapter extends RecyclerView.Adapter<SelectAdapter.ViewHolder> {
    Context context; List<SelectBean.ResultBean> result;
    public SelectAdapter(Context context, List<SelectBean.ResultBean> result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.ilist, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
                 viewHolder.sele_img.setImageURI(result.get(i).getMasterPic());
                 viewHolder.sele_name.setText(result.get(i).getCommodityName());
                 viewHolder.sele_price.setText(result.get(i).getPrice()+"");
    }


    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView sele_img;
        private final TextView sele_name;
        private final TextView sele_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sele_img = itemView.findViewById(R.id.sele_img);
            sele_name = itemView.findViewById(R.id.sele_name);
            sele_price = itemView.findViewById(R.id.sele_price);
        }
    }
}
