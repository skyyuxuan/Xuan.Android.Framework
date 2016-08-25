package com.xuanstudio.timbersaw.util;

import com.google.gson.Gson;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by xuanyu on 16/8/25.
 */
public class OkHttpUtil {

    private static Gson mGson;
    private static MediaType JSON;
    private static OkHttpClient mOkHttpClient;

    static {
        mOkHttpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();
        mGson = new Gson();
        JSON = MediaType.parse("application/json; charset=utf-8");
    }

    public static void enqueue(Request request, Callback callback) {
        mOkHttpClient.newCall(request).enqueue(callback);
    }

    public static RequestBody createJsonRequestBody(Map<String, Object> map) {
        String json = mGson.toJson(map);
        return createJsonRequestBody(json);
    }

    public static RequestBody createJsonRequestBody(String json) {
        RequestBody body = RequestBody.create(JSON, json);
        return body;
    }

}
