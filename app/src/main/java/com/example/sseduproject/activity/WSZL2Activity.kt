package com.example.sseduproject.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.sseduproject.R
import com.example.sseduproject.bean.SelectBean
import com.example.sseduproject.tools.NetTools
import com.example.sseduproject.url.Urls
import com.hhkj.highschool.base.BaseActivity
import kotlinx.android.synthetic.main.activity_wszl2.*
import org.jetbrains.anko.toast

class WSZL2Activity : BaseActivity() {

    private var current_school_code = ""//所在学校
    private var current_profession_id = ""//所学专业
    private var target_profession_ids = ""//目标专业
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wszl2)

        setLeftBtn(true)
        setTextTitle("完善资料")

        setRightBtn(true, "保存", View.OnClickListener {
            net_select_target_school_profession()

        })
        onClick()
    }

    private fun onClick() {
        ll_1.setOnClickListener {
            SelectSchoolActivity().selectDictionary(this, "完善信息"
                    , "", "", "3",
                    object : SelectSchoolActivity.SelectDictionaryCallBack {
                        override fun getData(bean: SelectBean) {
                            tv_name1.text = bean.xyName+"  "+bean.zyName
                            current_school_code = bean.xyId
                            current_profession_id = bean.zyId

                            tv_sel1.visibility = View.GONE
                        }
                    })

        }
        ll_2.setOnClickListener {
            SelectYearActivity().selectDictionary(this, "完善信息"
                    , "", "", "3",
                    object : SelectYearActivity.SelectDictionaryCallBack {
                        override fun getData(bean: SelectBean) {
                            tv_year.text = bean.name
                            tv_sel2.visibility = View.GONE
                        }
                    })
        }

        ll_3.setOnClickListener {
            SelectSchool2Activity().selectDictionary(this, "完善信息"
                    , "", "", "3",
                    object : SelectSchool2Activity.SelectDictionaryCallBack {
                        override fun getData(bean: SelectBean) {
                            tv_name2.text = bean.xyName+"  "+bean.zyName
                            Log.e("zj","bean = "+bean.zyId)
                            target_profession_ids = bean.zyId
                            tv_sel3.visibility = View.GONE
//                            tv_select1.text = "" + bean.ecName
//                            ecId = "" + bean.ecId
//                            ecName = "" + bean.ecName
                        }
                    })
        }
    }

    private fun net_select_target_school_profession() {

        if (current_school_code == "" ){
            tv_sel1.setTextColor(resources.getColor(R.color.colorPrimary))
            toast("所在院校不能为空")
            return
        }

        if (tv_year.text.toString() == "" ){
            tv_sel2.setTextColor(resources.getColor(R.color.colorPrimary))
            toast("报考年份不能为空")
            return
        }

        if (target_profession_ids == "" ){
            tv_sel3.setTextColor(resources.getColor(R.color.colorPrimary))
            toast("报考院校不能为空")
            return
        }
        val map = hashMapOf<String, String>()
        map.put("school_year", tv_year.text.toString())
        map.put("current_school_code",current_school_code)
        map.put("current_profession_id", current_profession_id)
        map.put("target_profession_ids", target_profession_ids)
        NetTools.net("post",map, Urls.select_target_school_profession, this) { response ->
            startActivity(Intent(this@WSZL2Activity, MainActivity::class.java))
            WSZL1Activity.instance!!.finish()
            finish()
        }
    }
}
