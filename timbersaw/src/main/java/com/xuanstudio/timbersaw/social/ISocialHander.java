package com.xuanstudio.timbersaw.social;

import android.app.Activity;

import com.xuanstudio.timbersaw.social.listener.ISendCmdListener;

/**
 * Created by xuanyu on 16/8/25.
 */
public interface ISocialHander {

    void authorize(Activity activity);

    void sendCmd(ICmd iCmd, ISendCmdListener listener);

    void setAccessToken(AccessToken accessToken);
}
