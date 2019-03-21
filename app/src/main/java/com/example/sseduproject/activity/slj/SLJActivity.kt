package com.example.sseduproject.activity.slj

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.sseduproject.R
import com.hhkj.highschool.base.BaseActivity

/**
 * 四六级
 */
class SLJActivity : BaseActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slj)
        setLeftBtn(true)
        setTextTitle("四六级")
    }

    override fun onClick(v: View) {
        when (v.id) {
            //四级词汇
            R.id.ll_sjch -> {
                var intent = Intent(this@SLJActivity, SLCHActivity::class.java)
                intent.putExtra("title","四级词汇")
                startActivity(intent)
            }
            //六级词汇
            R.id.ll_ljch -> {
                var intent = Intent(this@SLJActivity, SLCHActivity::class.java)
                intent.putExtra("title","六级词汇")
                startActivity(intent)
            }
            //六级题库
            R.id.ll_sjtk -> {

            }
            //六级题库
            R.id.ll_ljtk -> {

            }
        }
    }
}
