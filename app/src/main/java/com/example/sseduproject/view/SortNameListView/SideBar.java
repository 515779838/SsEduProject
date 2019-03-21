package com.example.sseduproject.view.SortNameListView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sseduproject.tools.ScreenTools;

/**
 * @author J
 *         一个自定义view 实现a-z的竖直绘制，和监听滑动事件
 */
public class SideBar extends View {
    private OnTouchingLetterChangedListener onTouchingLetterChangedListener;
    private String[] b = null;
    private String[] b2 = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};
    private int choose = -1;
    private Paint paint = new Paint();

    private TextView mTextDialog;
    private Context context;

    private int mHeight = 0;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    public void setXing(String[] strings) {
        setXing(strings, true);
    }

    /**
     * @param strings
     * @param flag    是否显示完整的字母表
     */
    public void setXing(String[] strings, boolean flag) {
        if (flag) {
            b = b2;
        } else {
            if (strings == null) {
                b = b2;
            } else {
                b = strings;
            }
        }
        if (mHeight == 0) {
            mHeight = getHeight();
        }
        this.getLayoutParams().height = mHeight / 27 * b.length;
        invalidate();
        SideBar.this.setVisibility(View.VISIBLE);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public SideBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public SideBar(Context context) {
        super(context);
        this.context = context;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (b == null) {
            b = b2;
            this.setVisibility(View.GONE);
        }
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / b.length;
        for (int i = 0; i < b.length; i++) {
            paint.setColor(Color.parseColor("#A1A1A2"));
            paint.setColor(Color.BLACK);
            paint.setAntiAlias(true);
            paint.setTextSize(28);
            if (i == choose) {
                paint.setColor(Color.parseColor("#B8B8B8"));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(b[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(b[i], xPos, yPos, paint);
            paint.reset();
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) getLayoutParams();
//        lp.setMargins(0, ScreenTools.dip2px(context, 40), 0, ScreenTools.dip2px(context, 40));//设置边距
        lp.setMargins(0, ScreenTools.dip2px(context, 10), 0, ScreenTools.dip2px(context, 20));//设置边距
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * b.length);

        switch (action) {
            case MotionEvent.ACTION_UP:
                setBackgroundDrawable(new ColorDrawable(0x00000000));
                choose = -1;//
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                // setBackgroundResource(R.drawable.sidebar_background);
                if (oldChoose != c) {
                    if (c >= 0 && c < b.length) {
                        if (listener != null) {
                            listener.onTouchingLetterChanged(b[c]);
                        }
                        if (mTextDialog != null) {
                            mTextDialog.setText(b[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }

                        choose = c;
                        invalidate();
                    }
                }

                break;
        }
        return true;
    }

    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        public void onTouchingLetterChanged(String s);
    }

}