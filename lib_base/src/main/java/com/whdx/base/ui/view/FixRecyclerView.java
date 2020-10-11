package com.whdx.base.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.whdx.base.R;
import com.whdx.base.util.CanvasUtils;
import com.wjx.android.weather.common.util.DisplayUtil;

/**
 * 包装RecyclerView, 使其为空时绘制 "暂无数据" 字样
 */
public class FixRecyclerView extends RecyclerView {

	private Paint mPaint;
	private float mTextWidth;

	public static final int GRAVITY_TOP = 0;
	public static final int GRAVITY_CENTER = 1;

	private int gravity;
	private int mTopMargin;
	private CharSequence mEmptyText;
	private Drawable mEmptyDrawable;

	public FixRecyclerView(Context context) {
		this(context, null);
	}

	public FixRecyclerView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public FixRecyclerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mPaint = new Paint();
		mPaint.setAntiAlias(true);

		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.JRecyclerView);
		int color = ta.getColor(R.styleable.JRecyclerView_empty_text_color, Color.GRAY);
		float textsize = ta.getDimension(R.styleable.JRecyclerView_empty_text_size, DisplayUtil.sp2px(context,18));
		int style = ta.getInt(R.styleable.JRecyclerView_empty_text_style, Typeface.NORMAL);
		gravity = ta.getInt(R.styleable.JRecyclerView_empty_text_gravity, GRAVITY_CENTER);
		mTopMargin = (int) ta.getDimension(R.styleable.JRecyclerView_empty_text_top_margin, DisplayUtil.dip2px(context,10));
		mEmptyText = ta.getString(R.styleable.JRecyclerView_empty_text);
		int emptyResId = ta.getResourceId(R.styleable.JRecyclerView_empty_image, R.mipmap.icon_empty);
		ta.recycle();
		mEmptyDrawable = context.getResources().getDrawable(emptyResId);
		mPaint.setTypeface(Typeface.defaultFromStyle(style));
		mPaint.setColor(color);
		mPaint.setTextSize(textsize);
		setTextImpl(mEmptyText);
	}

	public void setText(CharSequence text){
		setTextImpl(text);
		Adapter adapter = getAdapter();
		if(adapter == null || adapter.getItemCount() == 0) {
			invalidate();
		}
	}

	private void setTextImpl(CharSequence text) {
		this.mEmptyText = text;
		if(text != null) {
			mTextWidth = mPaint.measureText(text, 0, text.length());
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if(isEmpty()){
			if(TextUtils.isEmpty(mEmptyText)){
				drawEmptyIcon(canvas);
			}else {
				drawEmptyText(canvas);
			}
		}
	}

	public final boolean isEmpty(){
		Adapter adapter = getAdapter();
		return adapter != null && adapter.getItemCount() == 0;
	}

	private void drawEmptyText(Canvas canvas) {
		switch (gravity) {
			case GRAVITY_TOP:
				CanvasUtils.drawTopText(mEmptyText, mTextWidth, canvas, mPaint, this, mTopMargin);
				break;
			case GRAVITY_CENTER:
				CanvasUtils.drawCenterText(mEmptyText, mTextWidth, canvas, mPaint, this);
				break;
		}
	}

	@Override
//	protected void onMeasure(int widthSpec, int heightSpec) {
//	/*	int height = MeasureSpec.getSize(heightSpec);
//		if(isEmpty()){
//			int minHeight = mEmptyDrawable.getIntrinsicHeight() + DensityUtils.DP20;
//			if(height < minHeight){
//				height = minHeight;
//				heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//			}
//		}*/
//		super.onMeasure(widthSpec, heightSpec);
//	}
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		ViewGroup mViewGroup = (ViewGroup) getParent();
		if(null != mViewGroup){
			int mParentHeight = mViewGroup.getHeight();
			setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec), mParentHeight);
		}
	}
	private void drawEmptyIcon(Canvas canvas) {
		int left = (getWidth() - mEmptyDrawable.getIntrinsicWidth()) / 2;
		int right = left + mEmptyDrawable.getIntrinsicWidth();
		int top;
		if(gravity == GRAVITY_TOP){
			top = mTopMargin + getPaddingTop();
		}else {
			top = (getHeight() - mEmptyDrawable.getIntrinsicHeight()) / 2;
		}
		int bottom = top + mEmptyDrawable.getIntrinsicHeight();
		mEmptyDrawable.setBounds(left, top, right, bottom);
		mEmptyDrawable.draw(canvas);
	}
}
