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
import cn.sharesdk.framework.PlatformActionListener
import cn.sharesdk.tencent.qq.ReceiveActivity.setPlatformActionListener
import cn.sharesdk.sina.weibo.SinaWeibo
import cn.sharesdk.framework.ShareSDK
import cn.sharesdk.framework.Platform
import cn.sharesdk.wechat.friends.Wechat
import com.example.sseduproject.bean.WxLoginBean


class LoginActivity : BaseActivity() {
    private var timeCount: MyCountTimer? = null
    private var type = "1"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        onClick()
        if (!et_phone.text.isEmpty()){
            et_phone.setSelection(et_phone.text.length)
        }
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
        ll_wechat.setOnClickListener {
            wx_login()
//            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
        }
    }

    private fun net_login(type: String) {

        if (et_phone.text.isEmpty()){
            toast("手机号不能为空")
            return
        }
        val map = HashMap<String, String>()

        var url = ""
        if (type == "1") {
            if (et_pwd.text.isEmpty()){
                toast("验证码不能为空")
                return
            }
            map["phone"] = et_phone.text.toString().trim()
            map["captcha"] = et_pwd.text.toString()
            url = Urls.login_captcha
        } else if (type == "2") {
            if (et_pwd.text.isEmpty()){
                toast("密码不能为空")
                return
            }
            map["phone"] = et_phone.text.toString().trim()
            map["password"] = et_pwd.text.toString()
            url = Urls.login
        }

//        startActivity(Intent(this@LoginActivity, WSZL1Activity::class.java))
//        startActivity(Intent(this@LoginActivity, MainActivity::class.java))

        NetTools.net("post", map, url, this) { response ->
            Log.e("zj", "response = " + response.data.toString())

            val resultBean = Gson().fromJson<LoginBean>(response.data.toString(), LoginBean::class.java!!)

            SPTools.put(this, Constant.TOKEN, resultBean.token)
            if (resultBean.data_integrity != null){
                SPTools.put(this, Constant.ISLOGIN, resultBean.data_integrity)
            }

//            var intent = Intent(this@LoginActivity, WSZL1Activity::class.java)
//            intent.putExtra("type", type)
//            startActivity(intent)

            if (SPTools[this,Constant.ISLOGIN,""] == "false"){
                var intent = Intent(this@LoginActivity, WSZL1Activity::class.java)
                intent.putExtra("type", type)
                startActivity(intent)
            }else{
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }


//            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        }
    }


    private fun wx_login() {


        val wechat = ShareSDK.getPlatform(Wechat.NAME)
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        wechat.platformActionListener = object : PlatformActionListener {

            override fun onError(arg0: Platform, arg1: Int, arg2: Throwable) {
                // TODO Auto-generated method stub
                arg2.printStackTrace()
            }

            override fun onComplete(arg0: Platform, arg1: Int, arg2: HashMap<String, Any>) {
                // TODO Auto-generated method stub
                //输出所有授权信息

                Log.e("zj","wx_login = "+arg0.getDb().exportData())
                var wxBean = Gson().fromJson<WxLoginBean>(arg0.getDb().exportData().toString(), WxLoginBean::class.java!!)
                net_open_wx_login(wxBean)
            }

            override fun onCancel(arg0: Platform, arg1: Int) {
                // TODO Auto-generated method stub

            }
        }
        wechat.showUser(null)//执行登录，登录后在回调里面获取用户资料
    }

    private fun net_common_verCode() {
        val map = hashMapOf<String, String>()
        map.put("phone", et_phone.text.toString())
        NetTools.net("post", map, Urls.captcha, this) { response ->
            timeCount!!.start()
            toast(response.msg!!)
        }
    }

    private fun net_open_wx_login(wxLoginBean: WxLoginBean) {
        Log.e("zj","11111")
        val map = hashMapOf<String, String>()
        map.put("openid", wxLoginBean.openid)
        map.put("nickname", wxLoginBean.nickname)
        if (wxLoginBean.gender == "0"){
            map.put("sex", "1")
        }else  if (wxLoginBean.gender == "1"){
            map.put("sex", "2")
        }

        map.put("headimgurl", wxLoginBean.icon)
        map.put("unionid", wxLoginBean.unionid)
        NetTools.net("post", map, Urls.open_wx_login, this) { response ->

            Log.e("zj","open_wx_login = "+response.data)

            val resultBean = Gson().fromJson<LoginBean>(response.data.toString(), LoginBean::class.java!!)

            SPTools.put(this, Constant.TOKEN, resultBean.token)
            if (resultBean.data_integrity != null){
                SPTools.put(this, Constant.ISLOGIN, resultBean.data_integrity)
            }

//            var intent = Intent(this@LoginActivity, WSZL1Activity::class.java)
//            intent.putExtra("type", type)
//            startActivity(intent)

            if (SPTools[this,Constant.ISLOGIN,""] == "false"){
                var intent = Intent(this@LoginActivity, WSZL1Activity::class.java)
                intent.putExtra("type", type)
                startActivity(intent)
            }else{
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
        }
    }
}
