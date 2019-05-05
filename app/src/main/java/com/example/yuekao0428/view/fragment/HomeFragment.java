package com.example.yuekao0428.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yuekao0428.R;
import com.example.yuekao0428.model.SelectBean;
import com.example.yuekao0428.presenter.ShowPresenter;
import com.example.yuekao0428.view.activity.ViewActivity;
import com.example.yuekao0428.view.adapter.SelectAdapter;
import com.example.yuekao0428.view.interfaces.IMainView;

import java.util.List;


public class HomeFragment extends Fragment implements IMainView {

    private View view;
    private RecyclerView recycler;

    int page=1;
    int count=10;
    private EditText edit_sou;
    private ImageView back;
    private ImageView butsou;
    private ViewActivity view_self;
    private ShowPresenter showPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.homefragment, null);
        initView();
        initData();

        return view;
    }


    private void initView() {
        view_self = view.findViewById(R.id.view_self);
        edit_sou = view.findViewById(R.id.edit_sou);
        back = view.findViewById(R.id.back);
        butsou = view.findViewById(R.id.Butsou);
        recycler = view.findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
    }
    private void initData() {
        showPresenter = new ShowPresenter(this);

        showPresenter.setView(this);
        butsou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keywork = edit_sou.getText().toString();
                TextView textView = new TextView(getActivity());
               // textView.setTag(name);
                textView.setText(keywork);
                view_self.addView(textView);
                view_self.requestLayout();
                showPresenter.ShowData(keywork, page, count);
            }
        });
    }

    @Override
    public void onSuccess(Object o) {
        SelectBean selectBean=(SelectBean)o;
        List<SelectBean.ResultBean> result = selectBean.getResult();
        SelectAdapter selectAdapter = new SelectAdapter(getActivity(),result);
        recycler.setAdapter(selectAdapter);
    }

    @Override
    public void onFail(String err) {

    }
}
