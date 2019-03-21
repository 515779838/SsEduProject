package com.example.sseduproject.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View

import com.example.sseduproject.R
import com.example.sseduproject.tools.MyCountTimer
import com.example.sseduproject.tools.NetTools
import com.example.sseduproject.url.Urls
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_forget_pw.*
import org.jetbrains.anko.toast

class ForgetPwActivity : BaseActivity() {

    private var timeCount: MyCountTimer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pw)

        timeCount = MyCountTimer(tv_code, 0xffffffff.toInt(), 0xffffffff.toInt())//传入了文字颜色值
        onClick()

        setLeftBtn(true)
        setTextTitle("忘记密码")

        setRightBtn(true, "保存", View.OnClickListener {
            net_forget_password()
        })
    }


    private fun onClick() {

        tv_code.setOnClickListener {
            net_common_verCode()
        }
        togglePwd.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                //如果选中，显示密码
                et_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                //否则隐藏密码
                et_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    private fun net_common_verCode() {
        if (et_phone.text.isEmpty()){
            toast("手机号不能为空")
            return
        }
        val map = hashMapOf<String, String>()
        map.put("phone", et_phone.text.toString())
        NetTools.net("post",map, Urls.captcha, this) { response ->
            timeCount!!.start()
            toast(response.msg!!)
        }
    }

    private fun net_forget_password() {
        if (et_phone.text.isEmpty()){
            toast("手机号不能为空")
            return
        }

        if (et_code.text.isEmpty()){
            toast("验证码不能为空")
            return
        }
        if (et_pwd.text.isEmpty()){
            toast("密码不能为空")
            return
        }
        val map = hashMapOf<String, String>()
        map.put("phone", et_phone.text.toString())
        map.put("captcha", et_code.text.toString())
        map.put("password", et_pwd.text.toString())
        NetTools.net("patch",map, Urls.forget_password, this) { response ->
            timeCount!!.start()
            toast(response.msg!!)
        }
    }
}
