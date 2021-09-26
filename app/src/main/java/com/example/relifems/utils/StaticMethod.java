package com.example.relifems.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.example.relifems.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StaticMethod {

    private static Dialog dialog;

    public static void showToastLong(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public static void showToastShort(Context context, String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void loadingView(Activity activity, boolean status) {
        if (status) {
            if (dialog == null) {
                if (!activity.isFinishing()) {
                    dialog = new ProgressDialog(activity, R.style.MyProgressDialogTheme);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setCancelable(false);
                    if (!dialog.isShowing())
                        dialog.show();
                }
            }
        } else {
            if (activity != null && dialog != null && dialog.isShowing()) {
                dialog.dismiss();
                dialog = null;
            }
        }
    }

    public static String releaseDate(String dates) {
        try {
            String[] data = dates.split("T");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = sdf.parse(data[0]);
            sdf = new SimpleDateFormat("MMM dd, yyyy");
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

}