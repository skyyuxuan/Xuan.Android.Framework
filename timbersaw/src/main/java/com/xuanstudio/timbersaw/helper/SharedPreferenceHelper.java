package com.xuanstudio.timbersaw.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by xuanyu on 16/8/24.
 */
public class SharedPreferenceHelper {

    private SharedPreferences sp;

    public SharedPreferenceHelper(Context context, String name) {
        this(context, name, Context.MODE_PRIVATE);
    }

    private SharedPreferenceHelper(Context context, String name, int model) {
        this.sp = getSp(context, name, model);
    }

    private SharedPreferences getSp(Context context, String filename, int model) {
        return context.getSharedPreferences(filename, model);
    }

    public <T> boolean saveObj(String key, T object) {
        Gson gson = new Gson();
        String value = gson.toJson(object);
        return save(key, value);

    }

    @SuppressWarnings("unchecked")
    public <T> T getObj(String key, T object) {
        if (sp != null) {
            String value = sp.getString(key, "");
            if (!value.equals("")) {
                Gson gson = new Gson();
                try {
                    object = (T) gson.fromJson(value, object.getClass());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

    public boolean save(String key, boolean value) {
        boolean isSuccess = false;
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
        return isSuccess;
    }

    public boolean save(String key, int value) {
        boolean isSuccess = false;
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt(key, value);
            editor.commit();
        }
        return isSuccess;
    }

    public boolean save(String key, float value) {
        boolean isSuccess = false;
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
        return isSuccess;
    }

    public boolean save(String key, long value) {
        boolean isSuccess = false;
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong(key, value);
            editor.commit();
        }
        return isSuccess;
    }

    public boolean save(String key, String value) {
        boolean isSuccess = false;
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString(key, value);
            editor.commit();
        }
        return isSuccess;
    }

    public String getString(String key) {
        return getString(key, "");
    }

    public String getString(String key, String defValue) {
        String value = "";
        if (sp != null) {
            value = sp.getString(key, defValue);
        }
        return value;
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        if (sp != null) {
            return sp.getBoolean(key, defValue);
        }
        return false;
    }

    public int getInt(String key) {
        return getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        if (sp != null) {
            return sp.getInt(key, defValue);
        }
        return 0;
    }

    public float getFloat(String key) {
        return getFloat(key, 0f);
    }

    public float getFloat(String key, float defValue) {
        if (sp != null) {
            return sp.getFloat(key, defValue);
        }
        return 0f;
    }

    public long getLong(String key) {
        return getLong(key, 0l);
    }

    public long getLong(String key, long defValue) {
        if (sp != null) {
            return sp.getLong(key, defValue);
        }
        return 0l;
    }
}
