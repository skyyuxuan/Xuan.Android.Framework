package com.xuanstudio.timbersaw.mvp;

/**
 * Created by xuanyu on 16/8/24.
 */
public interface IPresenter<V extends IMvpView> {

    void attachView(V mvpView);

    void detachView();

    void onStart();

    void onStop();

    void onPause();
}
