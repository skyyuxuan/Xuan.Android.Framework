package com.xuanstudio.timbersaw.social.listener;

import com.xuanstudio.timbersaw.social.SocialPlatformType;

/**
 * Created by xuanyu on 16/8/25.
 */
public interface ISendCmdListener {

    void onComplete(SocialPlatformType platform_type, String resutlt);

    void onError(SocialPlatformType platform_type, String err_msg);

    void onCancel(SocialPlatformType platform_type);
}
