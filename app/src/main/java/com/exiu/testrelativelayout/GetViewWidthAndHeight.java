package com.exiu.testrelativelayout;

import android.view.View;

/**
 * 获取控件宽高
 * 
 * @author House
 * 
 */
public class GetViewWidthAndHeight {
	/**
	 * 获取控件宽度
	 * 
	 * @return
	 */
	public static int getViewWidth(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int viewW = view.getMeasuredWidth();
		return viewW;
	}

	/**
	 * 获取控件高度
	 * 
	 * @param view
	 * @return
	 */
	public static int getViewHeight(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		int viewH = view.getMeasuredHeight();
		return viewH;
	}
}
