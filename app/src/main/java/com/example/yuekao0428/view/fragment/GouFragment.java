package com.example.yuekao0428.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.yuekao0428.R;
import com.example.yuekao0428.model.GowWuBean;
import com.example.yuekao0428.presenter.GouWuPresenter;
import com.example.yuekao0428.view.adapter.GouAdapter;
import com.example.yuekao0428.view.interfaces.IMainView;

import java.util.List;


public class GouFragment extends Fragment  implements IMainView {

    private View view;
    private ExpandableListView expand;
    private CheckBox checkAll;
    private TextView priceAll;
    private GowWuBean gowWuBean;
    private GouAdapter gouAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.goufragment, null);
        initData();
        return view;
    }

    private void initData() {
        expand = view.findViewById(R.id.expand);
        checkAll = view.findViewById(R.id.checkAll);
        priceAll = view.findViewById(R.id.priceAll);
        GouWuPresenter gouWuPresenter = new GouWuPresenter(this);
        gouWuPresenter.GowWuData();
        gouWuPresenter.setView(this);

        checkAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 if (checkAll.isChecked()){
                     selectAll(true);
                 }else {
                     selectAll(false);
                 }
                setAllprice();
              gouAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(Object o) {
        gowWuBean = (GowWuBean)o;
        gouAdapter = new GouAdapter(getActivity(), gowWuBean,priceAll);
         expand.setAdapter(gouAdapter);
        for (int i = 0; i < gowWuBean.getData().size() ; i++) {
        expand.expandGroup(i);
        }
    }

 public  void  selectAll(boolean ischeck){
     for (int i = 0; i <gowWuBean.getData().size() ; i++) {
         GowWuBean.DataBean dataBean = gowWuBean.getData().get(i);
         dataBean.setisCheck(ischeck);
         List<GowWuBean.DataBean.ListBean> list = dataBean.getList();
         for (int j = 0; j <list.size() ; j++) {
             GowWuBean.DataBean.ListBean listBean = list.get(j);
             listBean.setisCheck(ischeck);

         }
     }
       gouAdapter.notifyDataSetChanged();
 }
 public  void setAllprice(){
        double money=0;
     for (int i = 0; i <gowWuBean.getData().size() ; i++) {
         for (int j = 0; j <gowWuBean.getData().get(i).getList().size() ; j++) {
              if (gowWuBean.getData().get(i).getList().get(j).getisCheck()==true){
               double num= gowWuBean.getData().get(i).getList().get(j).getNum()*gowWuBean.getData().get(i).getList().get(j).getPrice();
               money+=num;
              }
         }
         priceAll.setText("总价："+money);
     }
 }
    @Override
    public void onFail(String err) {

    }
}
