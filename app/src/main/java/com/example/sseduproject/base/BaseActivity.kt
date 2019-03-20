package com.hhkj.highschool.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.sseduproject.R
import com.example.sseduproject.app.MyApp
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.toast

open class BaseActivity : BaseActivity2() {

    var progressDialog: ProgressDialog? = null

    var pageIndex = 0
    var pageSize = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApp.getActivies().add(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        MyApp.getActivies().remove(this)
    }

    /**
     * 修改标题

     * @param title 标题名
     */
    protected fun setTextTitle(title: String) {
        (findViewById<TextView>(R.id.tv_toolsbar_title)).text = title
    }

    /**
     * 修改左按钮

     * @param flag 是否显示
     */
    protected fun setLeftBtn(flag: Boolean) {
        if (flag) {
            (findViewById<TextView>(R.id.tv_toolsbar_left) as TextView).visibility = View.VISIBLE
            (findViewById<TextView>(R.id.tv_toolsbar_left) as TextView).setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    hideSoftInput()
                    finish()
                }
            })
        } else {
            (findViewById<TextView>(R.id.tv_toolsbar_left) as TextView).visibility = View.GONE
        }
    }

    /**
     * 修改右按钮(文字按钮)

     * @param flag     是否显示
     * *
     * @param string   按钮名
     * *
     * @param listener 点击事件
     */
    protected fun setRightBtn(flag: Boolean, string: String, listener: View.OnClickListener) {
        if (flag) {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).text = string
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.VISIBLE
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).setOnClickListener(listener)
        } else {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.GONE
        }
    }


    /**
     * 设置右上角图片按钮

     * @param isShow
     * *
     * @param resId
     * *
     * @param onClickListener
     */
    protected fun setRightButton(isShow: Boolean, resId: Int, onClickListener: View.OnClickListener?) {
        val iv_right = findViewById<ImageView>(R.id.iv_right) as ImageView
        if (iv_right != null) {
            if (isShow) {
                iv_right!!.visibility = View.VISIBLE
                iv_right!!.setImageResource(resId)
                iv_right!!.setOnClickListener(onClickListener)
            } else {
                iv_right!!.visibility = View.GONE
            }
        }
    }

    /**
     * 修改右按钮(纯图按钮)

     * @param flag     是否显示
     * *
     * @param resId    按钮图
     * *
     * @param listener 点击事件
     */
    protected fun setRightBtn(flag: Boolean, resId: Int, listener: View.OnClickListener) {
        if (flag) {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).text = ""
            val layoutParams = (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).layoutParams as LinearLayout.LayoutParams
            layoutParams.height = layoutParams.height - dip2px(28f)
            layoutParams.width = layoutParams.height
            layoutParams.gravity = Gravity.CENTER_VERTICAL
            layoutParams.setMargins(0, 0, dip2px(12f), 0)
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).layoutParams = layoutParams
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).setBackgroundResource(resId)
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.VISIBLE
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).setOnClickListener(listener)
        } else {
            (findViewById<TextView>(R.id.tv_toolsbar_right) as TextView).visibility = View.GONE
        }
    }

    open fun showProgressDialog() {
        showProgressDialog("加载中...")
    }

    open fun showProgressDialog(msg: String) {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog(this)
            }
            if (progressDialog != null && !progressDialog!!.isShowing) {
                progressDialog!!.setCanceledOnTouchOutside(false)
                progressDialog!!.setMessage(msg)
                progressDialog!!.show()
            }
        } catch (e: Exception) {

        }
    }

    open fun dismissProgressDialog() {
        try {
            if (progressDialog != null && progressDialog!!.isShowing) {
                progressDialog!!.dismiss()
            }
        } catch (e: Exception) {

        }
    }

    /**
     * 收起键盘
     */
    protected fun hideSoftInput() {
        try {
            val mInputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mInputMethodManager.hideSoftInputFromWindow(this.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

//    fun setLeftBtn2(flag: Boolean) {
//        mFlag = false
//        if (flag) {
//            (findViewById<TextView>(R.id.tv_toolsbar_left) as TextView).visibility = View.VISIBLE
//            (findViewById<TextView>(R.id.tv_toolsbar_left) as TextView).setOnClickListener(object : View.OnClickListener {
//                override fun onClick(p0: View?) {
//                    showBack()
//                }
//            })
//        } else {
//            (findViewById<TextView>(R.id.tv_toolsbar_left) as TextView).visibility = View.GONE
//        }
//    }
//
//    private var mFlag = true
//    override fun onBackPressed() {
//        if(mFlag){
//            super.onBackPressed()
//        }else{
//            showBack()
//        }
//    }

//    fun showBack(){
//        val builder = CustomDialog.Builder(this)
//        builder.setCancel(true)
//        builder.setMessage("请确定您的数据已保存?")
//        builder.setTitle("提示")
//        builder.setPositiveButton("我点错了",
//                { dialog, which -> dialog.dismiss() }, R.drawable.background_btn)
//        builder.setNegativeButton("放弃保存",
//                { dialog, which ->
//                    dialog.dismiss()
//                    hideSoftInput()
//                    finish()
//                }, R.drawable.background_btn)
//        builder.create().show()
//
//    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 返回当前程序版本名
     */
    fun getAppVersionName(): String {
        var versionName: String? = ""
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionName = pi.versionName
            if (versionName == null || versionName.length <= 0) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return versionName!!
    }

    /**
     * 返回当前程序版本
     */
    fun getAppVersionCode(): String {
        var versionId = 0
        try {
            // ---get the package info---
            val pm = packageManager
            val pi = pm.getPackageInfo(packageName, 0)
            versionId = pi.versionCode
            if (versionId <= 0) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return "$versionId"
    }


    /**
     * size 当前页面总共获取数据条数
     * msg 无数据提示语
     * resId 无数据提示图
     */
//    fun setListToastView(size: Int, msg: String, resId: Int) {
//        setListToastView(size, msg, resId, true)
//    }

    /**
     * size 当前页面总共获取数据条数
     * msg 无数据提示语
     * resId 无数据提示图
     */
//    fun setListToastView(size: Int, msg: String, resId: Int, isShow: Boolean) {
//        if (isShow) {
//            if (findViewById<View>(R.id.view_no_data) != null) {
//                if (size == 0) {
//                    // toast("暂无数据")
//                    findViewById<View>(R.id.view_no_data).visibility = View.VISIBLE
//                    findViewById<View>(R.id.view_no_data).setOnClickListener { }
//                    findViewById<View>(R.id.view_no_data).findViewById<TextView>(R.id.textView).text = msg
//                    findViewById<View>(R.id.view_no_data).findViewById<ImageView>(R.id.imageView).imageResource = resId
//                } else {
//                    if (pageIndex != 0) {
//                        toast("无更多数据")
//                    }
//                    findViewById<View>(R.id.view_no_data).visibility = View.GONE
//                    findViewById<View>(R.id.view_no_data).findViewById<TextView>(R.id.textView).text = ""
//                    findViewById<View>(R.id.view_no_data).findViewById<ImageView>(R.id.imageView).imageResource = android.R.color.transparent
//                }
//            }
//        } else {
//            if (findViewById<View>(R.id.view_no_data) != null) {
//                findViewById<View>(R.id.view_no_data).visibility = View.GONE
//                findViewById<View>(R.id.view_no_data).findViewById<TextView>(R.id.textView).text = ""
//                findViewById<View>(R.id.view_no_data).findViewById<ImageView>(R.id.imageView).imageResource = android.R.color.transparent
//            }
//        }
//    }

}
