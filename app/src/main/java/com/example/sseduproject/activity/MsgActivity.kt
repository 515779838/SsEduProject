package com.example.sseduproject.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.sseduproject.R
import com.hhkj.highschool.base.BaseActivity

class MsgActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg)

        setLeftBtn(true)
        setTextTitle("消息中心")
        initView()
    }

    private fun initView() {

    }
}
