package com.example.sseduproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sseduproject.R;
import com.example.sseduproject.bean.SelectBean;

import java.util.ArrayList;
import java.util.List;


public class SelectAdapter extends BaseAdapter {

    private int index = 0;
    private List<SelectBean> list;
    private Context context;
    private String flag = "";

    public SelectAdapter(Context context, List<SelectBean> list) {
        this.list = list;
        this.context = context;
    }

    public SelectAdapter(String flag,Context context, List<SelectBean> list) {
        this.flag = flag;
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_select, parent, false);
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.view_line = (View) convertView.findViewById(R.id.view_line);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.tv_name.setText(list.get(position).getName());

        if (flag.equals("")){
            if (index == position){
                holder.tv_name.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                holder.view_line.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }else {
                holder.tv_name.setTextColor(context.getResources().getColor(R.color.color_2c));
                holder.view_line.setBackgroundColor(context.getResources().getColor(R.color.hint));
            }
        }

        return convertView;
    }

    public class Holder {
        public TextView tv_name;
        public View view_line;
    }

    // 设置高亮选中
    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

}
