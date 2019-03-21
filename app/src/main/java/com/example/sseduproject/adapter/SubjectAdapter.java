package com.example.sseduproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sseduproject.R;
import com.example.sseduproject.bean.HomeBean;
import com.example.sseduproject.bean.SelectBean;

import java.util.List;


public class SubjectAdapter extends BaseAdapter {

    private int index = 0;
    private List<HomeBean.VideosBean> list;
    private Context context;
    private String flag = "";

    public SubjectAdapter(Context context, List<HomeBean.VideosBean> list) {
        this.list = list;
        this.context = context;
    }

    public SubjectAdapter(String flag, Context context, List<HomeBean.VideosBean> list) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_tk, parent, false);
            holder.tv_title = convertView.findViewById(R.id.tv_title);
            holder.tv_watch = convertView.findViewById(R.id.tv_watch);
            holder.tv_like = convertView.findViewById(R.id.tv_like);
            holder.iv_img = convertView.findViewById(R.id.iv_img);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

//        holder.tv_title.setText(list.get(position).getName());

        RequestOptions requestOptions =new  RequestOptions();
//        requestOptions.error(R.mipmap.ic_defult_img).placeholder(R.mipmap.ic_defult_img);
//        Log.e("zj","imgs = "+Urls.file_access_host+"/" + list.get(position).getFOriginPath());
        Glide.with(context).load(list.get(position).getPic()).into(holder.iv_img);
        holder.tv_title.setText(list.get(position).getTitle());
        return convertView;
    }

    public class Holder {
        public TextView tv_title;
        public TextView tv_watch;
        public TextView tv_like;
        public ImageView iv_img;
    }


}
