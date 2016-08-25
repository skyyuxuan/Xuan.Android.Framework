package com.xuanstudio.timbersaw.social.listener;

import com.xuanstudio.timbersaw.social.AccessToken;
import com.xuanstudio.timbersaw.social.SocialPlatformType;

/**
 * Created by xuanyu on 16/8/25.
 */
public interface IAuthListener {

    void onComplete(SocialPlatformType platform_type, AccessToken accessToken);

    void onError(SocialPlatformType platform_type, String err_msg);

    void onCancel(SocialPlatformType platform_type);
}
