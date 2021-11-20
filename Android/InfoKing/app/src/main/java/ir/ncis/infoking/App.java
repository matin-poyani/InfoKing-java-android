package ir.ncis.infoking;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import CustomControls.CustomTextViewBold;
import Models.StructDetail;
import Models.StructQuest;
import Models.StructUser;

public class App extends Application {
    public static final Handler HANDLER = new Handler();
    public static final String URL_SERVICE = "http://192.168.1.103/infoking/?";
    public static ActivityEnhanced ACTIVITY;
    public static Context CONTEXT;
    public static LayoutInflater INFLATER;
    public static SharedPreferences PREFERENCES;
    public static SharedPreferences.Editor EDITOR;
    public static StructDetail CURRENT_DETAIL = new StructDetail();
    public static StructQuest CURRENT_QUEST = new StructQuest();
    public static StructUser CURRENT_USER = new StructUser();
    public static Typeface FONT_BOLD;
    public static Typeface FONT_NORMAL;

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int exitValue = ipProcess.waitFor();
            ipProcess.destroy();
            runtime.freeMemory();
            return exitValue == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void startActivity(Class targetActivity) {
        startActivity(targetActivity, false);
    }

    public static void startActivity(Class targetActivity, boolean finish) {
        Intent intent = new Intent(ACTIVITY, targetActivity);
        ACTIVITY.startActivity(intent);
        if (finish) {
            ACTIVITY.finish();
        }
    }

    public static String timeStamp() {
        return String.valueOf(System.currentTimeMillis()).substring(0, 10);
    }

    public static void toast(String message) {
        toast(message, Toast.LENGTH_SHORT);
    }

    public static void toast(String message, int duration) {
        View view = INFLATER.inflate(R.layout.toast, null);
        CustomTextViewBold txtMessage = view.findViewById(R.id.txtMessage);
        txtMessage.setText(message);
        Toast toast = new Toast(CONTEXT);
        toast.setDuration(duration);
        toast.setView(view);
        toast.show();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CONTEXT = getApplicationContext();
        FONT_BOLD = Typeface.createFromAsset(getAssets(), "font/IRANSansMobile_Bold.ttf");
        FONT_NORMAL = Typeface.createFromAsset(getAssets(), "font/IRANSansMobile_Normal.ttf");
        PREFERENCES = PreferenceManager.getDefaultSharedPreferences(CONTEXT);
        EDITOR = PREFERENCES.edit();
        EDITOR.apply();
    }
}
