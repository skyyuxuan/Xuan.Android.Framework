package com.xuanstudio.timbersaw.social.weibo.cmd;

import com.xuanstudio.timbersaw.social.Constants;
import com.xuanstudio.timbersaw.social.IRestCmd;
import com.xuanstudio.timbersaw.util.OkHttpUtil;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by xuanyu on 16/8/25.
 */
public class CmdWeiboUser implements IRestCmd {

    private String UserID;
    private String ScreenName;

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    @Override
    public Request convertToRequestParam() {
        Map<String, Object> map = new HashMap<>();
        RequestBody requestBody = OkHttpUtil.createJsonRequestBody(map);
        Request request = new Request.Builder()
                .url(createUrl())
                .post(requestBody)
                .build();
        return request;
    }

    private String createUrl() {
        String url = Constants.WeiboConstants.UserShow;
        return url + "?uid=" + getUserID();
    }
}
