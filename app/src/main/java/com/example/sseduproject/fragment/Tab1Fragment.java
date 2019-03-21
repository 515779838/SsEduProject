package com.example.sseduproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.cyf.nfcproject.bean.BaseBean;
import com.example.sseduproject.R;
import com.example.sseduproject.activity.MsgActivity;
import com.example.sseduproject.activity.slj.SLJActivity;
import com.example.sseduproject.adapter.SubjectAdapter;
import com.example.sseduproject.base.BaseFragment;
import com.example.sseduproject.bean.HomeBean;
import com.example.sseduproject.tools.NetTools;
import com.example.sseduproject.url.Urls;
import com.example.sseduproject.view.SlideShowView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends BaseFragment implements View.OnClickListener{


    private ImageView iv_right;

    private LinearLayout ll_slj;
    private LinearLayout ll_tkzyk;
    private LinearLayout ll_zmtzyk;
    private LinearLayout ll_gkk;
    private LinearLayout ll_zyjy;
    private LinearLayout ll_cglx;
    private LinearLayout ll_xxzx;
    private LinearLayout ll_yxfx;

    private HomeBean homeBean;

    private GridView gridView;

    private SlideShowView slideShowView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView =inflater.inflate(R.layout.fragment_tab1, container, false);
        initView();

        setTextTitle(true,"首页");
        initView();
        net_getIndex();
        return mView;
    }

    private void initView() {

        gridView = mView.findViewById(R.id.gridView);
        slideShowView = mView.findViewById(R.id.slideShowView);

        ll_slj = mView.findViewById(R.id.ll_slj);
        ll_tkzyk = mView.findViewById(R.id.ll_tkzyk);
        ll_zmtzyk = mView.findViewById(R.id.ll_zmtzyk);
        ll_gkk = mView.findViewById(R.id.ll_gkk);
        ll_zyjy = mView.findViewById(R.id.ll_zyjy);
        ll_cglx = mView.findViewById(R.id.ll_cglx);
        ll_xxzx = mView.findViewById(R.id.ll_xxzx);
        ll_yxfx = mView.findViewById(R.id.ll_yxfx);

        ll_slj.setOnClickListener(this);
        ll_tkzyk.setOnClickListener(this);
        ll_zmtzyk.setOnClickListener(this);
        ll_gkk.setOnClickListener(this);
        ll_zyjy.setOnClickListener(this);
        ll_xxzx.setOnClickListener(this);
        ll_cglx.setOnClickListener(this);
        ll_yxfx.setOnClickListener(this);

        iv_right = mView.findViewById(R.id.iv_right);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(R.mipmap.home_icon_message);

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), MsgActivity.class));
            }
        });

        // 轮播Banner
        slideShowView.setTimeInterval(5);
        slideShowView.setOnItemClickListener(new SlideShowView.OnSlideShowViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                if (advertiseBeanList.size() > 0 &&
//                        advertiseBeanList.get(position).getJumpUrl() != null &&
//                        !advertiseBeanList.get(position).getJumpUrl().equals("")) {
//                    Intent intent = new Intent(getActivity(), WebActivity.class);
//                    intent.putExtra("title", "轮播图");
//                    intent.putExtra("url", advertiseBeanList.get(position).getJumpUrl());
//                    startActivity(intent);
//                }
            }
        });

    }

    /**
     * 首页应用查询
     */
    private void net_getIndex() {

        Map<String, String> map = new HashMap<>();
        NetTools.net("get",map, new Urls().index, getActivity(), new NetTools.MyCallBack() {
            @Override
            public void getData(BaseBean response) {
                Log.e("zj", "getAppList = " + response.getData());
                homeBean = new Gson().fromJson(response.getData(), HomeBean.class);
                setData();

            }
        });
    }

    private void setData() {

        if (homeBean.getBanners() != null) {
            String imgs[] = new String[homeBean.getBanners().size()];
            for (int i = 0; i < homeBean.getBanners().size(); i++) {
                imgs[i] = homeBean.getBanners().get(i).getPic();
//                        imgs[i] = Urls.file_access_host + advertiseBeanList.get(i).getMediaUrl();
            }
            slideShowView.initUI(getActivity(), 2, imgs);
        }

        gridView.setAdapter(new SubjectAdapter( getActivity(),homeBean.getVideos()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {//四六级
            case R.id.ll_slj:
                startActivity(new Intent(getActivity(), SLJActivity.class));
                break;
            case R.id.ll_tkzyk:

                break;
            case R.id.ll_zmtzyk:

                break;
            case R.id.ll_gkk:

                break;
            case R.id.ll_zyjy:

                break;
            case R.id.ll_cglx:

                break;
            case R.id.ll_xxzx:

                break;
            case R.id.ll_yxfx:

                break;
        }
    }
}
