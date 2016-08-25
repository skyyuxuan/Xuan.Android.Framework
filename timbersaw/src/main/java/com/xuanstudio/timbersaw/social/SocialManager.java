package com.xuanstudio.timbersaw.social;

import android.content.Context;

/**
 * Created by xuanyu on 16/8/25.
 */
public class SocialManager {

    private static SocialManager mSocialManager = null;
    private static Context mContext = null;

    public static SocialManager getManager(Context context) {
        if (mSocialManager == null) {
            synchronized (SocialManager.class) {
                if (mSocialManager == null) {
                    mSocialManager = new SocialManager(context);
                }
            }
        }
        return mSocialManager;
    }

    private SocialManager(Context context) {
        this.mContext = context;
    }
}
