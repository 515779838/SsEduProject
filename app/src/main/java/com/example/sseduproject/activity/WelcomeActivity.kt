package com.example.sseduproject.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.cyf.nfcproject.tools.SPTools

import com.example.sseduproject.R
import com.example.sseduproject.constant.Constant

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        Handler().postDelayed({

            val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()

//            if (SPTools[this, Constant.ISLOGIN,""] == ""){
//                val intent = Intent(this@WelcomeActivity, LoginActivity::class.java)
//                startActivity(intent)
//                finish()
//            }else{
//                val intent = Intent(this@WelcomeActivity, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }

//                net_appCheckUpdate()

        }, 500)
    }
}
