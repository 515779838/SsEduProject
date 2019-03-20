package com.example.sseduproject.base;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.sseduproject.R;

public class BaseFragment extends Fragment {

    protected PopupWindow popupWindow;
    protected View cView;// popwindow内容视图
    protected View mView;
    protected View view_line;
    protected GridView gridView;// popwindow内容视图中的主视图


    public int pageIndex = 0;
    public int pageSize = 20;

//    protected void initThisView() {
//        // 初始化popwindow子内容的相关控件
//        cView = getActivity().getLayoutInflater().inflate(R.layout.layout_gridview, null);
//        gridView = (GridView) cView.findViewById(R.id.gridView);
//        view_line = (View) cView.findViewById(R.id.view_line);
//        view_line.setVisibility(View.GONE);
//    }

    /**
     * 修改标题
     *
     * @param title 标题名
     */
    protected void setTextTitle(boolean flag, String title) {
        if (flag) {
            ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setVisibility(View.VISIBLE);
            ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setText(title);
        } else {
            ((TextView) mView.findViewById(R.id.tv_toolsbar_title)).setVisibility(View.GONE);
        }
    }


    /**
     * 创建PopupWindow
     *
     * @param view  点击的view(影响显示的位置)
     * @param bView 变黑的view
     */
//    protected void showPopuptWindow(final View view, final View bView) {
//        showPopuptWindow(view, bView, null);
//    }

//    /**
//     * 创建PopupWindow
//     *
//     * @param view      点击的view(影响显示的位置)
//     * @param bView     变黑的view
//     * @param tv_select 显示箭头的view
//     */
//    protected void showPopuptWindow(final View view, final View bView, final TextView tv_select) {
//        LinearLayout ll_popbg = (LinearLayout) cView.findViewById(R.id.ll_popbg);
//        ll_popbg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                popupWindow.dismiss();
//            }
//        });
//        // 实例化popwindow
//        popupWindow = new PopupWindow(cView, ViewGroup.LayoutParams.MATCH_PARENT, dip2px(getActivity(), 350), true);
//        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
//        popupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        //点击空白处时，隐藏掉pop窗口
//        popupWindow.setFocusable(true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return false;
//                // 这里如果返回true的话，touch事件将被拦截
//                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
//            }
//        });
//        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
//        // 我觉得这里是API的一个bug
//        popupWindow.setBackgroundDrawable(ContextCompat.getDrawable(getActivity(), android.R.color.transparent));
//        //添加pop窗口关闭事件
//        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // bView.setAlpha(0f);
//                if (tv_select != null) {
//                    Drawable drawable = ContextCompat.getDrawable(getActivity(), R.mipmap.ic_arrow_down);
//                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//                    tv_select.setCompoundDrawables(null, null, drawable, null);
//                }
//                if (bView != null) {
//                    bView.setVisibility(View.GONE);
//                }
//            }
//        });
//        // 设置好参数之后再show
//        popupWindow.showAsDropDown(view);
//        // bView.setAlpha(0.7f);
//        if (bView != null) {
//            bView.setVisibility(View.VISIBLE);
//        }
//        if (tv_select != null) {
//            Drawable drawable = ContextCompat.getDrawable(getActivity(), R.mipmap.ic_arrow_up);
//            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//            tv_select.setCompoundDrawables(null, null, drawable, null);
//        }
//    }

    /**
     * 收起键盘
     */
    private void hideSoftInput() {
        try {
            InputMethodManager mInputMethodManager = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
            mInputMethodManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
//            if (BaseCallback.isLog) {
//                Log.e("cyf", "e : " + e.toString());
//            }
        }
    }

//    /**
//     * 弹出dialog
//     */
//    public void showProgressDialog() {
//        hideSoftInput();
//        if (BaseActivity.this != null && progressDialog == null) {
//            progressDialog = new Dialog(BaseActivity.this, R.style.MyDialog);
//            progressDialog.setContentView(R.layout.dialog_loading);
//            progressDialog.setCancelable(false);
//        }
//        if (BaseActivity.this != null && progressDialog != null && !progressDialog.isShowing()) {
//            progressDialog.show();
//        }
//    }
//
//    /**
//     * 取消dialog
//     */
//    public void disnissProgressDialog() {
//        if (BaseActivity.this != null && progressDialog != null && progressDialog.isShowing()) {
//            progressDialog.dismiss();
//        }
//    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue （DisplayMetrics类中属性density）
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

//    @Override
//    public Resources getActivity()getResources() {
//        Resources res = super.getResources();
//        Configuration config = new Configuration();
//        config.setToDefaults();
//        res.updateConfiguration(config, res.getDisplayMetrics());
//        return res;
//    }

    /**
     * 输入框禁用空格和回车
     */
    protected InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (source.equals(" ") || source.toString().contentEquals("\n")) return "";
            else return null;
        }
    };

    /**
     * 输入框禁用回车
     */
    protected InputFilter filter2 = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            if (source.toString().contentEquals("\n")) return "";
            else return null;
        }
    };

}
