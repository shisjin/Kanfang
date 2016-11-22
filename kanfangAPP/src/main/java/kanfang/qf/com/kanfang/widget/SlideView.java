package kanfang.qf.com.kanfang.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import kanfang.qf.com.kanfang.utils.L;

/**
 * Created by Administrator on 2016/11/22.
 */
public class SlideView extends View {

    //定义点击回调接口
    public interface SlideClick {
        public void slideOnClick(int postion, String str);

        public void slideUp();
    }

    SlideClick listener;

    /**
     * 设置监听
     *
     * @param click
     */
    public void setOnSlideClick(SlideClick click) {
        this.listener = click;
    }


    public static final String[] lettes = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
    };

    //画笔
    Paint mPaint;

    /**
     * 文字宽
     */
    float textW;
    /**
     * 文字高
     */
    float textH;

    /**
     * 当前被点击的位置
     */
    int currintPostion = -1;

    public SlideView(Context context) {
        super(context);
        init();
    }

    public SlideView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //画笔
        mPaint = new Paint();
        //设置文字大小
        mPaint.setTextSize(40);
        mPaint.setColor(Color.parseColor("#FF01FA3F"));
        mPaint.setAntiAlias(true);
        //计算文本的高度
        textH = mPaint.descent() - mPaint.ascent();
        //测量第一个字母的宽度
        textW = mPaint.measureText(lettes[0]);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasureWH(widthMeasureSpec, 1), getMeasureWH(heightMeasureSpec, 2));
        //计算文字大小
        textH = (getMeasuredHeight() - getPaddingBottom() - getPaddingTop()) / lettes.length;
        mPaint.setTextSize(textH - 5);
    }


    private int getMeasureWH(int wh, int type) {
        int mode = MeasureSpec.getMode(wh);
        int size = MeasureSpec.getSize(wh);
        //根据模式，计算大小
        switch (mode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.UNSPECIFIED:
                return size;
            case MeasureSpec.AT_MOST: {
                //wrap_content
                //如果是宽度，则是文本的测量宽度
                if (type == 1) {
                    //测量宽度
                    return (int) textW + getPaddingLeft() + getPaddingRight();
                } else {
                    //如果 是高度 ，则是所有文本的高度之和
                    return size;
                }
            }
        }
        return size;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        L.d("onDraw~~~~~~~~~~~~~~~~");
        super.onDraw(canvas);
        int len = lettes.length;
        for (int i = 0; i < len; i++) {
            //从字母数组中，取出每个字母，并绘制出来
            float dx = getMeasuredWidth() - mPaint.measureText(lettes[i]);
            float dy = i * textH - mPaint.ascent() + getPaddingTop();
            //如果是被点中的，变颜色
            if (i == currintPostion) {
                //加粗
                mPaint.setFakeBoldText(true);
                mPaint.setColor(Color.parseColor("#FF0011F9"));
            } else {
                mPaint.setFakeBoldText(false);
                mPaint.setColor(Color.parseColor("#FF01FA3F"));
            }
            canvas.drawText(lettes[i], dx / 2, dy, mPaint);


        }
    }

    /*解决点击事件*/
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                eventLable(event.getY());
            }
            break;
            case MotionEvent.ACTION_MOVE: {
                eventLable(event.getY());
            }
            break;
            case MotionEvent.ACTION_UP: {
                //设置点击位置为-1
                currintPostion = -1;
                //重绘界面
                invalidate();
                //调用slideUP方法
                if (listener != null) {
                    listener.slideUp();
                }

            }
            break;
        }

        return true;
    }

    /**
     * 处理点击事件
     *
     * @param y
     */
    private void eventLable(float y) {
        int index = (int) ((y - getPaddingTop()) / textH);
        if (index < 0) {
            index = 0;
        }
        if (index > lettes.length - 1) {
            index = lettes.length - 1;
        }
        //设置点击位置
        if (currintPostion != index) {
            L.d("当前点击了：" + index + lettes[index]);
            currintPostion = index;
            //重绘界面
            invalidate();
            //调用接口实例方法
            if (listener != null) {
                listener.slideOnClick(index, lettes[index]);
            }
        }
    }


}
