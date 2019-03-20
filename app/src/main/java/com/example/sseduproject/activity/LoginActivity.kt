package com.example.sseduproject.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.cyf.nfcproject.tools.SPTools

import com.example.sseduproject.R
import com.example.sseduproject.bean.LoginBean
import com.example.sseduproject.constant.Constant
import com.example.sseduproject.tools.MyCountTimer
import com.example.sseduproject.tools.NetTools
import com.example.sseduproject.url.Urls
import com.google.gson.Gson
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {
    private var timeCount: MyCountTimer? = null
    private var type = "1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        onClick()
        timeCount = MyCountTimer(tv_code, 0xffffffff.toInt(), 0xffffffff.toInt())//传入了文字颜色值
    }

    private fun onClick() {
        ll_1.setOnClickListener {
            type = "1"
            v_1.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_2.setBackgroundColor(resources.getColor(android.R.color.transparent))
            ll_help1.visibility = View.VISIBLE
            ll_help2.visibility = View.GONE
            tv_code.visibility = View.VISIBLE

            iv_pwd.setImageResource(R.mipmap.login_icon_verification_code)

        }
        ll_2.setOnClickListener {
            type = "2"
            v_2.setBackgroundColor(resources.getColor(R.color.color_login_sel))
            v_1.setBackgroundColor(resources.getColor(android.R.color.transparent))
            ll_help2.visibility = View.VISIBLE
            ll_help1.visibility = View.GONE
            tv_code.visibility = View.GONE

            iv_pwd.setImageResource(R.mipmap.login_icon_password)
        }
        btn_login.setOnClickListener {
            net_login(type)
//            if (type == "1"){
//                net_login(type)
//            }else if (type == "2"){
//
//            }
        }
        ll_help1.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgetPwActivity::class.java))
        }
        ll_help2.setOnClickListener {
            startActivity(Intent(this@LoginActivity, HelpActivity::class.java))
        }
        tv_code.setOnClickListener {
            net_common_verCode()
        }
    }

    private fun net_login(type: String) {
        val map = HashMap<String, String>()

        var url = ""
        if (type == "1") {
            map["phone"] = et_name.text.toString().trim()
            map["captcha"] = et_pwd.text.toString()
            url = Urls.login_captcha
        } else if (type == "2") {
            map["phone"] = et_name.text.toString().trim()
            map["password"] = et_pwd.text.toString()
            url = Urls.login
        }

//        startActivity(Intent(this@LoginActivity, WSZL1Activity::class.java))
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))

//        NetTools.net("post",map, url, this) { response ->
//            Log.e("zj", "response = " + response.data.toString())
//            SPTools.put(this, Constant.TOKEN, response.data.toString())
//
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//        }
    }


    private fun getData(loginBean: LoginBean) {
//        SPTools.put(this, Constant.USERNAME, et_username.text.toString().trim())
//        SPTools.put(this, Constant.PASSWORD, et_password.text.toString().trim())
//        SPTools.put(this, Constant.TOKEN, loginBean.token)
//        SPTools.put(this, Constant.YHXLH, loginBean.yhxlh)
//        SPTools.put(this, Constant.YHTX, loginBean.yhtx)
//        SPTools.put(this, Constant.YHMC, loginBean.yhmc)
//        SPTools.put(this, Constant.USERTYPE, loginBean.usertype)
//        SPTools.put(this, Constant.USERTYPENAME, loginBean.usertypeName)
//        SPTools.put(this, Constant.SJHM, loginBean.sjhm)
//        SPTools.put(this, Constant.EMAIL, loginBean.email)
//        if (loginBean.usertype == "") {
//            // 管理员
//            startActivity(Intent(this@LoginActivity, Main2Activity::class.java))
//        } else {
//            // 教师/学生（家长）
//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//        }
//        finish()
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
