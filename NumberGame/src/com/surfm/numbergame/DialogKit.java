package com.surfm.numbergame;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class DialogKit{

	class NegativeClickListener implements OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
	}
	
	class PositiveClickListener implements OnClickListener{
		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.dismiss();
		}
	}
	
	private static DialogKit instance;
	
	private DialogKit(){}
	
	public static DialogKit getInstance(){
		if(instance == null){
			instance = new DialogKit();
		}
		return instance;
	}
	


	/**
	 * Show confirmation dialog.
	 * @param context
	 * @param messageId
	 * 			body message resource id
	 * @param positiveId
	 * 			positive button text resource id
	 * @param negativeId
	 * 			negativie button text resource id
	 * @param positiveListener
	 * 			positive button callback listener
	 * @param negativeListener
	 * 			negative button callback listener
	 * @return dialog
	 * 			AlertDialog
	 */
	public static Dialog showConfirmDialog(Context context, int messageId,int positiveId,int negativeId,
			OnClickListener positiveListener, OnClickListener negativeListener) {
		ContextThemeWrapper ctw = new ContextThemeWrapper(context,
				R.style.AppTheme);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
		if (negativeListener == null) {
			negativeListener = DialogKit.getInstance().new NegativeClickListener();
		}
		if(positiveListener == null){
			positiveListener = DialogKit.getInstance().new PositiveClickListener();
		}
		builder.setMessage(messageId);
		builder.setPositiveButton(positiveId, positiveListener);
		builder.setNegativeButton(negativeId, negativeListener);
		
		
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
        dialog.show();

		TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
		messageView.setGravity(Gravity.LEFT);
		
		return dialog;
	}
	
	
	/**
	 * Show confirmation dialog.
	 * @param context
	 * @param messageId
	 * 			body message resource id
	 * @param positiveId
	 * 			positive button text resource id
	 * @param negativeId
	 * 			negativie button text resource id
	 * @param positiveListener
	 * 			positive button callback listener
	 * @param negativeListener
	 * 			negative button callback listener
	 * @return dialog
	 * 			AlertDialog
	 */
	public static Dialog showConfirmDialog(Context context, String msg,int positiveId,int negativeId,
			OnClickListener positiveListener, OnClickListener negativeListener) {
		ContextThemeWrapper ctw = new ContextThemeWrapper(context,
				R.style.AppTheme);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
		if (negativeListener == null) {
			negativeListener = DialogKit.getInstance().new NegativeClickListener();
		}
		if(positiveListener == null){
			positiveListener = DialogKit.getInstance().new PositiveClickListener();
		}
		builder.setMessage(msg);
		builder.setPositiveButton(positiveId, positiveListener);
		builder.setNegativeButton(negativeId, negativeListener);
		
		
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
        dialog.show();

		TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
		messageView.setGravity(Gravity.LEFT);
		
		return dialog;
	}
	
	/**
	 * Show confirmation dialog.
	 * @param context
	 * @param messageId
	 * 			body message resource id
	 * @param positiveListener
	 * 			positive button callback listener
	 * @param negativeListener
	 * 			negative button callback listener
	 * @return dialog
	 * 			AlertDialog
	 */
	public static Dialog showConfirmDialog(Context context, int messageId,
			OnClickListener positiveListener, OnClickListener negativeListener) {
       return showConfirmDialog(context, messageId,android.R.string.ok,android.R.string.cancel, positiveListener, negativeListener);
	}
	
	/**
	 * Show alert dialog. 
	 * @param context
	 * @param messageId
	 * @param positiveListener
	 */
	public static void showAlertDialog(Context context, int messageId,
			OnClickListener positiveListener) {
       AlertDialog dialog = (AlertDialog) showConfirmDialog(context, messageId,android.R.string.ok,android.R.string.cancel, positiveListener, null);
       dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setVisibility(View.GONE);
	}
	
	/**
	 * Show alert dialog. 
	 * @param context
	 * @param messageId
	 * @param positiveListener
	 */
	public static void showAlertDialog(Context context, String msg,
			OnClickListener positiveListener) {
       AlertDialog dialog = (AlertDialog) showConfirmDialog(context, msg,android.R.string.ok,android.R.string.cancel, positiveListener, null);
       dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setVisibility(View.GONE);
	}



	public static void showSearchAppDialog(final Context context, int messageId, final String pkgName) {
		ContextThemeWrapper ctw = new ContextThemeWrapper(context,
				R.style.AppTheme);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
		
		builder.setMessage(messageId);
		builder.setPositiveButton(android.R.string.ok, new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String url = "https://play.google.com/store/apps/details?id=" + pkgName;
				Intent intent = new Intent(Intent.ACTION_VIEW);
				Uri uri = Uri.parse(url);
				intent.setData(uri);
				context.startActivity(intent);
			}
		});
		builder.setNegativeButton(android.R.string.cancel, DialogKit.getInstance().new NegativeClickListener());
		
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
        dialog.show();

		TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
		messageView.setGravity(Gravity.LEFT);
	}
	
	public static void showMenuDialog(Context context, String[] items, DialogInterface.OnClickListener listener, String title) {
		ContextThemeWrapper ctw = new ContextThemeWrapper(context,
				R.style.AppTheme);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
		if(title != null){
			builder.setTitle(title);
		}
		if(listener == null){
			listener = DialogKit.getInstance().new PositiveClickListener();
		}
		builder.setItems(items, listener);
		
		AlertDialog dialog = builder.create();
        dialog.show();
	}
	
	public static Dialog createCustomDialog(Context context, BaseAdapter adapter, DialogInterface.OnClickListener listener, String title){
		ContextThemeWrapper ctw = new ContextThemeWrapper(context,
				R.style.AppTheme);
		AlertDialog.Builder builder = new AlertDialog.Builder(ctw);
		
		if(title != null){
			builder.setTitle(title);
		}
		if(listener == null){
			listener = DialogKit.getInstance().new PositiveClickListener();
		}
        builder.setAdapter(adapter, listener);
       
        AlertDialog dialog = builder.create();
        return dialog;
	}

}
