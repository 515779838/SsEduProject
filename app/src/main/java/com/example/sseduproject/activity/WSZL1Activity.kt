package com.example.sseduproject.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import com.example.sseduproject.R
import com.example.sseduproject.tools.MyCountTimer
import com.example.sseduproject.tools.NetTools
import com.example.sseduproject.url.Urls
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_wszl1.*
import org.jetbrains.anko.toast

/**
 * 完善资料1
 */
class WSZL1Activity : BaseActivity() {
    private var timeCount: MyCountTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wszl1)
        setLeftBtn(true)
        setTextTitle("完善资料")


        setRightBtn(true, "下一步", View.OnClickListener {
//            net_forget_password()
            startActivity(Intent(this@WSZL1Activity,WSZL2Activity::class.java))
        })
        onClick()
        timeCount = MyCountTimer(tv_code, 0xffffffff.toInt(), 0xffffffff.toInt())//传入了文字颜色值

    }


    private fun onClick() {
        ll_1.setOnClickListener {
            v_1.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_2.setBackgroundColor(resources.getColor(android.R.color.transparent))
        }
        ll_2.setOnClickListener {
            v_2.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_1.setBackgroundColor(resources.getColor(android.R.color.transparent))

        }

        ll_3.setOnClickListener {
            v_3.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_4.setBackgroundColor(resources.getColor(android.R.color.transparent))
        }
        ll_4.setOnClickListener {
            v_4.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_3.setBackgroundColor(resources.getColor(android.R.color.transparent))

        }

        tv_code.setOnClickListener {
            net_common_verCode()
        }
    }

    private fun net_common_verCode() {
        val map = hashMapOf<String, String>()
        map.put("phone", et_name.text.toString())
        NetTools.net("post",map, Urls.captcha, this) { response ->
            timeCount!!.start()
            toast(response.msg!!)
        }
    }
}
