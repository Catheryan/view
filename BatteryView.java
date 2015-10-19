package com.yzh.android.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class BatteryView extends View {

	/**
	 * 画笔信息
	 */
	private Paint mBatteryPaint;
	private Paint mPowerPaint;
	private float mBatteryStroke = 2f;
	/**
	 * 屏幕高宽
	 */
	private int measureWidth;
	private int measureHeigth;
	/**
	 * 
	 * 电池参数
	 */
	private float mBatteryHeight = 20f; // 电池的高度
	private float mBatteryWidth = 40f; // 电池的宽度
	private float mCapHeight = 15f;
	private float mCapWidth = 5f;
	/**
	 * 
	 * 电池电量
	 */
	private float mPowerPadding = 1;
	private float mPowerHeight = mBatteryHeight - mBatteryStroke
			- mPowerPadding * 2; // 电池身体的高度
	private float mPowerWidth = mBatteryWidth - mBatteryStroke - mPowerPadding
			* 2;// 电池身体的总宽度
	private float mPower = 0f;
	/**
	 * 
	 * 矩形
	 */
	private RectF mBatteryRect;
	private RectF mCapRect;
	private RectF mPowerRect;

	public BatteryView(Context context) {
		super(context);
		initView();
	}

	public BatteryView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initView();
	}

	public void initView() {
		/**
		 * 设置电池画笔
		 */
		mBatteryPaint = new Paint();
		mBatteryPaint.setColor(Color.GRAY);
		mBatteryPaint.setAntiAlias(true);
		mBatteryPaint.setStyle(Style.STROKE);
		mBatteryPaint.setStrokeWidth(mBatteryStroke);
		/**
		 * 设置电量画笔
		 */
		mPowerPaint = new Paint();
		mPowerPaint.setColor(Color.GREEN);
		mPowerPaint.setAntiAlias(true);
		mPowerPaint.setStyle(Style.FILL);
		mPowerPaint.setStrokeWidth(mBatteryStroke);
		/**
		 * 设置电池矩形
		 */
		mBatteryRect = new RectF(mCapWidth, 0, mBatteryWidth, mBatteryHeight);
		/**
		 * 设置电池盖矩形
		 */
		mCapRect = new RectF(0, (mBatteryHeight - mCapHeight) / 2, mCapWidth,
				(mBatteryHeight - mCapHeight) / 2 + mCapHeight);
		/**
		 * 设置电量矩形
		 */
		mPowerRect = new RectF(mCapWidth + mBatteryStroke / 2 + mPowerPadding
				+ mPowerWidth * ((100f - mPower) / 100f), // 需要调整左边的位置
				mPowerPadding + mBatteryStroke / 2, // 需要考虑到 画笔的宽度
				mBatteryWidth - mPowerPadding * 2, mBatteryStroke / 2
						+ mPowerPadding + mPowerHeight);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		//canvas.translate(measureWidth / 2, measureHeigth / 2);
		canvas.translate(0,measureHeigth / 4+11);
		canvas.drawRoundRect(mBatteryRect, 2f, 2f, mBatteryPaint); // 画电池轮廓需要考虑
																	// 画笔的宽度
		canvas.drawRoundRect(mCapRect, 2f, 2f, mBatteryPaint);// 画电池盖
		canvas.drawRect(mPowerRect, mPowerPaint);// 画电量
		canvas.restore();
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		measureWidth = MeasureSpec.getSize(widthMeasureSpec)/11;
		measureHeigth = MeasureSpec.getSize(heightMeasureSpec);
		setMeasuredDimension(measureWidth, measureHeigth);
	}

	/**
	 * 
	 * 
	 * @category 设置电池电量
	 * @param power
	 */
	public void setPower(float power) {
		mPower = power;
		if (mPower < 0) {
			mPower = 0;
		}
		mPowerRect = new RectF(mCapWidth + mBatteryStroke / 2 + mPowerPadding
				+ mPowerWidth * ((100f - mPower) / 100f), // 需要调整左边的位置
				mPowerPadding + mBatteryStroke / 2, // 需要考虑到 画笔的宽度
				mBatteryWidth - mPowerPadding * 2, mBatteryStroke / 2
						+ mPowerPadding + mPowerHeight);
		invalidate();
	}

}
