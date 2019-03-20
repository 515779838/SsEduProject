package com.hhkj.highschool.base

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.cyf.nfcproject.tools.SPTools
//import com.google.gson.Gson
//import com.hhkj.highschool.bean.ClassBean
//import com.hhkj.highschool.bean.GradeBean
//import com.hhkj.highschool.bean.SelectBean
//import com.hhkj.highschool.bean.TermBean
//import com.hhkj.highschool.constant.Constant
//import com.hhkj.highschool.tools.NetTools
//import com.hhkj.highschool.url.Urls

/**
 * Created by caoyingfu on 2017/8/27.
 */

open class BseActivity3 : AppCompatActivity() {

//    /**
//     * 获取年级
//     * 根据工具包获取年级列表
//     */
//    fun net_outcomes_get_Grade(toolId: String, callback: NetTools.MyCallBack) {
//        val map = hashMapOf<String, String>()
//        map.put("toolId", toolId)
//        map.put("schId", SPTools[this, Constant.SCHOOLID, ""] as String)
//        NetTools.net(map, Urls().outcomes_get_Grade, this, callback)
//    }
//
//    /**
//     * 获取班级
//     */
//    fun net_outcomes_get_Class(gradeId: String, callback: NetTools.MyCallBack) {
//        val map = hashMapOf<String, String>()
//        map.put("gradeId", gradeId)
//        NetTools.net(map, Urls().outcomes_get_Class, this, callback)
//    }
//
//    /**
//     * 获取学期
//     */
//    fun net_comment_get_Term(schId: String, gradeId: String, callback: NetTools.MyCallBack) {
//        val map = hashMapOf<String, String>()
//        map.put("schId", schId)
//        map.put("gradeId", gradeId)
//        NetTools.net(map, Urls().comment_get_Term, this, callback)
//    }
//
//    /**
//     * 获取年班
//     * 教师获取年级和班级（公用）
//     */
//    fun net_comment_get_Class_Grade(toolId: String, callback: NetTools.MyCallBack) {
//        val map = hashMapOf<String, String>()
//        map.put("toolId", toolId)
//        map.put("schId", SPTools[this, Constant.SCHOOLID, ""] as String)
//        Log.e("cyf777", map.toString())
//        NetTools.net(map, Urls().comment_get_Class_Grade, this, callback)
//    }
//
//    /**
//     * 返回SelectBean数组
//     * type：1年级，2班级，3学期
//     */
//    fun getBean(json: String, type: Int): ArrayList<SelectBean>? {
//        val list: ArrayList<SelectBean>? = arrayListOf()
//        when (type) {
//            1 -> {
//                val bean = Gson().fromJson(json, GradeBean::class.java)
//                for (i in 0 until bean.gradeList.size) {
//                    val selectbean = SelectBean()
//                    selectbean.other1 = bean.defaultGradeId
//                    selectbean.other2 = bean.defaultName
//                    for (j in 0 until bean.gradeList.size) {
//                        if (bean.gradeList[j].gradeId == bean.defaultGradeId) {
//                            selectbean.other3 = bean.gradeList[j].gradeCode
//                            break
//                        }
//                    }
//                    selectbean.name = bean.gradeList!![i].gradeName
//                    selectbean.id = bean.gradeList!![i].gradeId
//                    selectbean.gradeCode = bean.gradeList!![i].gradeCode
//                    list!!.add(selectbean)
//                }
//            }
//            2 -> {
//                val bean = Gson().fromJson(json, ClassBean::class.java)
//                for (i in 0 until bean.classList.size) {
//                    val selectbean = SelectBean()
//                    selectbean.other1 = bean.defaultId
//                    selectbean.other2 = bean.defaultName
//                    for (j in 0 until bean.classList.size) {
//                        if (bean.classList[j].classId == bean.defaultId) {
//                            selectbean.other3 = bean.classList[j].classCode
//                            break
//                        }
//                    }
//                    selectbean.name = bean.classList!![i].className
//                    selectbean.id = bean.classList!![i].classId
//                    selectbean.classCode = bean.classList!![i].classCode
//                    list!!.add(selectbean)
//                }
//            }
//            3 -> {
//                val bean = Gson().fromJson(json, TermBean::class.java)
//                for (i in 0 until bean.lists.size) {
//                    val selectbean = SelectBean()
//                    selectbean.other1 = bean.defaultId
//                    selectbean.other2 = bean.defaultName
//                    for (j in 0 until bean.lists.size) {
//                        if (bean.lists[j].termId == bean.defaultId) {
//                            selectbean.other3 = bean.lists[j].termCode
//                            break
//                        }
//                    }
//                    selectbean.name = bean.lists!![i].termName
//                    selectbean.id = bean.lists!![i].termId
//                    selectbean.code = bean.lists!![i].termCode
//                    list!!.add(selectbean)
//                }
//            }
//        }
//        return list!!
//    }

}
