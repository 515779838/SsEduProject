package com.example.sseduproject.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cyf.nfcproject.bean.BaseBean;
import com.example.sseduproject.R;
import com.example.sseduproject.activity.MsgActivity;
import com.example.sseduproject.base.BaseFragment;
import com.example.sseduproject.tools.NetTools;
import com.example.sseduproject.url.Urls;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1Fragment extends BaseFragment {


    private ImageView iv_right;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =inflater.inflate(R.layout.fragment_tab1, container, false);
        initView();

        setTextTitle(false,"首页");
        net_getIndex();
        return mView;
    }

    private void initView() {
        iv_right = mView.findViewById(R.id.iv_right);
        iv_right.setVisibility(View.VISIBLE);
        iv_right.setImageResource(R.mipmap.home_icon_message);

        iv_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(), MsgActivity.class));
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

            }
        });
    }

}
