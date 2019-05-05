package com.example.yuekao0428.view.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuekao0428.R;
import com.example.yuekao0428.model.GowWuBean;
import com.example.yuekao0428.model.SelectBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GouAdapter extends BaseExpandableListAdapter {
    Context context; GowWuBean gowWuBean;TextView priceAll;

    private ParentHolder parentholder;
    Button del;
    Button add;


    public GouAdapter(Context context, GowWuBean gowWuBean, TextView priceAll) {
       this.context=context;
       this.gowWuBean=gowWuBean;
       this.priceAll=priceAll;
    }

    @Override
    public int getGroupCount() {
        return gowWuBean.getData().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return gowWuBean.getData().get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        parentholder = new ParentHolder();
        View view = LayoutInflater.from(context).inflate(R.layout.parentfragment, null);
        parentholder.parentCheck = view.findViewById(R.id.parentCheck);
        parentholder.parentName = view.findViewById(R.id.parentName);

         parentholder.parentCheck.setChecked(gowWuBean.getData().get(groupPosition).getisCheck());
         parentholder.parentName.setText(gowWuBean.getData().get(groupPosition).getSellerName());

        GowWuBean.DataBean dataBean = gowWuBean.getData().get(groupPosition);
        parentholder.parentCheck.setChecked(dataBean.getisCheck());
     parentholder.parentCheck.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             CheckBox checkBox=(CheckBox)v;
             boolean checked = checkBox.isChecked();
             gowWuBean.getData().get(groupPosition).setisCheck(checked);
             SelectAll(groupPosition,checked);
             setAllprice();
             notifyDataSetChanged();

         }
     });

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChlidHolder chlidHolder;

         chlidHolder = new ChlidHolder();
        View view = LayoutInflater.from(context).inflate(R.layout.childfragment, null);
        chlidHolder. childCheck = view.findViewById(R.id.childCheck);
        chlidHolder. childimg = view.findViewById(R.id.childimg);
        chlidHolder .childName = view.findViewById(R.id.childName);
        chlidHolder. childPrice = view.findViewById(R.id.childPrice);
        chlidHolder .num = view.findViewById(R.id.num);

        Button add = view.findViewById(R.id.add);
        Button  del = view.findViewById(R.id.del);

         chlidHolder.childCheck.setChecked(gowWuBean.getData().get(groupPosition).getList().get(childPosition).getisCheck());
       chlidHolder.childName.setText(gowWuBean.getData().get(groupPosition).getList().get(childPosition).getTitle());
        chlidHolder.childPrice.setText(gowWuBean.getData().get(groupPosition).getList().get(childPosition).getPrice()+"");
        chlidHolder.num.setText(gowWuBean.getData().get(groupPosition).getList().get(childPosition).getNum()+"");
        chlidHolder.childimg.setImageURI(gowWuBean.getData().get(groupPosition).getList().get(childPosition).getImages());



        chlidHolder.childCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox=(CheckBox)v;
                GowWuBean.DataBean dataBean = gowWuBean.getData().get(groupPosition);
                GowWuBean.DataBean.ListBean listBean = gowWuBean.getData().get(groupPosition).getList().get(childPosition);
                listBean.setisCheck(checkBox.isChecked());
                boolean SelectAll=isSelectAll(groupPosition);
                dataBean.setisCheck(SelectAll);
                setAllprice();
                notifyDataSetChanged();

            }

        });
        final List<GowWuBean.DataBean.ListBean> list = gowWuBean.getData().get(groupPosition).getList();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int  num1 = gowWuBean.getData().get(groupPosition).getList().get(childPosition).getNum();
              num1++;
                   list.get(childPosition).setNum(num1);
                chlidHolder.num.setText(num1 +"");
                Toast.makeText(context,num1+"",Toast.LENGTH_SHORT).show();
                setAllprice();

            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int  num1 = gowWuBean.getData().get(groupPosition).getList().get(childPosition).getNum();

                if (num1 <=1){
                    Toast.makeText(context,"不能再少了",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    num1--;
                    list.get(childPosition).setNum(num1);
                    chlidHolder.num.setText(num1 +"");
                    setAllprice();

                }
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class  ParentHolder{
        CheckBox parentCheck;
        TextView parentName;
    }
    class  ChlidHolder{
        CheckBox childCheck;
        SimpleDraweeView childimg;
        TextView childName;
        TextView childPrice;
        TextView num;

    }
    private boolean isSelectAll(int groupPosition) {
        for (int i = 0; i <gowWuBean.getData().get(groupPosition).getList().size() ; i++) {
            GowWuBean.DataBean.ListBean listBean = gowWuBean.getData().get(groupPosition).getList().get(i);
            boolean check=listBean.getisCheck();
            if (!check){
                return  false;
            }
        }
        return  true;
    }
    private void SelectAll(int groupPosition, boolean checked) {
        for (int i = 0; i <gowWuBean.getData().get(groupPosition).getList().size() ; i++) {
            GowWuBean.DataBean.ListBean listBean = gowWuBean.getData().get(groupPosition).getList().get(i);
            listBean.setisCheck(checked);
        }
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
}
