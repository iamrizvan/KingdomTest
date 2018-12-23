package work.rizvan.com.kingdomtest.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


public class UserUtils
{

    private static String REGION_USER_PREFERENCES = "sehajob.com";
    private static String PREF_USER_NAME = "user_name";
    private static String PREF_USER_ID = "user_id";
    private static String PREF_USER_EMAIL = "user_email";
    private static String PREF_USER_LOGIN_STATUS = "login_status";
    private static String PREF_USER_VOICE_STATUS = "voice_status";


    public static void clearUserUtils(Context context)
    {
        SharedPreferences settings = context.getSharedPreferences(REGION_USER_PREFERENCES, Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }

    public static void saveUserName(Context context, String name)
    {
        if (context != null)
            CommonUtils.setPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_NAME, name);

    }

    public static String getUserName(Context context)
    {
        if (context != null)
        {
            String name = CommonUtils.getPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_NAME);
            return name;
        }
        return null;
    }


    public static void saveUserId(Context context, String id)
    {
        if (context != null)
            CommonUtils.setPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_ID, id);

    }

    public static String getUserId(Context context)
    {
        if (context != null)
        {
            String id = CommonUtils.getPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_ID);
            return id;
        }
        return null;
    }


    public static void saveVoiceStatus(Context context, String vs)
    {
        if (context != null)
            CommonUtils.setPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_VOICE_STATUS, vs);
    }

    public static String getVoiceStatus(Context context)
    {
        if (context != null)
        {
            String vs = CommonUtils.getPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_VOICE_STATUS);
            return vs;
        }
        return null;
    }


    public static void saveUserEmail(Context context, String email)
    {
        if (context != null)
            CommonUtils.setPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_EMAIL, email);

    }

    public static String getUserEmail(Context context)
    {
        if (context != null)
        {
            String email = CommonUtils.getPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_EMAIL);
            return email;
        }
        return null;
    }

    public static void saveLoginStatus(Context context, String status)
    {
        if (context != null)
            CommonUtils.setPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_LOGIN_STATUS, status);

    }

    public static String getLoginStatus(Context context)
    {
        if (context != null)
        {
            String loginStatus = CommonUtils.getPreferenceString(context, REGION_USER_PREFERENCES, PREF_USER_LOGIN_STATUS);
            return loginStatus;
        }
        return null;
    }

     public static void hideKeyBoard(Context context, View v) {
        if (context != null && v != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        }
    }

}
