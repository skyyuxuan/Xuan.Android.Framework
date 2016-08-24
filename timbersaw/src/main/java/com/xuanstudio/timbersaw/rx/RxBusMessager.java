package com.xuanstudio.timbersaw.rx;

/**
 * Created by xuanyu on 16/8/24.
 */
public abstract class RxBusMessager<T> {

    private RxBus rxBus;

    protected RxBusMessager() {
        rxBus = RxBus.getInstance();
    }

    protected abstract RxBusChangeEvent changeEvent();

    public abstract void onAction(T action);

    protected void post() {
        RxBusChangeEvent messagerChangeEvent = changeEvent();
        if (messagerChangeEvent != null && rxBus.hasObservers()) {
            rxBus.post(messagerChangeEvent);
        }
    }

    public class RxBusChangeEvent {
    }
}
