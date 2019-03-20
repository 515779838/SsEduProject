package com.example.sseduproject.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.sseduproject.R
import com.hhkj.highschool.base.BaseActivity

class WSZL2Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wszl2)

        setLeftBtn(true)
        setTextTitle("完善资料")

        setRightBtn(true, "保存", View.OnClickListener {
            //            net_forget_password()

            startActivity(Intent(this@WSZL2Activity,MainActivity::class.java))

        })
    }
}
