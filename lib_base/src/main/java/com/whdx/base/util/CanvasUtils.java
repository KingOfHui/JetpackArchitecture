package com.whdx.base.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * <br>createBy guoshiwen
 * <br>createTime: 2020/4/23 15:04
 * <br>desc: TODO
 */
public class CanvasUtils {

	/**
	 * 在控件中间绘制文字
	 * @param owner 控件本身
	 */
	public static void drawCenterText(CharSequence text, float textWidth, Canvas canvas, Paint paint, View owner){
		int width = owner.getWidth() - owner.getPaddingLeft() - owner.getPaddingRight();
		int height = owner.getHeight() - owner.getPaddingTop() - owner.getPaddingBottom();
		Paint.FontMetrics fm = paint.getFontMetrics();
		float ascent = Math.abs(paint.ascent());
		float descent = Math.abs(paint.descent());
		float textHeight = ascent + descent;

		canvas.drawText(text, 0, text.length(),  owner.getPaddingLeft() + (width - textWidth) / 2, owner.getPaddingTop() + -fm.ascent + (height - textHeight) / 2, paint);
	}

	public static void drawTopText(CharSequence text, float textWidth, Canvas canvas, Paint paint, View owner, int topMargin){
		int width = owner.getWidth() - owner.getPaddingLeft() - owner.getPaddingRight();
		Paint.FontMetrics fm = paint.getFontMetrics();

		canvas.drawText(text, 0, text.length(),  owner.getPaddingLeft() + (width - textWidth) / 2,
				owner.getPaddingTop() + -fm.ascent + topMargin, paint);
	}
}
