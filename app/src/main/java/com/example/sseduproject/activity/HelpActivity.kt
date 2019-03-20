package com.example.sseduproject.activity

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.sseduproject.R
import com.example.sseduproject.constant.Constant
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_help.*

class HelpActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)

        setLeftBtn(true)
        setTextTitle("请求帮助")

        tv_call.text = Constant.PHONE
        tv_call.setOnClickListener {
            var mIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constant.PHONE));//跳转到拨号界面，同时传递电话号码
            startActivity(mIntent)
        }
    }
}
