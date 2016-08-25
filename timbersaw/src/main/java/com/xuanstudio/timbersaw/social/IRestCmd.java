package com.xuanstudio.timbersaw.social;

import okhttp3.Request;

/**
 * Created by xuanyu on 16/8/25.
 */
public interface IRestCmd extends ICmd {

    Request convertToRequestParam();

}
