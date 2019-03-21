package com.example.sseduproject.activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.sseduproject.R
import com.example.sseduproject.adapter.SelectAdapter
import com.example.sseduproject.bean.SelectBean
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_select_year.*

class SelectYearActivity : BaseActivity() {

    companion object {
        var callBack: SelectDictionaryCallBack? = null
    }

    fun selectDictionary(activity: Activity, title: String, gradeId: String, classId: String, type: String, callBack: SelectDictionaryCallBack) {
        SelectYearActivity.callBack = callBack
        val intent = Intent(activity, SelectYearActivity::class.java)
        intent.putExtra("title", title)
        intent.putExtra("gradeId", gradeId)
        intent.putExtra("classId", classId)
        intent.putExtra("type", type)
        activity.startActivity(intent)
    }

    interface SelectDictionaryCallBack {
        fun getData(bean: SelectBean)
    }

    private var list4 =  ArrayList<SelectBean>()
    private var selectAdapter: SelectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_year)

        setLeftBtn(true)
        setTextTitle("请选择入学年份")
        initView()
    }

    private fun initView() {
        var bean1 = SelectBean()
        bean1.name = "2015年以前"

        var bean2 = SelectBean()
        bean2.name = "2015"

        var bean3 = SelectBean()
        bean3.name = "2016"

        var bean4 = SelectBean()
        bean4.name = "2017"

        var bean5 = SelectBean()
        bean5.name = "2018"

        list4.add(bean1)
        list4.add(bean2)
        list4.add(bean3)
        list4.add(bean4)
        list4.add(bean5)
        selectAdapter = SelectAdapter("1",this@SelectYearActivity, list4)

        lv_year.adapter =selectAdapter

        lv_year.setOnItemClickListener { parent, view, position, id ->

            callBack!!.getData(list4!![position])
            finish()
        }
    }
}
