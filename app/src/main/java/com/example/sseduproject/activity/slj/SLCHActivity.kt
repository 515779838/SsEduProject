package com.example.sseduproject.activity.slj

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sseduproject.R
import com.hhkj.highschool.base.BaseActivity

/**
 * 四六级词汇
 */
class SLCHActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slch)
        setLeftBtn(true)
       var title = intent.getStringExtra("title")
        setTextTitle(title)
        var title2 = ""
        if (title == "四级词汇"){
            title2 = "切换六级"
        }else if (title == "六级词汇"){
            title2 = "切换四级"
        }

        setRightBtn(true,title2,View.OnClickListener {
            //调用接口 刷新数据
        })
        initView()
    }

    private fun initView() {

    }
}
