package com.hhkj.highschool.base

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import com.example.sseduproject.R


/**
 * Created by caoyingfu on 2017/8/15.
 */

open class BaseActivity2 : BseActivity3() {

//    protected var popupWindow: PopupWindow? = null
//    protected var cView: View? = null// popwindow内容视图
//    protected var view_line: View? = null
//    protected var gridView: GridView? = null// popwindow内容视图中的主视图
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        initThisView()
//    }
//
//    private fun initThisView() {
//        // 初始化popwindow子内容的相关控件
//        cView = layoutInflater.inflate(R.layout.layout_gridview, null)
//        gridView = cView!!.findViewById<View>(R.id.gridView) as GridView
//        view_line = cView!!.findViewById<View>(R.id.view_line)
//        view_line!!.visibility = View.GONE
//    }
//
//    /**
//     * 创建PopupWindow
//
//     * @param view      点击的view(影响显示的位置)
//     * *
//     * @param bView     变黑的view
//     * *
//     * @param tv_select 显示箭头的view
//     */
//    @JvmOverloads protected fun showPopuptWindow(view: View, bView: View?, tv_select: TextView? = null) {
//        val ll_popbg = cView!!.findViewById<View>(R.id.ll_popbg) as LinearLayout
//        ll_popbg.setOnClickListener { popupWindow!!.dismiss() }
//        // 实例化popwindow
//        if (popupWindow == null) {
//            popupWindow = PopupWindow(cView, ViewGroup.LayoutParams.MATCH_PARENT, ScreenTools.dip2px(this, 350f), true)
//        }
//        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
//        popupWindow!!.inputMethodMode = PopupWindow.INPUT_METHOD_NEEDED
//        popupWindow!!.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
//        //点击空白处时，隐藏掉pop窗口
//        popupWindow!!.isFocusable = true
//        popupWindow!!.isOutsideTouchable = true
//
//        // 这里如果返回true的话，touch事件将被拦截
//        // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//        popupWindow!!.setTouchInterceptor { view, motionEvent -> (false) }
//
//
//        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
//        // 我觉得这里是API的一个bug
//        popupWindow!!.setBackgroundDrawable(ContextCompat.getDrawable(this, android.R.color.transparent))
//        //添加pop窗口关闭事件
//
//        popupWindow!!.setOnDismissListener {
//            if (tv_select != null) {
//                val drawable = ContextCompat.getDrawable(this@BaseActivity2, R.mipmap.ic_arrow_down)
//                drawable!!.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
//                tv_select.setCompoundDrawables(null, null, drawable, null)
//            }
//            if (bView != null) {
//                bView.visibility = View.GONE
//            }
//        }
//
//        // 设置好参数之后再show
//        popupWindow!!.showAsDropDown(view)
//        // bView.setAlpha(0.7f);
//        if (bView != null) {
//            bView.visibility = View.VISIBLE
//        }
//        if (tv_select != null) {
//            val drawable = ContextCompat.getDrawable(this@BaseActivity2, R.mipmap.ic_arrow_up)
//            drawable!!.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
//            tv_select.setCompoundDrawables(null, null, drawable, null)
//        }
//    }

}
