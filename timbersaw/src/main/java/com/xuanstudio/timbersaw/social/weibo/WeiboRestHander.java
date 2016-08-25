package com.xuanstudio.timbersaw.social.weibo;

import android.app.Activity;

import com.xuanstudio.timbersaw.social.AccessToken;
import com.xuanstudio.timbersaw.social.IRestCmd;
import com.xuanstudio.timbersaw.social.ICmd;
import com.xuanstudio.timbersaw.social.ISocialHander;
import com.xuanstudio.timbersaw.social.SocialPlatformType;
import com.xuanstudio.timbersaw.social.listener.ISendCmdListener;
import com.xuanstudio.timbersaw.util.OkHttpUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by xuanyu on 16/8/25.
 */
class WeiboRestHander implements ISocialHander {

    @Override
    public void authorize(Activity activity) {

    }

    @Override
    public void sendCmd(ICmd iCmd, final ISendCmdListener listener) {
        Request request = ConfigParams(iCmd);
        if (request != null) {
            OkHttpUtil.enqueue(request, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (listener != null) {
                        listener.onError(SocialPlatformType.WEIBO, e.getMessage());
                    }
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    ResponseBody requestBody = response.body();
                    if (requestBody != null) {
                        if (listener != null) {
                            listener.onComplete(SocialPlatformType.WEIBO, requestBody.string());
                        }
                    }
                }
            });
        }
    }

    @Override
    public void setAccessToken(AccessToken accessToken) {

    }

    private Request ConfigParams(ICmd iCmd) {
        if (iCmd instanceof IRestCmd) {
            IRestCmd iRestCmd = (IRestCmd) iCmd;
            return iRestCmd.convertToRequestParam();
        } else {
        }
        return null;
    }

}
