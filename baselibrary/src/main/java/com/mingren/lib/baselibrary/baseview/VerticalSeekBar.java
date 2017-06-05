package com.mingren.lib.baselibrary.baseview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/**
 * 垂直的SeekBar
 * 为了响应滑块位置的改变，可以绑定OnSeekBarChangeListener来进行相应的监听处理。
 *
 */
public class VerticalSeekBar extends SeekBar {

	private int mPageCount = 0;
	Context context;
	public VerticalSeekBar(Context context) {
		super(context);
		this.context = context;
	}

	public VerticalSeekBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}


	public VerticalSeekBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	@Override
	public synchronized void setProgress(int progress) {
		super.setProgress(progress);
		onSizeChanged(getWidth(),getHeight(),0,0);
	}


	//Thumb的变化情况
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(h, w, oldh, oldw);
	}

	@Override
	public void setThumb(Drawable thumb) {
		super.setThumb(thumb);

	}

	//测量宽度也需要旋转
	@Override
	protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(heightMeasureSpec, widthMeasureSpec);
	//	setMeasuredDimension(dip2px(context,getMeasuredHeight()),dip2px(context,getMeasuredWidth()));
		setMeasuredDimension(getMeasuredHeight(),getMeasuredWidth());
	}


	//画布旋转90
	protected void onDraw(Canvas c) {
		c.rotate(-90);
		c.translate(-getHeight(),0);

		super.onDraw(c);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!isEnabled()) {
			return false;
		}

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
			case MotionEvent.ACTION_UP:
				int i=0;
				//设置y值为进度条的值
				i=getMax() - (int) (getMax() * event.getY() / getHeight());
				setProgress(i);
				onSizeChanged(getWidth(), getHeight(), 0, 0);
				break;

			case MotionEvent.ACTION_CANCEL:
				break;
		}
		return true;
	}

}