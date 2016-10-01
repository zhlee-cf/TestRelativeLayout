package com.exiu.testrelativelayout;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.widget.Toast;

import java.util.List;

public class MyUtils {

	/**
	 * 方法：主线程和子线程弹吐司
	 * 
	 * @param act
	 * @param msg
	 */
	public static void showToast(final Activity act, final String msg) {

		if ("main".equals(Thread.currentThread().getName())) {
			// 主线程弹吐司
			Toast.makeText(act, msg, Toast.LENGTH_SHORT).show();
		} else {
			// 子线程弹吐司
			act.runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(act, msg, Toast.LENGTH_SHORT).show();
				}
			});
		}
	}

	/**
	 * 判断指定名称的服务是否运行
	 * 
	 * @param act
	 * @param serviceName
	 * @return
	 */
	public static boolean isServiceRunning(Activity act, String serviceName) {

		ActivityManager am = (ActivityManager) act.getSystemService(Context.ACTIVITY_SERVICE);

		List<RunningServiceInfo> runningServices = am.getRunningServices(2000); // 参数是服务数量的最大值，一般手机中，运行，20
		for (RunningServiceInfo runningServiceInfo : runningServices) {
			String runningServiceName = runningServiceInfo.service.getClassName();
			if (runningServiceName.equals(serviceName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 得到设备屏幕的宽度
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 得到设备屏幕的高度
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 得到设备的密度
	 */
	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}